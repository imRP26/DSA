/*
 * https://leetcode.com/problems/minimum-edge-reversals-so-every-node-is-reachable/
 */



/*
 * Approach of simple BFS and Re-rooting!
 * Perform BFS from a random node (in this case, node 0) and for each node encountered in the BFS 
 * traversal, the distance from the source node and number of edges reversed so far are recorded. 
 * After this, for each node, there are 2 components in the individual result array :-
 * Part 1 -> Traversal from the random starting node to the current node
 * Part 2 -> Traversal from the current node to the part of the tree other than that coming from 
 *           the random source node.
 * res[currentNode] = # edge reversals from part1 + # edge reversals from part2
 * # edge reversals from part1 -> distance of currentNode from startingNode - number of edge reversals 
 *                                at currentNode
 * # edge traversals from part2 -> total # edge reversals in the entire tree - number of edge reversals 
 *                                 at currentNode 
 */
class Solution {

    private Map<Integer, Set<Integer> > undirectedGraph = new HashMap<>(), directedGraph = new HashMap<>();
    private int[] dist, reversedEdgeCount;

    private int bfs(int src) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<>();
        dist[src] = 0;
        q.offer(src);
        int numReversedEdges = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            if (!vis.add(node))
                continue;
            for (int neighbor : undirectedGraph.getOrDefault(node, Collections.emptySet())) {
                if (!vis.contains(neighbor)) {
                    dist[neighbor] = 1 + dist[node];
                    q.offer(neighbor);
                    if (!directedGraph.getOrDefault(node, Collections.emptySet()).contains(neighbor)) {
                        ++numReversedEdges;
                        reversedEdgeCount[neighbor] = 1 + reversedEdgeCount[node];
                    }
                    else
                        reversedEdgeCount[neighbor] = reversedEdgeCount[node]; 
                }
            }
        }
        return numReversedEdges;
    }

    public int[] minEdgeReversals(int n, int[][] edges) {
        int[] res = new int[n];
        dist = new int[n];
        reversedEdgeCount = new int[n];
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            undirectedGraph.computeIfAbsent(u, k -> new HashSet<>()).add(v);
            undirectedGraph.computeIfAbsent(v, k -> new HashSet<>()).add(u);
            directedGraph.computeIfAbsent(u, k -> new HashSet<>()).add(v);
        }
        int numReversedEdges = bfs(0);
        for (int i = 0; i < n; i++) {
            int val1 = dist[i] - reversedEdgeCount[i], val2 = numReversedEdges - reversedEdgeCount[i];
            res[i] = val1 + val2;
        }
        return res;
    }
}



/*
 * 
 */
