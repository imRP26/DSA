/*
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */



/*
 * 2 very very good links that are to be followed for reference :-
 * My approach of BFS, in line with Striver's approach as mentioned in his video
 */
class Solution1 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]> > graph = new HashMap<>();
        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], w = flight[2];
            graph.computeIfAbsent(u, val -> new ArrayList<>()).add(new int[] {v, w});
        }
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {src, -1, 0});
        distance[src] = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int node = arr[0], stops = arr[1], cost = arr[2];
            if (stops + 1 > k || !graph.containsKey(node))
                continue;
            for (int[] a : graph.get(node)) {
                int neighbor = a[0], w = a[1];
                if (distance[neighbor] > w + cost) {
                    queue.offer(new int[] {neighbor, stops + 1, w + cost});
                    distance[neighbor] = w + cost;
                }
            }            
        }
        return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
    }
}



/*
 * Approach of Bellman Ford from LC official solution!
 * Important point check -> See as to how you always end up taking the previous 
 * iteration value in line nos. 56 and 57!!!
 */
class Solution2 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(dist, n);
            for (int[] flight : flights) {
                int u = flight[0], v = flight[1], w = flight[2];
                if (dist[u] != Integer.MAX_VALUE)
                    temp[v] = Math.min(temp[v], dist[u] + w);
            }
            dist = temp;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}



/*
 * Approach of Dijkstra from official LC Solution!
 * Can also refer to Striver's video on the same!!
 */
class Solution3 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]> > graph = new HashMap<>();
        for (int[] flight : flights)
            graph.computeIfAbsent(flight[0], val -> new ArrayList<>()).add(new int[] {flight[1], flight[2]});
        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // (distanceFromSourceNode, node, numStopsFromSourceNode)
        pq.offer(new int[] {0, src, 0});
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int dist = temp[0], node = temp[1], steps = temp[2];
            if (steps > stops[node] || steps > k + 1)
                continue;
            stops[node] = steps;
            if (node == dst)
                return dist;
            if (!graph.containsKey(node))
                continue;
            for (int[] arr : graph.get(node))
                pq.offer(new int[] {dist + arr[1], arr[0], steps + 1});
        }
        return -1;
    }
}
