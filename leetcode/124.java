import java.util.*;

/*
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// My Naive DFS
class Solution1 {
    
    int result = Integer.MIN_VALUE;
    
    // returns the maximum sum available at a particular node of the tree
    int computeSum(TreeNode root, int sum) {
        if (root == null)
            return 0;
        // maximum sum obtained from left-subtree
        int leftSum = computeSum(root.left, sum);
        // maximum sum obtained from right-subtree
        int rightSum = computeSum(root.right, sum);
        /*
         * Whether we want to take into consideration of a required path 
         * consisting of only the root or including the root alongwith the 
         * value obtained from the left or right subtrees
         */
        int val = Math.max(root.val, root.val + Math.max(leftSum, rightSum));
        /*
         * Whether we want to take into consideration of a required path consisting 
         * of the above choice or a path going from this current node's left subtree 
         * into its right subtree alongwith the node value
         */
        result = Math.max(result, Math.max(val, root.val + leftSum + rightSum));
        return val;
    }
    
    public int maxPathSum(TreeNode root) {
        computeSum(root, 0);
        return result;
    }
} 



// An awesomely concise recursive solution
class Solution2 {

    int result = Integer.MIN_VALUE;

    int dfs(TreeNode root, int sum) {
        if (root == null)
            return 0;
        int leftSum = Math.max(0, dfs(root.left, 0));
        int rightSum = Math.max(0, dfs(root.right, 0));
        result = Math.max(result, leftSum + rightSum + root.val);
        return root.val + Math.max(leftSum, rightSum);
    }

    public int maxPathSum(TreeNode root) {
        dfs(root, 0);
        return result;
    }
}