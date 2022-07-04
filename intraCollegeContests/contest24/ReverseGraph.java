import java.util.*;

/*
 * Question Link -> https://binarysearch.com/problems/Reverse-Graph
 */



// Approach using 2-D lists
class Solution1 {
    public int[][] solve(int[][] graph) {
        List<List<Integer> > revGraph = new ArrayList<>();
        int n = graph.length;
        for (int i = 0; i < n; i++)
            revGraph.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].length; j++)
                revGraph.get(graph[i][j]).add(i);
        }
        int[][] result = new int[n][];
        for (int i = 0; i < n; i++)
            result[i] = new int[revGraph.get(i).size()];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < revGraph.get(i).size(); j++)
                result[i][j] = revGraph.get(i).get(j);
        }
        for (int i = 0; i < n; i++)
            Arrays.sort(result[i]);
        return result;
    }
}



// Approach using a List inside a HashMap
class Solution2 {
    public int[][] solve(int[][] graph) {
        int n = graph.length;
        Map<Integer, List<Integer> > revGraph = new HashMap<>();
        for (int i = 0; i < n; i++)
            revGraph.put(i, new ArrayList<Integer>());
        for (int i = 0; i < n; i++) {
            for (int j : graph[i])
                revGraph.get(j).add(i);
        }
        int[][] result = new int[n][];
        for (int i = 0; i < n; i++) {
            List<Integer> node = revGraph.get(i);
            result[i] = new int[node.size()];
            for (int j = 0; j < node.size(); j++)
                result[i][j] = node.get(j);
            Arrays.sort(result[i]);
        }
        return result;
    }
}
