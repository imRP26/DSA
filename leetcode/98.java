import java.util.*;

/*
 * https://leetcode.com/problems/validate-binary-search-tree/
 */



// Definition for a binary tree node.
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



// My Naive Approach
class Solution1 {
    
    void inorder(TreeNode root, List<Integer> nodes) {
        if (root == null)
            return;
        inorder(root.left, nodes);
        nodes.add(root.val);
        inorder(root.right, nodes);
    }
    
    public boolean isValidBST(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        inorder(root, nodes);
        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i) <= nodes.get((i - 1)))
                return false;
        }
        return true;
    }
}



/*
 * Recursively iterating over the tree while defining the interval <minVal, maxVal> 
 * for each node which value must fit in.
 */
class Solution2 {

    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null)
            return true;
        return (min == null || root.val > min.val) && 
               (max == null || root.val < max.val) && 
               isValidBST(root.left, min, root) && 
               isValidBST(root.right, root, max);
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
}



/*
 * Iterative Inorder Traversal using a Stack
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prevNode = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prevNode != null && root.val <= prevNode.val)
                return false;
            prevNode = root;
            root = root.right;
        }
        return true;
    }
}
