/*
 * https://leetcode.com/problems/min-cost-to-connect-all-points/
 */



/*
 * MST using Prim's and Kruskal's!
 */
class Solution {

    private Map<Integer, List<int[]> > graph = new HashMap<>();
    
    private int mstPrim(int src) {
        Set<Integer> vis = new HashSet<>();
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minPQ.offer(new int[] {src, 0});
        int res = 0;
        while (!minPQ.isEmpty()) {
            int[] temp = minPQ.poll();
            int u = temp[0];
            if (vis.contains(u))
                continue;
            vis.add(u);
            res += temp[1];
            for (int[] arr : graph.getOrDefault(u, Collections.emptyList())) {
                if (!vis.contains(arr[0]))
                    minPQ.offer(new int[] {arr[0], arr[1]});
            }
        }
        return res;
    }

    private int[] parent, rank;
    private List<int[]> edges = new ArrayList<>();

    private int findParent(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void doUnion(int x, int y) {
        int xPar = findParent(x), yPar = findParent(y);
        if (rank[xPar] >= rank[yPar]) {
            rank[xPar] += rank[yPar];
            parent[yPar] = parent[xPar];
        }
        else {
            rank[yPar] += rank[xPar];
            parent[xPar] = parent[yPar];
        } 
    }

    private int mstKruskal(int n, int[][] points) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        rank = new int[n];
        Arrays.fill(rank, 1);
        Collections.sort(edges, (a, b) -> a[2] - b[2]);
        int res = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (findParent(u) == findParent(v))
                continue;
            res += edge[2];
            doUnion(u, v);
        }
        return res;
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int d = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                graph.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[] {j, d});
                graph.computeIfAbsent(j, k -> new ArrayList<>()).add(new int[] {i, d});
                edges.add(new int[] {i, j, d});
            }
        }
        //return mstPrim(0);
        return mstKruskal(n, points);
    }
}
