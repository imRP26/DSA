import java.util.*;


/*
 * https://leetcode.com/problems/count-nodes-with-the-highest-score/
 */


/*
 * Just break down the cases and simulate them. And, oh yes, don't forget using "long"
 * You're awesome and you know it!!
 */
class Solution {

    Map<Integer, List<Integer> > graph;
    Map<Integer, Integer> subNodes;

    int countSubtreeNodes(int root) {
        List<Integer> neighbors = graph.get(root);
        int nodes = 1;
        for (int node : neighbors)
            nodes += countSubtreeNodes(node);
        subNodes.put(root, nodes);
        return nodes;
    }

    public int countHighestScoreNodes(int[] parents) {
        graph = new HashMap<>();
        int totalNodes = parents.length, root = -1;
        long maxProd = Integer.MIN_VALUE;
        for (int i = 0; i < totalNodes; i++)
            graph.put(i, new ArrayList<>());
        for (int i = 0; i < totalNodes; i++) {
            if (parents[i] == -1) {
                root = i;
                continue;
            }
            graph.get(parents[i]).add(i);
        }
        subNodes = new HashMap<>();
        for (int i = 0; i < totalNodes; i++)
            subNodes.put(i, 0);
        countSubtreeNodes(root);
        Map<Long, Integer> scoreCount = new HashMap<>();
        for (int i = 0; i < totalNodes; i++) {
            List<Integer> children = graph.get(i);
            long prod = 1;
            if (i == root) { // parent of the tree
                long leftSubtreeNodes = 0, rightSubtreeNodes = 0;
                leftSubtreeNodes += subNodes.get(children.get(0));
                if (children.size() == 2)
                    rightSubtreeNodes += subNodes.get(children.get(1));
                if (rightSubtreeNodes == 0)
                    rightSubtreeNodes++;
                prod = leftSubtreeNodes * rightSubtreeNodes;
            }
            else if (children.isEmpty()) // leaf node
                prod = totalNodes - 1;
            else { // internal node
                long remNodes = totalNodes - subNodes.get(i), leftSubtreeNodes = 0, rightSubtreeNodes = 0;
                leftSubtreeNodes += subNodes.get(children.get(0));
                if (children.size() == 2)
                    rightSubtreeNodes += subNodes.get(children.get(1));
                if (rightSubtreeNodes == 0)
                    rightSubtreeNodes++;
                prod = remNodes * leftSubtreeNodes * rightSubtreeNodes;
            }
            scoreCount.put(prod, scoreCount.getOrDefault(prod, 0) + 1);
            maxProd = Math.max(maxProd, prod);
        }
        return scoreCount.get(maxProd);
    }
}



/*
 * Alternative Solution referenced from here :- 
 * https://leetcode.com/problems/count-nodes-with-the-highest-score/solutions/1538012/intuitive-java-solution-faster-than-100/
 */

