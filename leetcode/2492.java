import java.util.*;

/*
 * Simple Graph Theory
 */
class Solution {
    public int minScore(int n, int[][] roads) {
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int i = 1; i <= n; i++)
            graph.put(i, new ArrayList<>());
        for (int[] road : roads) {
            int u = road[0], v = road[1], w = road[2];
            List<Integer> node1Info = graph.get(u);
            node1Info.add(v);
            node1Info.add(w);
            List<Integer> node2Info = graph.get(v);
            node2Info.add(u);
            node2Info.add(w);
        }
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        int result = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Integer> nodeInfo = graph.get(node);
            if (visited[node])
                continue;
            visited[node] = true;
            for (int i = 0; i < nodeInfo.size(); i+= 2) {
                int v = nodeInfo.get(i), w = nodeInfo.get(i + 1);
                if (!visited[v]) {
                    queue.offer(v);
                    result = Math.min(result, w);
                }
            }
        }
        return result;
    }
}
