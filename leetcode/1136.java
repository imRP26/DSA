/*
 * https://leetcode.com/problems/parallel-courses/
 */



/*
 * Approach of Simple Topological Sorting
 */
class Solution {
    public int minimumSemesters(int n, int[][] relations) {
        int[] indegree = new int[n + 1];
        int res = 0, nodes = 0;
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int[] rel : relations) {
            indegree[rel[1]]++;
            graph.computeIfAbsent(rel[0], k -> new ArrayList<>()).add(rel[1]);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }
        while (!q.isEmpty()) {
            int sz = q.size();
            res++;
            for (int i = 0; i < sz; i++) {
                int node = q.poll();
                nodes++;
                for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                    if (--indegree[neighbor] == 0)
                        q.offer(neighbor);
                }
            }
        }
        return (nodes == n) ? res : -1;
    }
}



/*
 * Approach of Cycle check using DFS and Longest Path from LC Official Editorial!
 */
class Solution {

    private int[] vis, pathLength;
    private Map<Integer, List<Integer> > graph = new HashMap<>(); 

    private int dfsCheckCycle(int node) {
        if (vis[node] != 0)
            return vis[node];
        vis[node] = -1; // marked node as 'visiting'
        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (dfsCheckCycle(neighbor) == -1)
                return -1;
        }
        return vis[node] = 1; // marked node as 'visited'
    }
   
    private int dfsMaxPath(int node) {
        if (pathLength[node] != 0)
            return pathLength[node];
        int maxLength = 1;
        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            int length = dfsMaxPath(neighbor);
            maxLength = Math.max(maxLength, 1 + length);
        }
        return pathLength[node] = maxLength;
    }

    public int minimumSemesters(int n, int[][] relations) {
        for (int[] rel : relations)
            graph.computeIfAbsent(rel[0], k -> new ArrayList<>()).add(rel[1]);
        vis = new int[n + 1];
        for (int node = 1; node <= n; node++) {
            if (dfsCheckCycle(node) == -1)
                return -1;
        }
        pathLength = new int[n + 1];
        int res = 1;
        for (int node = 1; node <= n; node++) {
            int length = dfsMaxPath(node);
            res = Math.max(res, length);
        } 
        return res;
    }
}



/*
 * Same approach as above, but combining the cycle-check and max path length functions 
 * into a single entity!
 */
class Solution {

    private Map<Integer, List<Integer> > graph = new HashMap<>();
    private int[] vis;

    private int dfs(int node) {
        if (vis[node] != 0)
            return vis[node];
        vis[node] = -1; // marked node  as 'visiting'
        int maxLength = 1;
        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            int length = dfs(neighbor);
            if (length == -1)
                return -1;
            maxLength = Math.max(maxLength, 1 + length);
        }
        return vis[node] = maxLength; // marked node as 'visited'
    }

    public int minimumSemesters(int n, int[][] relations) {
        for (int[] rel : relations)
            graph.computeIfAbsent(rel[0], k -> new ArrayList<>()).add(rel[1]);
        vis = new int[n + 1];
        int res = 1;
        for (int node = 1; node <= n; node++) {
            int length = dfs(node);
            if (length == -1)
                return -1;
            res = Math.max(res, length);
        }
        return res;
    }
}
