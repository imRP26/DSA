/*
 * https://leetcode.com/problems/sum-of-distances-in-tree/
 */



/*
 * Approach of Subtree Sum + Count from -> 
 * https://leetcode.com/problems/sum-of-distances-in-tree/solutions/130611/sum-of-distances-in-tree/?orderBy=most_votes
 */
class Solution {

    int[] result, countSubtreeNodes;
    List<Set<Integer> > graph;
    int totalNodes;

    void dfs1(int node, int parent) {
        for (int child : graph.get(node)) {
            if (child == parent)
                continue;
            dfs1(child, node);
            countSubtreeNodes[node] += countSubtreeNodes[child];
            result[node] += result[child] + countSubtreeNodes[child];
        }
    }

    void dfs2(int node, int parent) {
        for (int child : graph.get(node)) {
            if (child == parent)
                continue;
            result[child] = result[node] - countSubtreeNodes[child] + totalNodes - countSubtreeNodes[child];
            dfs2(child, node);
        }
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        totalNodes = n;
        graph = new ArrayList<>();
        result = new int[n];
        countSubtreeNodes = new int[n];
        Arrays.fill(countSubtreeNodes, 1);
        for (int i = 0; i < n; i++)
            graph.add(new HashSet<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs1(0, -1);
        dfs2(0, -1);
        return result;
    }
}
