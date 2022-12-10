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

 
class Solution {

    long result = 0, treeSum = 0, subtreeSum;

    long dfs(TreeNode root) {
        if (root == null)
            return 0;
        subtreeSum = root.val + dfs(root.left) + dfs(root.right);
        result = Math.max(result, subtreeSum * (treeSum - subtreeSum));
        return subtreeSum;
    }

    public int maxProduct(TreeNode root) {
        treeSum = dfs(root);
        dfs(root);
        return (int)(result % (1e9 + 7));
    }
}
