/*
 * https://leetcode.com/problems/minimum-edge-weight-equilibrium-queries-in-a-tree/
 */



/*
 * The tree is rooted at node '0'.
 * Let freq[x][w] = number of edges of length w upon going from the root node to the current node 'x'.
 * Minimum number of edges to be reversed = dist(x, y) - max{freq of w from x to y}
 * max{freq of w from x to y} = freq[x][w] + freq[y][w] - 2 * freq[lca(x, y)][w], where 1 <= w <= 26
 * dist(x, y) = level[x] + level[y] - 2 * level[lca(x, y)]
 * 
 * TC = O(n log n + 26 * Q * log n)
 */
class Solution {

    private int numIter;
    private int[] level;
    private int[][] freq, kthParent;
    private Map<Integer, List<int[]> > graph = new HashMap<>();

    private void dfs(int node, int parent, int h) {
        kthParent[0][node] = parent;
        level[node] = h;
        for (int[] arr : graph.getOrDefault(node, Collections.emptyList())) {
            int child = arr[0], w = arr[1];
            if (child == parent)
                continue;
            freq[child][w]++;
            if (parent != -1) {
                for (int wt = 1; wt <= 26; wt++)
                    freq[child][wt] += freq[node][wt];
            }
            dfs(child, node, h + 1);
        }
    }

    private int kthParentQuery(int node, int k) {
        for (int i = 0; i <= numIter; i++) {
            if ((k & (1 << i)) != 0)
                node = kthParent[i][node];
            if (node == -1)
                break;
        }
        return node;
    }

    private int lcaQuery(int x, int y) {
        if (level[x] > level[y])
            x = kthParentQuery(x, level[x] - level[y]);
        else if (level[y] > level[x])
            y = kthParentQuery(y, level[y] - level[x]);
        if (x == y)
            return x;
        for (int k = numIter; k >= 0; k--) {
            int kthxPar = kthParent[k][x], kthyPar = kthParent[k][y];
            if (kthxPar != kthyPar) {
                x = kthxPar;
                y = kthyPar;
            }
        }
        return kthParent[0][x];
    }

    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[] {edge[1], edge[2]});
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(new int[] {edge[0], edge[2]});
        }
        numIter = (int)(Math.log(n + 1) / Math.log(2));
        kthParent = new int[numIter + 1][n];
        for (int[] row : kthParent)
            Arrays.fill(row, -1);
        freq = new int[n][27];
        level = new int[n];
        dfs(0, -1, 0);
        for (int j = 1; j <= numIter; j++) {
            for (int i = 0; i < n; i++) {
                int intermediate = kthParent[j - 1][i];
                kthParent[j][i] = intermediate == -1 ? -1 : kthParent[j - 1][intermediate];
            }
        }
        int nq = queries.length;
        int[] res = new int[nq];
        for (int i = 0; i < nq; i++) {
            int a = queries[i][0], b = queries[i][1], lca = lcaQuery(a, b), maxFreq = 0, dist = level[a] + level[b] - 2 * level[lca];
            for (int w = 1; w <= 26; w++)
                maxFreq = Math.max(maxFreq, freq[a][w] + freq[b][w] - 2 * freq[lca][w]);
            res[i] = dist - maxFreq;
        }
        return res;
    }
}
