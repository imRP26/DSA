/*
 * https://cses.fi/problemset/task/1130
 */
import java.io.*;
import java.util.*;


public class TreeMatching {
	
	private static int[][] dp;
	private static Map<Integer, List<Integer> > graph = new HashMap<>();
	
	private static int memoization(int node, int par, int flag) {
		if (dp[node][flag] != -1)
			return dp[node][flag];
		int ans = 0;
		for (int child : graph.getOrDefault(node, Collections.emptyList())) {
			if (child == par)
				continue;
			if (flag == 0)
				ans += Math.max(memoization(child, node, 0), memoization(child, node, 1));
			else
				ans = Math.max(ans, 1 + memoization(node, par, 0) + memoization(child, node, 0) - 
									Math.max(memoization(child, node, 0), memoization(child, node, 1)));
		}
		return dp[node][flag] = ans;
	}
	
	public static void main(String[] ags) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		for (int i = 1; i < n; i++) {
			String[] temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
			graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
		}
		dp = new int[n + 1][2];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		System.out.println(Math.max(memoization(1, -1, 0), memoization(1, -1, 1)));
	}
}
