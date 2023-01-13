/*
 * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/
 */



/*
 * DFS-based Approach from 
 * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/solutions/2889382/longest-path-with-different-adjacent-characters/
 */
class Solution1 {
	
	int longestPath = 1;
	Map<Integer, List<Integer> > graph = new HashMap<>();
	
	private int dfs(int node, String s) {
		if (!graph.containsKey(node))
			return 1;
		int longestChain = 0, secondLongestChain = 0;
		for (int neighbor : graph.get(node)) {
			int longestChainFromChild = dfs(neighbor, s);
            if (s.charAt(node) == s.charAt(neighbor))
				continue;
            if (longestChainFromChild > longestChain) {
				secondLongestChain = longestChain;
				longestChain = longestChainFromChild;
			}
			else if (longestChainFromChild > secondLongestChain)
				secondLongestChain = longestChainFromChild;
		}
		longestPath = Math.max(longestPath, 1 + longestChain + secondLongestChain);
		return 1 + longestChain;
	}
	
	public int longestPath(int[] parent, String s) {
		for (int i = 1; i < parent.length; i++)
			graph.computeIfAbsent(parent[i], val -> new ArrayList<>()).add(i);
		dfs(0, s);
		return longestPath;
	}
}



/*
 * BFS based approach from 
 * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/solutions/2889382/longest-path-with-different-adjacent-characters/
 */
class Solution2 {
	public int longestPath(int[] parent, String s) {
		int n = parent.length, longestPath = 1;
		int[] childrenCount = new int[n];
		for (int i = 1; i < n; i++)
			childrenCount[parent[i]]++;
		Queue<Integer> queue = new LinkedList<>();
		int[][] longestChains = new int[n][2];
		for (int i = 1; i < n; i++) {
			if (childrenCount[i] == 0) { // leaf node
				longestChains[i][0] = 1;
				queue.offer(i);
			}
		}
		while (!queue.isEmpty()) {
			int node = queue.poll(), par = parent[node];
			int longestChainFromNode = longestChains[node][0];
			if (s.charAt(node) != s.charAt(par)) {
				if (longestChainFromNode > longestChains[par][0]) {
					longestChains[par][1] = longestChains[par][0];
					longestChains[par][0] = longestChainFromNode;
				}
				else if (longestChainFromNode > longestChains[par][1])
					longestChains[par][1] = longestChainFromNode;
			}
			longestPath = Math.max(longestPath, 1 + longestChains[par][0] + longestChains[par][1]);
			childrenCount[par]--;
			if (childrenCount[par] == 0 && par != 0) {
				longestChains[par][0]++;
				queue.offer(par);
			}
		}
		return longestPath;
	}
}
