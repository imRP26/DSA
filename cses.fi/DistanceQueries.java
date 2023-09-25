/*
 * https://cses.fi/problemset/task/1135/
 */
import java.io.*;
import java.util.*;


public class DistanceQueries {
	
	private static Map<Integer, List<Integer> > graph = new HashMap<>();
	private static int numIter;
	private static int[][] kthParent;
	private static int[] nodelevel;
	
	private static void dfs(int node, int parent, int level) {
		kthParent[0][node] = parent;
		nodelevel[node] = level;
		for (int child : graph.getOrDefault(node, Collections.emptyList())) {
			if (child != parent)
				dfs(child, node, 1 + level);
		}
	}
	
	private static int kthParentQuery(int node, int k) {
		for (int i = 0; i <= numIter; i++) {
			if ((k & (1 << i)) != 0)
				node = kthParent[i][node];
			if (node == -1)
				break;
		}
		return node;
	}

	private static int lca(int x, int y) {
		if (nodelevel[x] > nodelevel[y])
			x = kthParentQuery(x, nodelevel[x] - nodelevel[y]);
		else if (nodelevel[x] < nodelevel[y])
			y = kthParentQuery(y, nodelevel[y] - nodelevel[x]);
		if (x == y)
			return x;
		for (int k = numIter; k >= 0; k--) {
			int kthxPar = kthParent[k][x], kthyPar = kthParent[k][y];
			if (kthxPar != kthyPar) {
				x = kthxPar;
				y = kthyPar;
			}
		}
		return kthParent[0][x];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]), q = Integer.parseInt(temp[1]);
		for (int i = 1; i < n; i++) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
			graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
		}
		nodelevel = new int[n + 1];
		numIter = (int)(Math.log(n) / Math.log(2));
		kthParent = new int[numIter + 1][n + 1];
		for (int[] row : kthParent)
			Arrays.fill(row, -1);
		dfs(1, -1, 0);
		for (int j = 1; j <= numIter; j++) {
			for (int i = 1; i <= n; i++) {
				int intermediate = kthParent[j - 1][i];
				kthParent[j][i] = (intermediate == -1) ? -1 : kthParent[j - 1][intermediate];
			}
		}
		while (q-- > 0) {
			temp = br.readLine().trim().split(" ");
			int a = Integer.parseInt(temp[0]), b = Integer.parseInt(temp[1]);
			int res = nodelevel[a] + nodelevel[b] - 2 * nodelevel[lca(a, b)];
			System.out.println(res);
		}
	}
}
