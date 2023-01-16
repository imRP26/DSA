/*
 * https://leetcode.com/problems/choose-edges-to-maximize-score-in-a-tree/
 */



/*
 * Approach 1 from 
 * https://leetcode.com/problems/choose-edges-to-maximize-score-in-a-tree/solutions/2441609/java-2-solutions-dp-or-no-dp-take-or-not-take-explained/
 */
class Solution {
	
	private long dfs(int mode, int currNode, List<int[]>[] map, Long[][] memo) {
		if (memo[currNode][mode] != null)
			return memo[currNode][mode];
		long skip = 0, take = 0;
		for (int[] m : map[currNode])
			skip += dfs(0, m[0], map, memo);
		if (mode == 0) {
			for (int[] m : map[currNode])
				take = Math.max(take, skip - dfs(0, m[0], map, memo) + dfs(1, m[0], map, memo) + m[1]);
		}
		return memo[currNode][mode] = Math.max(skip, take);
	}
	
	public long maxScore(int[][] edges) {
		int root = 0, numEdges = edges.length;
		List<int[]>[] map = new ArrayList[numEdges];
		Arrays.setAll(map, o -> new ArrayList<>());
		for (int i = 0; i < numEdges; i++) {
			if (edges[i][1] == -1) {
				root = i;
				continue;
			}
			map[edges[i][0]].add(new int[] {i, edges[i][1]});
		}
		return dfs(0, root, map, new Long[numEdges][2]);
	}
}
