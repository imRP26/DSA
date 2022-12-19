/*
 * https://leetcode.com/problems/find-if-path-exists-in-graph/
 */


// Simple BFS 
class Solution1 {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            List<Integer> list = graph.get(u);
            list.add(v);
            graph.put(u, list);
            list = graph.get(v);
            list.add(u);
            graph.put(v, list);
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            source = queue.poll();
            if (source == destination)
                return true;
            if (visited.contains(source))
                continue;
            visited.add(source);
            List<Integer> neighbors = graph.get(source);
            for (int node : neighbors) {
                if (!visited.contains(node))
                    queue.offer(node);
            }
        }
        return false;
    }
}



// Simple DFS
class Solution2 {

    boolean result = false;

    void dfs(int source, int destination, Map<Integer, List<Integer> > graph, Set<Integer> visited) {
        if (source == destination) {
            result = true;
            return;
        }
        visited.add(source);
        for (int node : graph.get(source)) {
            if (!visited.contains(node))
                dfs(node, destination, graph, visited);
        }
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, val -> new ArrayList<Integer>()).add(v);
            graph.computeIfAbsent(v, val -> new ArrayList<Integer>()).add(u);
        }
        Set<Integer> visited = new HashSet<>();
        dfs(source, destination, graph, visited);
        return result;
    }
}



// Aliter DFS
class Solution {

   boolean dfs(int source, int destination, Map<Integer, List<Integer> > graph, Set<Integer> visited) {
        if (source == destination)
            return true;
        visited.add(source);
        for (int node : graph.get(source)) {
            if (!visited.contains(node) && dfs(node, destination, graph, visited))
                return true;
        }
        return false;
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, val -> new ArrayList<Integer>()).add(v);
            graph.computeIfAbsent(v, val -> new ArrayList<Integer>()).add(u);
        }
        Set<Integer> visited = new HashSet<>();
        return dfs(source, destination, graph, visited);
    }
}



// Iterative DFS
class Solution3 {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, val -> new ArrayList<Integer>()).add(v);
            graph.computeIfAbsent(v, val -> new ArrayList<Integer>()).add(u);
        }
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(source);
        while (!stack.isEmpty()) {
            source = stack.pop();
            if (source == destination)
                return true;
            visited.add(source);
            for (int node : graph.get(source)) {
                if (!visited.contains(node))
                    stack.push(node);
            }
        }
        return false;
    }
}



// Union-Find
class Solution {

    int[] parent;
    int[] rank;

    int findParent(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    void unionOperation(int u, int v) {
        int uPar = findParent(u), vPar = findParent(v);
        if (rank[uPar] < rank[vPar]) {
            parent[uPar] = parent[vPar];
            rank[vPar] += rank[uPar];
        }
        else {
            parent[vPar] = parent[uPar];
            rank[uPar] += rank[vPar];
        }
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        rank = new int[n];
        Arrays.fill(rank, 1);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (findParent(u) == findParent(v))
                continue;
            unionOperation(u, v);
        }
        return findParent(source) == findParent(destination);
    }
}
