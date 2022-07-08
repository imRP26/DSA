import java.util.*;

/*
 * Question Link -> 
 * https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/
 */



/*
 * OFFLINE QUERY PROCESSING :- 
 * Sort edgeList wrt increasing weight.
 * Sort queries wrt increasing query weight.
 * For each query, union all those edges that have weight < the query weight limit.
 * If 2 nodes of query are present in the same component, then output True, 
 * else False.
 */
class Solution {

	private int[] parents;

	private int find(int u) {
		while (u != parents[u]) {
			parents[u] = parents[parents[u]];
			u = parents[u];
		}
		return u;
	}

	private void union(int u, int v) {
		int parU = find(u), parV = find(v);
		parents[parU] = parV;
	}

	public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
		int numQueries = queries.length, index = 0, numEdges = edgeList.length;
		this.parents = new int[n];
		for (int i = 0; i < n; i++)
			parents[i] = i;
		int[][] sortedQueries = new int[numQueries][4]; // {src, dest, wt, index}
		for (int i = 0; i < numQueries; i++)
			sortedQueries[i] = new int[] {queries[i][0], queries[i][1], queries[i][2], i};
		Arrays.sort(sortedQueries, (a, b) -> a[2] - b[2]);
		Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
		boolean[] result = new boolean[numQueries];
		for (int[] query : sortedQueries) {
			while (index < numEdges && edgeList[index][2] < query[2]) {
				union(edgeList[index][0], edgeList[index][1]);
				index++;
			}
			result[query[3]] = (find(query[0]) == find(query[1]));
		}
		return result;
	}
}
