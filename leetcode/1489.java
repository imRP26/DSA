/*
 * https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
 */



/*
 * Approach from LC Official Editorial!
 */
class Solution {

    private int findParent(int x, int[] parent) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private boolean doUnion(int x, int y, int[] parent) {
        int xPar = findParent(x, parent), yPar = findParent(y, parent);
        if (xPar == yPar)
            return false;
        parent[yPar] = parent[xPar];
        return true;
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<int[]> edgelist = new ArrayList<>();
        int m = edges.length, mstcost = 0;
        for (int i = 0; i < m; i++)
            edgelist.add(new int[] {i, edges[i][0], edges[i][1], edges[i][2]});
        Collections.sort(edgelist, (a, b) -> a[3] - b[3]);
        int[] parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        for (int i = 0; i < m; i++) {
            int[] edge = edgelist.get(i);
            int u = edge[1], v = edge[2], w = edge[3];
            if (!doUnion(u, v, parent))
                continue;
            mstcost += w;
        }
        List<List<Integer> > res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        for (int i = 0; i < m; i++) { // edge to be not considered
            int cost = 0, numEdges = 0;
            for (int k = 0; k < n; k++)
                parent[k] = k;
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < m; j++) {
                int[] edge = edgelist.get(j);
                int u = edge[1], v = edge[2], w = edge[3];
                if (i == j || !doUnion(u, v, parent))
                    continue;
                cost += w;
                set.add(u);
                set.add(v);
                numEdges++;
            }
            if (set.size() < n || numEdges != n - 1 || cost > mstcost) // critical edge
                res.get(0).add(edgelist.get(i)[0]);
            else { // compulsorily take this edge and recalculate MST weight
                cost = edgelist.get(i)[3];
                for (int k = 0; k < n; k++)
                    parent[k] = k;
                doUnion(edgelist.get(i)[1], edgelist.get(i)[2], parent);
                for (int j = 0; j < m; j++) {
                    int[] edge = edgelist.get(j);
                    int u = edge[1], v = edge[2], w = edge[3];
                    if (i == j || !doUnion(u, v, parent))
                        continue;
                    cost += w;
                }
                if (cost == mstcost)
                    res.get(1).add(edgelist.get(i)[0]);
            }
        }
        return res;
    }
}
