import java.util.*;

/*
 * Question Link -> 
 * https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths-ii/
 */



/*
 * TreeMap + DSU + TreeMap.lowerKey()
 */
class DistanceLimitedPathsExist {

	TreeMap<Integer, int[]> map;

    public DistanceLimitedPathsExist(int n, int[][] edgeList) {
        map = new TreeMap<>();
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        //Arrays.stream(edgeList).forEach(edge -> pq.add(edge));
        for (int[] edge : edgeList) // O(n * log n)
        	pq.add(edge);
        int[] parent = new int[n];
        for (int i = 0; i < n; i++)
        	parent[i] = i;
        while (!pq.isEmpty()) {
        	int[] currEdge = pq.poll(); // O(|edgeList|)
        	union(parent, currEdge[0], currEdge[1]); // O(log n) on average
        	map.put(currEdge[2], parent.clone()); 
        }
    }
    
    int find(int[] parent, int v) { // O(log n) on average
    	if (parent[v] == v)
    		return v;
    	parent[v] = find(parent, parent[v]);
    	return parent[v];
    }

    void union(int[] parent, int u, int v) {
    	int uPar = find(parent, u), vPar = find(parent, v);
    	parent[vPar] = uPar;
    }

    public boolean query(int p, int q, int limit) {
    	/*
    	 * The lowerKey() method is used to return the greatest key strictly < 
    	 * given key, passed as the parameter.
    	 */
        Integer lower = map.lowerKey(limit);
        if (lower == null)
        	return false;
        int[] parent = map.get(lower);
        return find(parent, p) == find(parent, q);
    }
}

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * DistanceLimitedPathsExist obj = new DistanceLimitedPathsExist(n, edgeList);
 * boolean param_1 = obj.query(p,q,limit);
 */



 /*
  * Good Solution, but yet to understand fully, especially the significance of 
  * the weight array.
  * https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths-ii/discuss/1023623/Java-simple-O(lgn)-Union-Find-solution-150ms
  */
  class DistanceLimitedPathsExist1 {

	int[] parent;
	int[] rank;
	int[] weight;

	public DistanceLimitedPathsExist1(int n, int[][] edgeList) {
		parent = new int[n];
		rank = new int[n];
		weight = new int[n];
		Arrays.fill(weight, 0);
		Arrays.fill(rank, 1);
		for (int i = 0; i < n; i++)
			parent[i] = i;
		Arrays.sort(edgeList, (e1, e2) -> e1[2] - e2[2]);
		for (int[] edge : edgeList)
			union(edge[0], edge[1], edge[2]);
	}

	int find(int i, int limit) {
		if (parent[i] == i || weight[i] >= limit)
			return i;
		return find(parent[i], limit);
	}

	void union(int i, int j, int limit) {
		int iRank = find(i, Integer.MAX_VALUE), jRank = find(j, Integer.MAX_VALUE);
		if (iRank != jRank) {
			if (rank[iRank] <= rank[jRank]) {
				parent[iRank] = jRank;
				rank[jRank] += rank[iRank];
				weight[iRank] = limit;
			}
			else {
				parent[jRank] = iRank;
				rank[iRank] += rank[jRank];
				weight[jRank] = limit;
			}
		}
	}

	public boolean query(int p, int q, int limit) {
		return find(p, limit) == find(q, limit);
	}
}
