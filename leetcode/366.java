import java.util.*;

/*
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 */



// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}



/*
 * My approach of storing all the heights of the treenodes in a list and then 
 * sorting them and inserting into my result list in order of increasing heights.
 * NOTE : SYNTAX FOR INSERTION OF A 1-D LIST INTO A 2-D LIST.
 * Definition of height used here :- The height of a node is the number of edges 
 * from the node to the deepest leaf.
 */
class Solution1 {
    
    int computeNodeHeights(TreeNode root, List<int[]> nodeHeight) {
        if (root == null)
            return -1;
        int leftHeight = computeNodeHeights(root.left, nodeHeight);
        int rightHeight = computeNodeHeights(root.right, nodeHeight);
        int currHeight = 1 + Math.max(leftHeight, rightHeight);
        nodeHeight.add(new int[] {root.val, currHeight});
        return currHeight;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<int[]> nodeHeight = new ArrayList<>();
        computeNodeHeights(root, nodeHeight);
        List<List<Integer>> result = new ArrayList<>();
        Collections.sort(nodeHeight, (o1, o2) -> (o1[1] - o2[1]));
        int currHeight = -1;
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < nodeHeight.size(); i++) {
            int height = nodeHeight.get(i)[1];
            if (height != currHeight) {
                if (currHeight != -1)
                    result.add(new ArrayList<>(temp));
                temp.clear();
                currHeight = height;
            }
            temp.add(nodeHeight.get(i)[0]);
        }
        result.add(new ArrayList<>(temp));
        return result;
    }
}



/*
 * The essential of the problem is not to find the leaves, but group leaves of 
 * same level together and also to cut the tree. This is the exact role that 
 * backtracking plays. The helper function returns the level which is the 
 * distance from its furthest subtree leaf to root, which helps to identify 
 * which group the root belongs to.
 */
class Solution2 {

    int findLeavesHelper(TreeNode root, List<List<Integer>> result) {
        if (root == null)
            return -1;
        int leftLevel = findLeavesHelper(root.left, result);
        int rightLevel = findLeavesHelper(root.right, result);
        int currLevel = 1 + Math.max(leftLevel, rightLevel);
        if (result.size() == currLevel)
            result.add(new ArrayList<>());
        result.get(currLevel).add(root.val);
        root.left = root.right = null;
        return currLevel;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        findLeavesHelper(root, result);
        return result;
    }
}
