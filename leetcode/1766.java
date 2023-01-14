/*
 * https://leetcode.com/problems/tree-of-coprimes/
 */



/*
 * Approach from 
 * https://leetcode.com/problems/tree-of-coprimes/solutions/1074581/java-dfs-o-n/ 
 */
class Solution {

	int[] result;
	boolean[][] coPrime = new boolean[51][51];
	Map<Integer, int[]> ancestor = new HashMap<>(); // <value, [depth, nodeIndex]>
	Map<Integer, List<Integer> > graph = new HashMap<>();

	private int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	private void dfs(int node, int parent, int nodeDepth, int[] nums) {
		int ancestorDepth = Integer.MAX_VALUE, closestAncestor = -1;
		for (int i = 1; i <= 50; i++) {
			if (coPrime[i][nums[node]] && ancestor.containsKey(i)) {
				if (ancestorDepth > nodeDepth - ancestor.get(i)[0]) {
					ancestorDepth = nodeDepth - ancestor.get(i)[0];
					closestAncestor = ancestor.get(i)[1];
				}
			}
		}
		result[node] = closestAncestor;
		int[] oldEntry = ancestor.containsKey(nums[node]) ? ancestor.get(nums[node]) : new int[] {-1, -1};
		ancestor.put(nums[node], new int[] {nodeDepth, node});
		for (int neighbor : graph.get(node)) {
			if (neighbor == parent)
				continue;
			dfs(neighbor, node, nodeDepth + 1, nums);
		}
		if (oldEntry[0] != -1)
			ancestor.put(nums[node], oldEntry);
		else   
			ancestor.remove(nums[node]);
	}


	public int[] getCoprimes(int[] nums, int[][] edges) {
		int n = nums.length;
		result = new int[n];
		for (int i = 1; i <= 50; i++) {
			for (int j = 1; j <= 50; j++) {
				if (gcd(i, j) == 1)
					coPrime[i][j] = coPrime[j][i] = true;
			}
		}
		for (int i = 0; i < n; i++)
			graph.put(i, new ArrayList<>());
		for (int[] edge : edges) {
			int u = edge[0], v = edge[1];
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		dfs(0, -1, 0, nums);
		return result;
	}
}
