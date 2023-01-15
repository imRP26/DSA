/*
 * https://leetcode.com/problems/number-of-good-paths/
 */



/*
 * DSU + HashMap Approach from 
 * https://leetcode.com/problems/number-of-good-paths/solutions/2892908/number-of-good-paths/
 */
class Solution {
	
	int[] parent, componentSize;
	Map<Integer, List<Integer> > graph = new HashMap<>();
	
	private int findParent(int x) {
		while (x != parent[x]) {
			parent[x] = parent[parent[x]];
			x = parent[x];
		}
		return x;
	}
	
	private void performUnion(int u, int v) {
		int uPar = findParent(u), vPar = findParent(v);
		if (uPar == vPar)
			return;
		if (componentSize[vPar] < componentSize[uPar]) {
			parent[vPar] = parent[uPar];
			componentSize[uPar] += componentSize[vPar];
		}
		else {
			parent[uPar] = parent[vPar];
			componentSize[vPar] += componentSize[uPar];
		}
	}
	
	public int numberOfGoodPaths(int[] vals, int[][] edges) {
		int nodes = vals.length, goodPaths = 0;
		parent = new int[nodes];
		for (int i = 0; i < nodes; i++)
			parent[i] = i;
		componentSize = new int[nodes];
		Arrays.fill(componentSize, 1);
		for (int[] edge : edges) {
			int u = edge[0], v = edge[1];
			graph.computeIfAbsent(u, val -> new ArrayList<>()).add(v);
			graph.computeIfAbsent(v, val -> new ArrayList<>()).add(u);
		}
		Map<Integer, List<Integer> > valuesToNodes = new TreeMap<>();
		for (int i = 0; i < nodes; i++)
			valuesToNodes.computeIfAbsent(vals[i], val -> new ArrayList<>()).add(i);
		for (int nodeValue : valuesToNodes.keySet()) {
			for (int node : valuesToNodes.get(nodeValue)) {
				if (!graph.containsKey(node))
					continue;
				for (int neighbor : graph.get(node)) {
					if (vals[node] >= vals[neighbor])
						performUnion(node, neighbor);
				}
			}
			Map<Integer, Integer> group = new HashMap<>();
			for (int node : valuesToNodes.get(nodeValue)) {
				int nodePar = findParent(node);
				group.put(nodePar, group.getOrDefault(nodePar, 0) + 1);
			}
			for (int groupKey : group.keySet()) {
				int sz = group.get(groupKey);
				goodPaths += sz * (sz + 1) / 2;
			}
		}
		return goodPaths;
	}
}
