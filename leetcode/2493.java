/*
 * Steps :-
 * (1) Divide the graph into its constituent connected components.
 * (2) Now for each component, check :- 
 *     (i) whether its bipartite.
 *     (ii) perform BFS from each of the vertices of that connected component 
            and return the maximum number of levels in the BFS tree.
 */


class Solution {

    int bfs(int src, Map<Integer, List<Integer> > graph, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        queue.offer(-1);
        colors[src] = 1;
        int levels = 0;
        while (!queue.isEmpty()) {
            src = queue.poll();
            if (src == -1) {
                if (!queue.isEmpty())
                    queue.offer(-1);
                levels++;
                continue;
            }
            List<Integer> neighbors = graph.get(src);
            for (int neighbor : neighbors) {
                if (colors[neighbor] == 0) {
                    colors[neighbor] = -colors[src];
                    queue.offer(neighbor);
                }
                else if (colors[neighbor] != -colors[src])
                    return -1;
            }
        }
        return levels;
    }

    List<Integer> findConnected(int src, Map<Integer, List<Integer> > graph, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(src);
        while (!queue.isEmpty()) {
            src = queue.poll();
            if (colors[src] != 0)
                continue;
            colors[src] = 1;
            list.add(src);
            List<Integer> neighbors = graph.get(src);
            for (int node : neighbors) {
                if (colors[node] == 0)
                    queue.offer(node);
            }
        }
        return list;
    }

    public int magnificentSets(int n, int[][] edges) {
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int i = 1; i <= n; i++)
            graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            List<Integer> adju = graph.get(u);
            adju.add(v);
            graph.put(u, adju);
            List<Integer> adjv = graph.get(v);
            adjv.add(u);
            graph.put(v, adjv);
        }
        List<List<Integer> > connectedComponents = new ArrayList<>();
        int[] colors = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (colors[i] != 0)
                continue;
            List<Integer> neighbors = findConnected(i, graph, colors);
            connectedComponents.add(neighbors);
        }
        int result = 0, numComponents = connectedComponents.size();
        for (int i = 0; i < numComponents; i++) {
            List<Integer> list = connectedComponents.get(i);
            int levels = Integer.MIN_VALUE;
            for (int j = 0; j < list.size(); j++) {
                Arrays.fill(colors, 0);
                int val = bfs(list.get(j), graph, colors);
                if (val == -1)
                    return -1;
                levels = Math.max(levels, val);
            }
            result += levels;
        }
        return result;
    }
}
