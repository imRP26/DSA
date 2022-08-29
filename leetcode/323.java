import java.util.*;

/*
 * Question Link -> 
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 */



// DFS using a List Of Integer arrays representation of a graph
class Solution1 {

    public int dfs(int src, List<Integer>[] graph, boolean[] visited) {
        if (visited[src])
            return 0;
        visited[src] = true;
        for (int node : graph[src])
            dfs(node, graph, visited);
        return 1;
    }

    public int countComponents(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int components = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++)
            components += dfs(i, graph, visited);
        return components;
    }
}



// BFS using a List of arrays representation of a graph
class Solution2 {

    public int bfs(int src, List<Integer>[] graph, boolean[] visited) {
        if (visited[src])
            return 0;
        visited[src] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        while (!queue.isEmpty()) {
            src = queue.poll();
            for (int node : graph[src]) {
                if (!visited[node]) {
                    visited[node] = true;
                    queue.offer(node);
                }
            }
        }
        return 1;
    }

    public int countComponents(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int components = 0;
        for (int i = 0; i < n; i++)
            components += bfs(i, graph, visited);
        return components;
    }
}



// DFS using 2-D list representation for a graph
class Solution3 {

    public void dfs(List<List<Integer> > graph, Set<Integer> visited, int v) {
        visited.add(v);
        for (int node : graph.get(v)) {
            if (!visited.contains(node))
                dfs(graph, visited, node);
        }
    }

    public int countComponents(int n, int[][] edges) {
        List<List<Integer> > graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                components++;
                dfs(graph, visited, i);
            }
        }
        return components;
    }
}



// BFS using 2-D list representation for a graph
class Solution4 {

    public void bfs(List<List<Integer> > graph, Set<Integer> visited, int v) {
        visited.add(v);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        while (!queue.isEmpty()) {
            v = queue.poll();
            for (int node : graph.get(v)) {
                if (visited.contains(node))
                    continue;
                queue.offer(node);
                visited.add(node);
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        List<List<Integer> > graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        Set<Integer> visited = new HashSet<>();
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (visited.contains(i))
                continue;
            components++;
            bfs(graph, visited, i);
        }
        return components;
    }
}



// Naive Union Find - TC = O(numNodes * numEdges), SC = O(numNodes)
class Solution5 {

    public int findParent(int[] parent, int i) {
        while (i != parent[i])
            i = parent[i];
        return i;
    }

    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        int components = n;
        for (int[] edge : edges) {
            int parent1 = findParent(parent, edge[0]);
            int parent2 = findParent(parent, edge[1]);
            if (parent1 != parent2) {
                parent[parent1] = parent2;
                components--;
            }
        }
        return components;
    }
}



// Union Find with Path Compression - TC = O(numNodes + numEdges * log(numNodes))
class Solution6 {

    public int findParent(int[] parent, int i) {
        if (i == parent[i])
            return i;
        return parent[i] = findParent(parent, parent[i]);
    }

    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        int components = n;
        for (int[] edge : edges) {
            int parent1 = findParent(parent, edge[0]);
            int parent2 = findParent(parent, edge[1]);
            if (parent1 != parent2) {
                parent[parent1] = parent2;
                components--;
            }
        } 
        return components;
    }
}



/*
 * Union Find by Path Compression and Union by Size :-
 * TC = O(n + m * α(n)) ≈ O(n + m), where m is the number of connections 
 * (union operations), n is the number of nodes.
 * Using both path compression and union by size ensures that the amortized time 
 * per operation is only α(n), which is optimal, where α(n) is the inverse 
 * Ackermann function. This function has a value α(n) < 5 for any value of n 
 * that can be written in this physical universe, so the disjoint-set operations 
 * take place in essentially constant time.
 */
class Solution7 {

    public int findParent(int[] parent, int i) {
        if (i == parent[i])
            return i;
        return parent[i] = findParent(parent, parent[i]);
    }

    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] componentSize = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            componentSize[i] = 1;
        }
        int components = n;
        for (int[] edge : edges) {
            int parent1 = findParent(parent, edge[0]);
            int parent2 = findParent(parent, edge[1]);
            if (parent1 != parent2) {
                if (componentSize[parent1] < componentSize[parent2]) {
                    componentSize[parent2] += componentSize[parent1];
                    parent[parent1] = parent2;
                }
                else {
                    componentSize[parent1] += componentSize[parent2];
                    parent[parent2] = parent1;
                }
                components--;
            }
        }
        return components;
    }
}



// Using a map representation for the graph
class Solution8 {

    public void dfsVisit(int src, Map<Integer, List<Integer> > graph, Set<Integer> visited) {
        for (int node : graph.get(src)) {
            if (visited.add(node))
                dfsVisit(node, graph, visited);
        }
    }

    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (visited.add(i)) {
                dfsVisit(i, graph, visited);
                components++;
            }
        }
        return components;
    }
}
