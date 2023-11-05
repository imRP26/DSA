/*
 * https://leetcode.com/problems/maximum-score-after-applying-operations-on-a-tree/
 */



/*
 * Approach of DP on Trees
 */
class Solution {
    
    private long[][] dp;
    private Map<Integer, List<Integer> > graph = new HashMap<>();
    
    /*
     * DP State :-
     * dp(node, take) = Maximum score obtained when we either consider this node to be picked (take = 1) 
     *                  or not picked (take = 0).
     * 
     * DP Transition :-
     * dp(node, 1) = sigma (dp(child, 1)) + values[node]
     * dp(node, 0) = max { sigma (dp(child, 1)), values[node] + sigma (dp(child, 0))}
     *
     * Base Case :-
     * dp(leafNode, 1) = values[leafNode], we need to pick up the leafNode and it has no further nodes 
     *                   in its subtree.
     * dp(leafNode, 0) = 0, since this node is not to be picked and there are no further nodes in its 
     *                   subtree.
     * 
     * Final Answer :-
     * dp(rootNode, 0)
     */
    
    private long dfs(int node, int parent, int take, int[] values) {
        if (graph.get(node).size() == 1 && node != 0)
            return take == 1 ? values[node] : 0;
        if (dp[node][take] != -1)
            return dp[node][take];
        if (take == 1) {
            long ans = values[node];
            for (int child : graph.get(node))
                ans += child != parent ? dfs(child, node, 1, values) : 0;
            return dp[node][take] = ans;
        }
        long notPick = 0, pick = values[node];
        for (int child : graph.get(node)) {
            notPick += child != parent ? dfs(child, node, 1, values) : 0;
            pick += child != parent ? dfs(child, node, 0, values) : 0;
        }
        return dp[node][take] = Math.max(pick, notPick);
    }
    
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        int n = edges.length;
        dp = new long[n + 2][2];
        for (long[] row : dp)
            Arrays.fill(row, -1);
        return dfs(0, -1, 0, values);
    }
}



/*
 * Approach of DFS
 */
class Solution {
    
    private Map<Integer, List<Integer> > graph = new HashMap<>();
    /*
     * Instead of just trying to calculate what this question is actually expecting, let's try computing 
     * the reverse thing - i.e., from the total sum of all the node values of the tree, just subtracting 
     * the minimum value(s) that can be subtracted - this is being done by the dfs(node, parent) function 
     * below.
     * 
     * When we're currently at a node, then we must assume that the only info that we need to act upon is 
     * the subtree of that node - no need to consider the ancestors of the current node as of then.
     * 
     * So if the current node is just a leaf node, then considering this node in my path would mean that 
     * my path sum will become 0, hence this node's value will need to be subtracted from the sum of 
     * values of all nodes, hence we return the leaf node's value.
     * 
     * When not at a leaf node, compute the desired sum (to be left out) from all of its descendant nodes 
     * in the subtree of the current node, and then return the min of that obtained sum and current node 
     * value - whichever is min, we need to exclude that set of node value(s) from the total sum.
     */
    private long dfs(int node, int parent, int[] values) {
        if (graph.get(node).size() == 1 && node != 0)
            return values[node];
        long sum = 0;
        for (int child : graph.getOrDefault(node, Collections.emptyList()))
            sum += child != parent ? dfs(child, node, values) : 0;
        return Math.min(sum, values[node]);
    }
    
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        long totalNodesSum = 0;
        for (int v : values)
            totalNodesSum += v;
        long val = dfs(0, -1, values);
        return totalNodesSum - val;
    }
}
