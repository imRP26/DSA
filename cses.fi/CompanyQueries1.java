/*
 * https://cses.fi/problemset/task/1687/
 */
import java.io.*;
import java.lang.*;
import java.util.*;


// Base Question for Binary Lifting!
public class CompanyQueries1 {
	
	private static int n, numIter;
	private static int[][] kthParent;
	private static Map<Integer, List<Integer> > graph = new HashMap<>();
	
	private static void dfs(int node, int parent) {
		kthParent[0][node] = parent;
		for (int neighbor : graph.getOrDefault(node, Collections.emptyList()))
			dfs(neighbor, node);
	}
	
	private static int query(int x, int k) {
	    for (int i = 0; i <= numIter; i++) {
			if ((k & (1 << i)) != 0)
				x = kthParent[i][x];
			if (x == -1)
				break;
		}
		return x;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		n = Integer.parseInt(temp[0]);
		int q = Integer.parseInt(temp[1]); 
		numIter = (int)(Math.log(n + 1) / Math.log(2));
		temp = br.readLine().trim().split(" ");
		for (int i = 2; i <= n; i++) {
			int v = Integer.parseInt(temp[i - 2]);
			graph.computeIfAbsent(v, k1 -> new ArrayList<>()).add(i);
		}
		kthParent = new int[numIter + 1][n + 1];
		for (int[] row : kthParent)
		    Arrays.fill(row, -1);
		dfs(1, -1);
		for (int j = 1; j <= numIter; j++) {
			for (int i = 1; i <= n; i++) {
				int intermediate = kthParent[j - 1][i];
				kthParent[j][i] = (intermediate == -1) ? -1 : kthParent[j - 1][intermediate]; 
			}
		}
		while (q-- > 0) {
			temp = br.readLine().trim().split(" ");
			int x = Integer.parseInt(temp[0]), k = Integer.parseInt(temp[1]);
			System.out.println(query(x, k));
		}
	}
}
