/*
 * https://leetcode.com/problems/find-closest-node-to-given-two-nodes/
 */



/*
 * DFS from the 2 given nodes and update distance values 
 */ 
class Solution1 {

    private void dfs(int src, int parent, int[] distances, int[] edges, Set<Integer> visited) {
        if (visited.contains(src))
            return;
        visited.add(src);
        distances[src] = (parent == -1) ? 0 : Math.min(distances[src], 1 + distances[parent]);
        if (edges[src] != -1)
            dfs(edges[src], src, distances, edges, visited);
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length, maxDistance = Integer.MAX_VALUE, result = -1;
        int[] distances1 = new int[n], distances2 = new int[n];
        Arrays.fill(distances1, Integer.MAX_VALUE);
        Arrays.fill(distances2, Integer.MAX_VALUE);
        dfs(node1, -1, distances1, edges, new HashSet<>());
        dfs(node2, -1, distances2, edges, new HashSet<>());
        for (int i = 0; i < n; i++) {
            if (maxDistance > Math.max(distances1[i], distances2[i])) {
                result = i;
                maxDistance = Math.max(distances1[i], distances2[i]);
            }
        }
        return result;
    }
}



/*
 * BFS Approach 2 from 
 * https://leetcode.com/problems/find-closest-node-to-given-two-nodes/solutions/2864716/find-closest-node-to-given-two-nodes/?orderBy=most_votes
 */
class Solution2 {

    private void bfs(int src, int[] edges, int[] dist) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        dist[src] = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited.contains(node))
                continue;
            visited.add(node);
            int neighbor = edges[node];
            if (neighbor != -1 && !visited.contains(neighbor)) {
                dist[neighbor] = 1 + dist[node];
                queue.offer(neighbor);
            }
        }
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[] dist1 = new int[n], dist2 = new int[n];
        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);
        bfs(node1, edges, dist1);
        bfs(node2, edges, dist2);
        int minDistNode = -1, minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int dist = Math.max(dist1[i], dist2[i]);
            if (minDist > dist) {
                minDist = dist;
                minDistNode = i;
            }
        }
        return minDistNode;
    }
}
