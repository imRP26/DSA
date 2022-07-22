import java.util.*;

/*
 * https://leetcode.com/problems/connecting-cities-with-minimum-cost/
 */



/*
 * Use of Kruskal's algorithm to generate an MST for the graph - Union Find is 
 * used to detect cycle. Procedure :- 
 * (1) Sort the edges in non-decreasing order.
 * (2) Pick up the smallest edge that doesn't form a cycle.
 * (3) Repeat until the MST is formed & every node is connected.
 */
class Solution1 {

    int numComponents;
    int[] parent;

    int findParent(int x) {
        while (x != parent[x])
            x = parent[parent[x]];
        return x;
    }

    public int minimumCost(int n, int[][] connections) {
        parent = new int[n + 1];
        numComponents = n;
        for (int i = 1; i <= n; i++)
            parent[i] = i;
        int result = 0;
        Arrays.sort(connections, (a, b) -> (a[2] - b[2])); // O(M*log(M))
        for (int[] connection : connections) { // O(M * log(N))
            int uPar = findParent(connection[0]), vPar = findParent(connection[1]);
            if (uPar != vPar) {
                result += connection[2];
                parent[uPar] = vPar;
                numComponents--;
            }
        }
        return numComponents == 1 ? result : -1;
    }
}



/*
 * Use of Prim's algorithm with a Priority Queue to get the edge with least cost 
 * and a visited set to keep nodes that are added to the MST. Procedure :-
 * (1) Build a graph based on the edges.
 * (2) Randomly pick a node to start with (in this case, the node with id 1)
 * (3) Pop the edge with least cost :-
 *     (i) If the edge doesn' exist in the MST (visited set), add its cost to 
 *         total cost and add new edges starting from the end node to the queue.
 */
class Solution2 {
    public int minimumCost(int n, int[][] connections) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] connection : connections) {
            int u = connection[0], v = connection[1], w = connection[2];
            graph.computeIfAbsent(u, (k) -> new ArrayList<>());
            graph.computeIfAbsent(v, (k) -> new ArrayList<>());
            graph.get(u).add(new int[] {v, w});
            graph.get(v).add(new int[] {u, w});
        }
        int result = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        heap.offer(new int[] {1, 1, 0});
        Set<Integer> visited = new HashSet<>();
        while (!heap.isEmpty()) {
            int[] connection = heap.poll();
            int v = connection[1], w = connection[2];
            if (!visited.contains(v)) {
                result += w;
                visited.add(v);
                for (int[] neighbor : graph.get(v))
                    heap.add(new int[] {v, neighbor[0], neighbor[1]});
            }
        }
        return visited.size() == n ? result : -1;
    }
}
