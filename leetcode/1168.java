/*
 * https://leetcode.com/problems/optimize-water-distribution-in-a-village/
 */



/*
 * Approch of Prim's and Kruskal's MST
 * Refer to Striver's theory video for once!
 * Also don't forget about the concept of phantom node!
 */
class Solution {

    private Map<Integer, List<int[]> > graph = new HashMap<>();

    private int mstPrim(int n) {
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minPQ.offer(new int[] {0, 0});
        int res = 0;
        boolean[] vis = new boolean[n + 1];
        while (!minPQ.isEmpty()) {
            int[] temp = minPQ.poll();
            int u = temp[0];
            if (vis[u])
                continue;
            vis[u] = true;
            res += temp[1];
            for (int[] arr : graph.getOrDefault(u, Collections.emptyList())) {
                if (!vis[arr[0]])
                    minPQ.offer(new int[] {arr[0], arr[1]});
            }
        }
        return res;
    }

    private int[] parent, rank;

    private int findParent(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void doUnion(int x, int y) {
        int xPar = findParent(x), yPar = findParent(y);
        if (xPar == yPar)
            return;
        if (rank[xPar] >= rank[yPar]) {
            rank[xPar] += rank[yPar];
            parent[yPar] = parent[xPar];
        }
        else {
            rank[yPar] += rank[xPar];
            parent[xPar] = parent[yPar];
        }
    }

    private int mstKruskal(int[] wells, int[][] pipes) {
        int n = wells.length, res = 0;
        List<int[]> edges = new ArrayList<>();
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++)
            parent[i] = i;
        rank = new int[n + 1];
        Arrays.fill(rank, 1);
        for (int[] pipe : pipes)
            edges.add(pipe);
        for (int i = 1; i <= wells.length; i++)
            edges.add(new int[] {0, i, wells[i - 1]});
        Collections.sort(edges, (a, b) -> a[2] - b[2]);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (findParent(u) == findParent(v))
                continue;
            res += edge[2];
            doUnion(u, v);
        }
        return res;
    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        for (int i = 1; i <= n; i++) {
            graph.computeIfAbsent(0, k -> new ArrayList<>()).add(new int[] {i, wells[i - 1]});
            graph.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[] {0, wells[i - 1]});
        }
        for (int[] pipe : pipes) {
            graph.computeIfAbsent(pipe[0], k -> new ArrayList<>()).add(new int[] {pipe[1], pipe[2]});
            graph.computeIfAbsent(pipe[1], k -> new ArrayList<>()).add(new int[] {pipe[0], pipe[2]});
        }
        return mstPrim(n);
        //return mstKruskal(wells, pipes);
    }
}
