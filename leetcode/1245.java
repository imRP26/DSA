/*
 * https://leetcode.com/problems/tree-diameter/
 */



/*
 * Simple BFS Solution from TLE-Eliminators-7.0
 */
class Solution {

    private Map<Integer, List<Integer> > graph = new HashMap<>();

    private int[] bfs(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        Set<Integer> vis = new HashSet<>();
        int level = -1, ans = -1;
        q.offer(-1);
        while (!q.isEmpty()) {
            node = q.poll();
            if (node == -1) {
                if (!q.isEmpty())
                    q.offer(node);
                level++;
            }
            if (!vis.add(node))
                continue;
            ans = node;
            for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                if (!vis.contains(neighbor))
                    q.offer(neighbor);
            }
        }
        return new int[] {ans, level};
    }

    public int treeDiameter(int[][] edges) {
        if (edges.length == 0)
            return 0;
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        return bfs(bfs(0)[0])[1];
    }
}



/*
 * 
 */
