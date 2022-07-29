import java.util.*;

/*
 * https://leetcode.com/problems/all-paths-from-source-lead-to-destination/
 */



/*
 * TC = O(E + V), SC = O(E + V)
 */
class Solution1 {

    enum State {PROCESSING, PROCESSED}

    List<Integer>[] buildDigraph(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int[] edge : edges)
            graph[edge[0]].add(edge[1]);
        return graph;
    }

    boolean verifyReach(List<Integer>[] graph, int source, int destination, State[] states) {
        if (states[source] != null)
            return states[source] == State.PROCESSED;
        if (graph[source].isEmpty())
            return source == destination;
        states[source] = State.PROCESSING;
        for (int neighbor : graph[source]) {
            if (!verifyReach(graph, neighbor, destination, states))
                return false;
        }
        states[source] = State.PROCESSED;
        return true;
    }

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<Integer>[] graph = buildDigraph(n, edges);
        return verifyReach(graph, source, destination, new State[n]);
    }
}



// Same solution as above, but using a different representation
class Solution2 {

    boolean dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int source, int destination) {
        if (!graph.containsKey(source))
            return source == destination;
        visited.add(source);
        for (int neighbor : graph.get(source)) {
            if (visited.contains(neighbor) || !dfs(graph, visited, neighbor, destination))
                return false;
        }
        visited.remove(source);
        return true;
    }

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges)
            graph.computeIfAbsent(edge[0], k -> new LinkedList<>()).add(edge[1]);
        return dfs(graph, new HashSet<>(), source, destination);
    }
}
