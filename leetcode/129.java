/*
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
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
// My Naive version
class Solution1 {
    
    int result = 0;
    
    void dfs(TreeNode root, String num) {
        if (root.left == null && root.right == null) {
            result += Integer.parseInt(num + String.valueOf(root.val));
            return;
        }
        if (root.left != null)
            dfs(root.left, num + String.valueOf(root.val));
        if (root.right != null)
            dfs(root.right, num + String.valueOf(root.val));
    }
    
    public int sumNumbers(TreeNode root) {
        dfs(root, "");
        return result;
    }
}



// Better recursive solution
class Solution2 {

    int computeSum(TreeNode root, int sum) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return sum * 10 + root.val;
        return computeSum(root.left, sum * 10 + root.val) + 
               computeSum(root.right, sum * 10 + root.val);
    }

    public int sumNumbers(TreeNode root) {
        return computeSum(root, 0);
    }
}
