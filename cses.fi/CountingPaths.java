/* 
 * https://cses.fi/problemset/task/1136
 */
import java.io.*;
import java.lang.*;
import java.util.*;


public class CountingPaths {
	
	private static int numIter;
	private static int[] level, res;
	private static int[][] kthParent;
	private static Map<Integer, List<Integer> > graph = new HashMap<>();
	
	private static void dfs(int node, int par, int l) {
		kthParent[0][node] = par;
		level[node] = l;
		for (int child : graph.getOrDefault(node, Collections.emptyList())) {
		    if (child != par)
		        dfs(child, node, l + 1);    
		}
	}
	
	private static int kthParentQuery(int node, int k) {
		for (int i = 0; i <= numIter; i++) {
			if ((k & (1 << i)) != 0)
				node = kthParent[i][node];
			if (node == 0)
				break;
		}
		return node;
	}
	
	private static int lca(int x, int y) {
		if (level[x] > level[y])
			x = kthParentQuery(x, level[x] - level[y]);
		else if (level[y] > level[x])
			y = kthParentQuery(y, level[y] - level[x]);
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
	
	private static int postorder(int node, int par) {
		int ans = 0;
		for (int child : graph.getOrDefault(node, Collections.emptyList())) {
		    if (child != par)
		        ans += postorder(child, node);    
		}
		res[node] += ans;
		return res[node];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]), m = Integer.parseInt(temp[1]);
		level = new int[n + 1];
		res = new int[n + 1];
		graph.computeIfAbsent(0, k -> new ArrayList<>()).add(1);
		graph.computeIfAbsent(1, k -> new ArrayList<>()).add(0);
		for (int i = 1; i < n; i++) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
			graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
		}
		numIter = (int)(Math.log(n) / Math.log(2));
		kthParent = new int[numIter + 1][n + 1];
		dfs(0, 0, 0);
		for (int j = 1; j <= numIter; j++) {
			for (int i = 1; i <= n; i++) {
				int intermediate = kthParent[j - 1][i];
				kthParent[j][i] = (intermediate == 0) ? 0 : kthParent[j - 1][intermediate];
			}
		}
		while (m-- > 0) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			if (level[u] > level[v]) {
				int x = u;
				u = v;
				v = x;
			}
			int uvlca = lca(u, v);
			if (uvlca == u) {
				res[v]++;
				res[kthParent[0][u]]--;
			}
			else {
				res[u]++;
				res[kthParent[0][uvlca]]--;
				res[v]++;
				res[uvlca]--;
			}
			
		}
		postorder(0, 0);
		for (int i = 1; i <= n; i++)
			System.out.print(res[i] + " ");
	}
}
