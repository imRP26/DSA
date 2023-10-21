/*
 * https://cses.fi/problemset/task/1690/
 */
import java.io.*;
import java.util.*;


public class HamiltonianFlights {

	/*
	 * DP State :-
	 * dp(mask, i) = number of ways to move from node 1 to node i such that all the 
	 *				 the nodes which are in the subset represented by mask are visited 
	 *				 exactly once and all other nodes aren't visited at all.
	 * 
	 * DP Transition :-
	 * dp(currMask, i) += dp(prevMask, j), where prevMask = currMask ^ (1 << i) and 
	 * node j is directly connected to node i. 
	 * 
	 * Base Case :-
	 * If i == 1, then it means that we're currently at the starting vertex and hence
	 * dp(mask, i) = 1 when mask == 1, otherwise dp(mask, i) = 0.
	 * If i'th bit in mask isn't set, then dp(mask, i) = 0.
	 * 
	 * Final Answer :-
	 * dp(2 ^ n - 1, n) 				 
	 */

	private static final int mod = (int)1e9 + 7;
	private static int[][] dp;
	private static Map<Integer, List<Integer> > graph = new HashMap<>();

	private static int memoize(int mask, int node) {
		if (node == 0) // base case
			return mask == 1 ? 1 : 0;
		if ((mask & (1 << node)) == 0) // base case
			return 0;
		if (dp[mask][node] != -1)
			return dp[mask][node];
		int newMask = mask ^ (1 << node), ans = 0;
		for (int neighbor : graph.getOrDefault(node, Collections.emptyList()))
			ans = (ans + memoize(newMask, neighbor)) % mod;
		return dp[mask][node] = ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int nodes = Integer.parseInt(temp[0]), edges = Integer.parseInt(temp[1]);
		while (edges-- > 0) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			// reversed edges - helps in computation and also easier logic!
			graph.computeIfAbsent(v - 1, k -> new ArrayList<>()).add(u - 1);
		}
		dp = new int[1 << nodes][nodes];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		System.out.println(memoize((1 << nodes) - 1, nodes - 1));
	}
}
