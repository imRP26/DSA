/*
 * https://cses.fi/problemset/task/1130
 */
import java.io.*;
import java.util.*;

/*
 * THE BIG EXPLANATION :- (wrt the example tree given below)
 *                                                      (1)
 * 													 /	 \    \
 * 													/	 \     \
 *                                                (2)   (3)   (4)
 * 												      /  \  \
 * 													 /	 \   \
 *  												(5) (6) (7)
 * 
 * TFO :- The maximum number of edges where each node is an endpoint of atmaax 1 edge.
 * 
 * DP States :-
 * dp(i, 0) = answer for the subtree rooted at node 'i' such that there's no edge between the node 'i' and 
 * 			  any of its children!
 * dp(i, 1) = answer for the subtree rooted at node 'i' such that there's an edge between the node 'i' and 
 * 			  only 1 of its children!
 * The Final answer = max(dp[1][0], dp[1][1]) - assuming that the given tree is being rooted at node '1'.
 * 
 * Base Case :-
 * dp[leaf][0] = dp[leaf][1] = 0.
 * 
 * DP Transitions when we're currently at node '1' :-
 * dp[1][0] = max(dp[2][0], dp[2][1]) + max(dp[3][0], dp[3][1]) + max(dp[4][0], dp[4][1])
 * => max(dp[3][0]) + max(dp[4][0]) = dp[1][0] - max(dp[2][0], dp[2][1])
 * => dp[node][0] = summation over all children {max(dp[child][0], dp[child][1])}
 * If we consider the edge (1)----(2) in the final answer, then 
 * dp[1][1] = 1 + dp[2][0] + max(dp[3][0], dp[3][1]) + max(dp[4][0], dp[4][1])
 * => dp[1][1] = 1 + dp[1][0] + dp[2][0] - max(dp[2][0], dp[2][1])
 * => dp[node][1] = 1 + dp[node][0] + dp[child][0] - max(dp[child][0], dp[child][1]) 
 */

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
