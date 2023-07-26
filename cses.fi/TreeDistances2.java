/*
 * https://cses.fi/problemset/task/1133
 */
import java.io.*;
import java.util.*;

/*
 * FULL Explanation :-
 * 
 *                                           (1)
 *                                     /    \        \
 *                                    /      \        \
 *                                  (c1) ... (ci) ... (cn)  
 * 
 * P.S. : For a more in-depth explanation, refer to Kartik Arora's video! 
 * 
 * res[i] = sum of distances from node i to all the other nodes in the tree.
 * subtreeAnswer[i] = sum of distances from node i to all other nodes in its subtree, 
 *                    assuming that the subtree is rooted at node no. 'i'.
 *                  = summation over (subtreeAnswer[ci] + numNodesInSubtree[ci]) over all i.
 * numNodesInSubtree[i] = number of nodes in the subtree rooted at node no. 'i'.
 * partialAnswer(1 | ci) = sum of the distances of all the nodes rom node no. 1 other 
 *                         than the nodes that belong to the subtree rooted at ci.
 *                       = res[1] - subtreeAnswer[ci] - numNodesInSubtree[ci]
 * res[ci] = subtreeAnswer[ci] + partialAnswer[1 | ci] + (total no. of nodes in the tree - numNodesInSubtree[ci])
 *         = subtreeAnswer[ci] + res[1] - subtreeAnswer[ci] - numNodesInSubtree[ci] + total no. of nodes - numNodesInSubtree[ci]
 * => res[ci] = res[parent] + nodes - 2 * numNodesInSubtree[ci]
 */

public class TreeDistances2 {
    
    private static int nodes;
    private static Map<Integer, List<Integer> > graph = new HashMap<>();
    private static long[] numNodesInSubtree, subtreeAnswer, result;

    private static long dfs1(int node, int parent) {
        long ans = 1;
        for (int child : graph.getOrDefault(node, Collections.emptyList())) {
            if (child != parent)
                ans += dfs1(child, node);
        }
        return numNodesInSubtree[node] = ans;
    }

    private static long dfs2(int node, int parent) {
        for (int child : graph.getOrDefault(node, Collections.emptyList())) {
            if (child != parent)
                subtreeAnswer[node] += dfs2(child, node);
        }
        return subtreeAnswer[node] + numNodesInSubtree[node];
    }

    private static void dfs3(int node, int parent) {
        result[node] = result[parent] + nodes - 2 * numNodesInSubtree[node];
        for (int child : graph.getOrDefault(node, Collections.emptyList())) {
            if (child != parent)
                dfs3(child, node);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nodes = Integer.parseInt(br.readLine().trim());
        for (int i = 1; i < nodes; i++) {
            String[] temp = br.readLine().trim().split(" ");
            int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        numNodesInSubtree = new long[nodes + 1];
        dfs1(1, 0);
        subtreeAnswer = new long[nodes + 1];
        dfs2(1, 0);
        result = new long[nodes + 1];
        result[1] = subtreeAnswer[1];
        for (int child : graph.getOrDefault(1, Collections.emptyList()))
            dfs3(child, 1);
        for (int i = 1; i <= nodes; i++)
            System.out.print(result[i] + " ");
    }
}
