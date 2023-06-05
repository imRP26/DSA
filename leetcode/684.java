/*
 * https://leetcode.com/problems/redundant-connection/
 */



/*
 * My naive and effective approach of DSU - the winner approach!
 */
class Solution {

    private int[] parent, rank;

    private int findParent(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void unionOperation(int u, int v) {
        int uPar = findParent(u), vPar = findParent(v);
        if (rank[uPar] < rank[vPar]) {
            rank[vPar] += rank[uPar];
            parent[uPar] = parent[vPar];
        }
        else {
            rank[uPar] += rank[vPar];
            parent[vPar] = parent[uPar];
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        int i = 0, nodes = edges.length;
        rank = new int[nodes + 1];
        Arrays.fill(rank, 1);
        parent = new int[nodes + 1];
        for (i = 1; i <= nodes; i++)
            parent[i] = i;
        for (i = 0; i < nodes; i++) {
            int u = edges[i][0], v = edges[i][1];
            if (findParent(u) == findParent(v))
                break;
            unionOperation(u, v);
        }
        return edges[i];
    }
}



/*
 * Simple (and intuitive) DFS solution from official LC Editorial
 */
class Solution {

    private Set<Integer> visited = new HashSet<>();
    private Map<Integer, List<Integer> > graph = new HashMap<>();

    private boolean dfs(int u, int v) {
        if (visited.contains(u))
            return false;
        if (u == v)
            return true;
        visited.add(u);
        for (int neib : graph.get(u)) {
            if (dfs(neib, v))
                return true;
        }
        return false;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int nodes = edges.length, u = -1, v = -1;
        for (int i = 1; i <= nodes; i++)
            graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            visited.clear();
            u = edge[0];
            v = edge[1];
            if (!graph.get(u).isEmpty() && !graph.get(v).isEmpty() && dfs(u, v))
            //if (dfs(u, v)) -> same meaning as above!
                break;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return new int[] {u, v};
    }
}
