/*
 * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 */



/*
 * Approach 1 - My Naive Approach of keeping 2 separate hashmaps
 */
class Solution {
    public int minReorder(int n, int[][] connections) {
        Map<Integer, Set<Integer> > graph = new HashMap<>(), fiction = new HashMap<>();
        for (int[] conn : connections) {
            graph.computeIfAbsent(conn[0], val -> new HashSet<>()).add(conn[1]);
            fiction.computeIfAbsent(conn[0], val -> new HashSet<>()).add(conn[1]);
            fiction.computeIfAbsent(conn[1], val -> new HashSet<>()).add(conn[0]);
        }
        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i))
                graph.put(i, new HashSet<>());
        }
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();
        while(!queue.isEmpty()) {
            int node = queue.poll();
            visited.add(node);
            for (int neighbor : fiction.get(node)) {
                if (!visited.contains(neighbor) && fiction.get(node).contains(neighbor)) {
                    if (graph.get(node).contains(neighbor))
                        result++;
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }
}



/*
 * BFS Approach from LC Official solution
 */
class Solution {
    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<List<Integer> > > graph = new HashMap<>();
        for (int[] conn : connections) {
            graph.computeIfAbsent(conn[0], val -> new ArrayList<List<Integer> > ()).add(Arrays.asList(conn[1], 1));
            graph.computeIfAbsent(conn[1], val -> new ArrayList<List<Integer> > ()).add(Arrays.asList(conn[0], 0));
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visited = new boolean[n];
        int result = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;
            if (!graph.containsKey(node))
                continue;
            for (List<Integer> neighbors : graph.get(node)) {
                int neighbor = neighbors.get(0), edgeSign = neighbors.get(1);
                if (!visited[neighbor]) {
                    result += edgeSign;
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }
}



/*
 * Approach of DFS from official LC Editorial
 */
class Solution {

    private int result = 0;
    private Map<Integer, List<List<Integer> > > graph = new HashMap<>();
    private Set<Integer> visited = new HashSet<>();

    private void dfs(int node) {
        if (!graph.containsKey(node))
            return;
        visited.add(node);
        for (List<Integer> neighbors : graph.get(node)) {
            int neighbor = neighbors.get(0), edgeSign = neighbors.get(1);
            if (!visited.contains(neighbor)) {
                result += edgeSign;
                dfs(neighbor);
            }
        }
    }

    public int minReorder(int n, int[][] connections) {
        for (int[] conn : connections) {
            graph.computeIfAbsent(conn[0], val -> new ArrayList<List<Integer> > ()).add(Arrays.asList(conn[1], 1));
            graph.computeIfAbsent(conn[1], val -> new ArrayList<List<Integer> > ()).add(Arrays.asList(conn[0], 0));
        }
        dfs(0);
        return result;
    }   
}
