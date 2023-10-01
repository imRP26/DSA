/*
 * https://atcoder.jp/contests/dp/tasks/dp_g
 */



/*
 * Referred to the concept from -> https://www.youtube.com/watch?v=BrLekXK2hxg
 */
import java.io.*;
import java.util.*;


class Main {
    
    /*
     * DP State :-
     * dp[node] = maximum length of path starting from a root upto this particular node
     * 
     * DP Transitions :-
     * dp[node] = max(dp[node], 1 + dp[child])
     * 
     * Final Answer :-
     * max(dp[node]) over all the nodes in the graph
     */
    
    private static Map<Integer, List<Integer> > graph = new HashMap<>();
    private static int[] dp;
    private static Set<Integer> vis = new HashSet<>();
    
    private static void dfs(int node) {
        dp[node] = 0;
        vis.add(node);
        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!vis.contains(neighbor))
                dfs(neighbor);
            dp[node] = Math.max(dp[node], 1 + dp[neighbor]);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int res = 0, nodes = Integer.parseInt(temp[0]), edges = Integer.parseInt(temp[1]);
        while (edges-- > 0) {
            temp = br.readLine().trim().split(" ");
            int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }
        dp = new int[nodes + 1];
        for (int i = 1; i <= nodes; i++) {
            if (!vis.contains(i))
                dfs(i);
        }
        for (int i = 1; i <= nodes; i++)
            res = Math.max(res, dp[i]);
        System.out.println(res);
    }
}
