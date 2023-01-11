/*
 * https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
 */



/*
 * DFS Approach from 
 * https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/solutions/3033500/java-solution-with-explanation-dfs/
 */ 
class Solution {

    Map<Integer, List<Integer> > graph = new HashMap<>();

    private int minAppleCollectionTime(int src, List<Boolean> hasApple, int parent) {
        int totalTime = 0;
        for (int node : graph.get(src)) {
            if (node == parent)
                continue;
            totalTime += minAppleCollectionTime(node, hasApple, src);
        }
        if (src != 0 && (hasApple.get(src) || totalTime > 0))
            totalTime += 2;
        return totalTime;
    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, val -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, val -> new ArrayList<>()).add(u);
        }
        return minAppleCollectionTime(0, hasApple, -1);
    }
}
