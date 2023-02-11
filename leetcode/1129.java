/*
 * https://leetcode.com/problems/shortest-path-with-alternating-colors/
 */



/*
 * BFS + Clever manipulation on 2D visited array approach from 
 * https://leetcode.com/problems/shortest-path-with-alternating-colors/solutions/3049265/shortest-path-with-alternating-colors/
 */ 
class Solution1 {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        Map<Integer, List<Pair<Integer, Integer> > > graph = new HashMap<>();
        for (int[] edge : redEdges)
            graph.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(new Pair<>(edge[1], 0));
        for (int[] edge : blueEdges)
            graph.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(new Pair<>(edge[1], 1));
        boolean[][] visited = new boolean[n][2];
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;
        visited[0][0] = visited[0][1] = true;
        Queue<List<Integer> > queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(-1);
        queue.offer(list);
        while (!queue.isEmpty()) {
            List<Integer> list1 = queue.poll();
            int node = list1.get(0), steps = list1.get(1), color = list1.get(2);
            if (!graph.containsKey(node))
                continue;
            for (Pair<Integer, Integer> edge : graph.get(node)) {
                int neighbor = edge.getKey(), nextColor = edge.getValue();
                if (nextColor == color || visited[neighbor][nextColor])
                    continue;
                List<Integer> list2 = new ArrayList<>();
                list2.add(neighbor);
                list2.add(1 + steps);
                list2.add(nextColor);
                queue.offer(list2);
                visited[neighbor][nextColor] = true;
                result[neighbor] = Math.min(result[neighbor], 1 + steps);
            }
        }
        for (int i = 0; i < n; i++)
            result[i] = (result[i] == Integer.MAX_VALUE) ? -1 : result[i];
        return result;
    }
}



/*
 * Easier implementation, but exactly same approach as above!
 */
class Solution2 {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        Map<Integer, List<Pair<Integer, Integer> > > graph = new HashMap<>();
        for (int[] edge : redEdges)
            graph.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(new Pair<>(edge[1], 0));
        for (int[] edge : blueEdges)
            graph.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(new Pair<>(edge[1], 1));
        boolean[][] visited = new boolean[n][2];
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;
        visited[0][0] = visited[0][1] = true;
        Queue<int[] > queue = new LinkedList<>();
        queue.offer(new int[] {0, 0, -1});
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int node = temp[0], steps = temp[1], color = temp[2];
            if (!graph.containsKey(node))
                continue;
            for (Pair<Integer, Integer> edge : graph.get(node)) {
                int neighbor = edge.getKey(), nextColor = edge.getValue();
                if (nextColor == color || visited[neighbor][nextColor])
                    continue;
                queue.offer(new int[] {neighbor, 1 + steps, nextColor});
                visited[neighbor][nextColor] = true;
                result[neighbor] = Math.min(result[neighbor], 1 + steps);
            }
        }
        for (int i = 0; i < n; i++)
            result[i] = (result[i] == Integer.MAX_VALUE) ? -1 : result[i];
        return result;
    }
} 
