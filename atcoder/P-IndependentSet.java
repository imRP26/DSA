/*
 * https://atcoder.jp/contests/dp/tasks/dp_p
 */
import java.io.*;
import java.util.*;


public class Main {
    
    /*
     * DP State :-
     * dp(i, j) = number of ways of coloring the subtree rooted at node 'i' when node 'i' is colored 
     *            using color 'j'.
     * Here, j = 0 and 1 mean that the node is to be colored as black and white respectively.  
     * 
     * DP Transition :-
     * dp(node, 0) = Product {dp(child, 1)} over all children 'child' of 'node'.
     * dp(node, 1) = Product {dp(child, 0) + dp(child, 1)} over all children 'child' of 'node'.
     * 
     * Base Case :-
     * dp(leaf, 0) = dp(leaf, 1) = 1
     * 
     * Final Answer :-
     * dp(1, 0) + dp(1, 1), where 1 is selected to be the root arbitrarily.
     */

    private static final long mod = (long)1e9 + 7;
    private static long[][] dp;
    private static Map<Integer, List<Integer> > graph = new HashMap<>();
    
    private static long dfs(int node, int parent, int color) {
        if (dp[node][color] != -1)
            return dp[node][color];
        long ans = 1;
        for (int child : graph.getOrDefault(node, Collections.emptyList())) {
            if (child == parent)
                continue;
            if (color == 0)
                ans = (ans * dfs(child, node, 1)) % mod;
            else {
                long val = (dfs(child, node, 0) + dfs(child, node, 1)) % mod;
                ans = (ans * val) % mod;
            }
        }
        return dp[node][color] = ans;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(br.readLine().trim());
        for (int i = 1; i < nodes; i++) {
            String[] temp = br.readLine().trim().split(" ");
            int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        dp = new long[nodes + 1][2];
        for (long[] row : dp)
            Arrays.fill(row, -1);
        long res = (dfs(1, 0, 0) + dfs(1, 0, 1)) % mod;
        System.out.println(res);
    }
}
