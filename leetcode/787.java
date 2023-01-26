/*
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */



/*
 * My solution that got me AC!! Happy!!
 */
class Solution {
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
 * 2 very very good links that are to be followed for reference :-
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/solutions/2825208/cheapest-flights-within-k-stops/
 * Striver's video on this question
 */
 