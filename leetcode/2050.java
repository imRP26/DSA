/*
 * https://leetcode.com/problems/parallel-courses-iii/
 */



/*
 * Approach of Topological Sorting from LC Official Editorial!
 * Go through the examples given in the editorial in order to understand why vanilla
 * topological sorting won't make the cut - don't be so fucking naive!
 */
class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        Map<Integer, List<Integer> > graph = new HashMap<>();
        int[] indegree = new int[n + 1], reachTime = new int[n + 1];
        for (int[] rel : relations) {
            graph.computeIfAbsent(rel[0], k -> new ArrayList<>()).add(rel[1]);
            indegree[rel[1]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0)
                q.offer(i);
            reachTime[i] = time[i - 1];
        }
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                reachTime[neighbor] = Math.max(reachTime[neighbor], reachTime[node] + time[neighbor - 1]);
                if (--indegree[neighbor] == 0)
                    q.offer(neighbor);
            }
        }
        int res = 0;
        for (int t : reachTime)
            res = Math.max(t, res);
        return res;
    }
}



/* 
 * Approach of DFS + DP from LC Official Editorial!
 */
class Solution {

    private Map<Integer, List<Integer> > graph = new HashMap<>();
    private Map<Integer, Integer> dp = new HashMap<>();

    private int dfs(int node, int[] time) {
        if (dp.containsKey(node))
            return dp.get(node);
        List<Integer> neighbors = graph.getOrDefault(node, Collections.emptyList());
        if (neighbors.size() == 0)
            return time[node - 1];
        int ans = 0;
        for (int neighbor : neighbors)
            ans = Math.max(ans, dfs(neighbor, time));
        dp.put(node, time[node - 1] + ans);
        return time[node - 1] + ans;
    }

    public int minimumTime(int n, int[][] relations, int[] time) {
        for (int[] rel : relations)
            graph.computeIfAbsent(rel[0], k -> new ArrayList<>()).add(rel[1]);
        int res = 0;
        for (int node = 1; node <= n; node++)
            res = Math.max(res, dfs(node, time));
        return res;
    }
}
