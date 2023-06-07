/*
 * https://leetcode.com/problems/shortest-cycle-in-a-graph/
 */



/*
 * Approach followed from here -> 
 * https://leetcode.com/problems/shortest-cycle-in-a-graph/solutions/3366362/java-python-3-bfs-within-dfs/
 */
class Solution {
    public int findShortestCycle(int n, int[][] edges) {
        Map<Integer, Set<Integer> > graph = new HashMap<>();
		for (int[] edge : edges) {
			graph.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
			graph.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
		}
		int result = Integer.MAX_VALUE;
		int[] distance = new int[n], parent = new int[n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(distance, Integer.MAX_VALUE);
			Arrays.fill(parent, -1);
			Queue<Integer> q = new LinkedList<>();
			q.offer(i);
			distance[i] = 0;
			while (!q.isEmpty()) {
				int node = q.poll();
				for (int neighbor : graph.getOrDefault(node, Collections.emptySet())) {
					if (distance[neighbor] == Integer.MAX_VALUE) {
						distance[neighbor] = 1 + distance[node];
						parent[neighbor] = node;
						q.offer(neighbor);
					}
					else if (parent[neighbor] != node && parent[node] != neighbor)
						result = Math.min(result, distance[node] + distance[neighbor] + 1);
				}
			}
		}
		return (result != Integer.MAX_VALUE) ? result : -1;
    }
}
