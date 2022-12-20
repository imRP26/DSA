/*
 * https://leetcode.com/problems/graph-valid-tree/
 */



// BFS + DSU
class Solution1 {

    int[] parent;
    int[] rank;
    Map<Integer, List<Integer> > graph;

    int findParent(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    boolean union(int u, int v) {
        int uPar = findParent(u), vPar = findParent(v);
        if (uPar == vPar)
            return false;
        if (rank[uPar] < rank[vPar]) {
            parent[uPar] = parent[vPar];
            rank[vPar] += rank[uPar];
        }
        else {
            parent[vPar] = parent[uPar];
            rank[uPar] += rank[vPar];
        }
        return true;
    }

    boolean bfs(int n) {
        int numNodes = 0;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited.contains(node))
                continue;
            visited.add(node);
            numNodes++;
            if (!graph.containsKey(node))
                continue;
            List<Integer> neighbors = graph.get(node);
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor))
                    queue.offer(neighbor);
            }
        }
        return numNodes == n;
    }

    public boolean validTree(int n, int[][] edges) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        rank = new int[n];
        Arrays.fill(rank, 1);
        graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (!union(u, v))
                return false;
            graph.computeIfAbsent(u, val -> new ArrayList<Integer>()).add(v);
            graph.computeIfAbsent(v, val -> new ArrayList<Integer>()).add(u);
        }
        return bfs(n);
    }
}



// BFS - not sure if this will give AC with all TCs
class Solution2 {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1)
            return false;
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, val -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, val -> new ArrayList<>()).add(u);
        }
        Set<Integer> visited = new HashSet<>();
        int numNodes = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited.contains(node))
                continue;
            visited.add(node);
            numNodes++;
            if (!graph.containsKey(node))
                continue;
            for (int neighbor : graph.get(node)) {
                if (!visited.contains(neighbor))
                    queue.offer(neighbor);
            }
        }
        return numNodes == n;
    }
}
