/*
 * https://leetcode.com/problems/possible-bipartition/
 */



// Node Colouring + BFS
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] nodeColor = new int[n + 1];
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int[] edge : dislikes) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, val -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, val -> new ArrayList<>()).add(u);
        }
        for (int i = 1; i <= n; i++) {
            if (nodeColor[i] != 0 || !graph.containsKey(i))
                continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            nodeColor[i] = 1;
            while (!queue.isEmpty()) {
                int x = queue.poll();
                for (int node : graph.get(x)) {
                    if (nodeColor[node] == 0) {
                        nodeColor[node] = 3 - nodeColor[x];
                        queue.offer(node);
                    }
                    else if (nodeColor[node] == nodeColor[x])
                        return false;
                }
            }
        }
        return true;
    }
}
