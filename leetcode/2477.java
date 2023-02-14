/*
 * https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/
 */



/*
 * Approach 1 (DFS) from 
 * https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/solutions/3080167/minimum-fuel-cost-to-report-to-the-capital/
 */
class Solution1 {
	
	long fuel;
	Map<Integer, List<Integer> > graph = new HashMap<>();

	public long dfs(int node, int parent, int seats) {
		int representatives = 1;
		if (!graph.containsKey(node))
			return representatives;
		for (int neighbor : graph.get(node)) {
			if (neighbor == parent)
				continue;
			representatives += dfs(neighbor, node, seats);
		}
		if (node != 0)
			fuel += Math.ceil((double)representatives / seats);
		return representatives;
	}

	public long minimumFuelCost(int[][] roads, int seats) {
		for (int[] road : roads) {
			graph.computeIfAbsent(road[0], val -> new ArrayList<>()).add(road[1]);
			graph.computeIfAbsent(road[1], val -> new ArrayList<>()).add(road[0]);
		}
		dfs(0, -1, seats);
		return fuel;
	}
} 



/*
 * 
 */

