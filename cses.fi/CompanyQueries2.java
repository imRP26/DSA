/*
 * https://cses.fi/problemset/task/1688/
 */
import java.io.*;
import java.lang.*;
import java.util.*;

// Base question for computing LCA in O(log(n))
public class CompanyQueries2 {
	
	private static int n, numIter;
	private static int[] nodeLevel;
	private static int[][] kthParent;
	private static Map<Integer, List<Integer> > graph = new HashMap<>();
	
	private static void dfs(int node, int parent, int level) {
		nodeLevel[node] = level;
		kthParent[0][node] = parent;
		for (int child : graph.getOrDefault(node, Collections.emptyList()))
			dfs(child, node, level + 1);
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
		if (nodeLevel[x] > nodeLevel[y])
			x = kthParentQuery(x, nodeLevel[x] - nodeLevel[y]);
		else if (nodeLevel[y] > nodeLevel[x])
			y = kthParentQuery(y, nodeLevel[y] - nodeLevel[x]);
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
		n = Integer.parseInt(temp[0]);
		int q = Integer.parseInt(temp[1]);
		temp = br.readLine().trim().split(" ");
		for (int i = 2; i <= n; i++) {
			int node = Integer.parseInt(temp[i - 2]);
			graph.computeIfAbsent(node, k -> new ArrayList<>()).add(i);
		}
		numIter = (int)(Math.log(n) / Math.log(2));
		kthParent = new int[numIter + 1][n + 1];
		nodeLevel = new int[n + 1];
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
			int x = Integer.parseInt(temp[0]), y = Integer.parseInt(temp[1]);
			System.out.println(lca(x, y));
		}
	}
}
