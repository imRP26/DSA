/*
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 */



/*
 * DFS + Backtracking - My Naive Solution, but proper AC nevertheless!
 * Good Solution reference - Approach 1 from (Especially how the TC is calculated!!)
 * https://leetcode.com/problems/all-paths-from-source-to-target/solutions/716919/all-paths-from-source-to-target/
 */ 
class Solution {

    List<List<Integer> > result;
    boolean[] visited = new boolean[20];

    private void dfs(int[][] graph, int currVertex, int dest, List<Integer> temp) {
        if (currVertex == dest) {
            temp.add(currVertex);
            result.add(new ArrayList<>(temp));
            temp.remove(temp.size() - 1);
            return;
        }
        visited[currVertex] = true;
        temp.add(currVertex);
        for (int i = 0; i < graph[currVertex].length; i++) {
            int node = graph[currVertex][i];
            if (!visited[node])
                dfs(graph, node, dest, temp);
        }
        visited[currVertex] = false;
        temp.remove(temp.size() - 1);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int src = 0, dest = graph.length - 1;
        result = new ArrayList<>();
        dfs(graph, 0, dest, new ArrayList<Integer>());
        return result;
    }
}
