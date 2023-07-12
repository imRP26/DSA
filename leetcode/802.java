/*
 * https://leetcode.com/problems/find-eventual-safe-states/
 */



/*
 * Messy Version that somehow got AC!! :D
 */
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int nodes = graph.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nodes; i++) {
            if (graph[i].length == 0)
                set.add(i);
        }
        while (true) {
            int oldSize = set.size();
            for (int i = 0; i < nodes; i++) {
                if (set.contains(i))
                    continue;
                int safeNodes = 0;
                for (int j = 0; j < graph[i].length; j++) {
                    if (set.contains(graph[i][j]))
                        safeNodes++;
                }
                if (safeNodes == graph[i].length)
                    set.add(i);
            }
            if (set.size() == oldSize)
                break;
        }
        List<Integer> res = new ArrayList<>();
        for (int node : set)
            res.add(node);
        Collections.sort(res);
        return res;
    }
}



/*
 * Approach of Topological Sort from LC Official Editorial using the technique of 
 * reversed graph - basically reverse the edges to get those nodes having indegree = 0
 */
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int nodes = graph.length;
        Map<Integer, Set<Integer> > revGraph = new HashMap<>();
        int[] indegree = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            for (int j : graph[i]) {
                indegree[i]++;
                revGraph.computeIfAbsent(j, k -> new HashSet<>()).add(i);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < nodes; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        } 
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            res.add(node);
            for (int neighbor : revGraph.getOrDefault(node, Collections.emptySet())) {
                if (--indegree[neighbor] == 0)
                    q.offer(neighbor);
            }
        }
        Collections.sort(res);
        return res;
    }
}



/*
 * Approach of DFS and Node-Coloring from here -> 
 * https://leetcode.com/problems/find-eventual-safe-states/solutions/120633/java-solution-dfs-topological-sort/
 * color[i] = 1 => node 'i' is visiting.
 * color[i] = 0 => node 'i' is not visited.
 * color[i] = 2 => node 'i' has already been visited.
 * color[i] = 1 => node 'i' is visited again, meaning its part of a cycle, meaning its not safe!
 */
class Solution {

    private int[] nodeColor;

    private boolean dfs(int i, int[][] graph) {
        if (nodeColor[i] > 0)
            return nodeColor[i] == 2;
        nodeColor[i] = 1;
        for (int j : graph[i]) {
            if (nodeColor[j] == 2)
                continue;
            if (nodeColor[j] == 1 || !dfs(j, graph))
                return false;
        }
        nodeColor[i] = 2;
        return true;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int nodes = graph.length;
        nodeColor = new int[nodes];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            if (dfs(i, graph))
                res.add(i);
        }
        return res;
    }
}
