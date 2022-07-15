import java.util.*;

/*
 * https://leetcode.com/problems/balanced-binary-tree/
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
    
    boolean result = true;

    public void dfs(TreeNode root, Map<TreeNode, Integer> map) {
        if (!result)
            return;
        if (root.left == null && root.right == null) {
            map.put(root, 1);
            return;
        }
        int leftHeight = 0, rightHeight = 0;
        if (root.left != null) {
            dfs(root.left, map);
            leftHeight = map.get(root.left);
        }
        if (root.right != null) {
            dfs(root.right, map);
            rightHeight = map.get(root.right);
        }
        map.put(root, Math.max(leftHeight, rightHeight) + 1);
        if (Math.abs(leftHeight - rightHeight) > 1)
            result = false;
    }
    
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        Map<TreeNode, Integer> map = new HashMap<>();
        dfs(root, map);
        return result;
    }
}



/*
 * Solution according to the definition of a strictly balanced binary tree
 * TC = O(N ^ 2)
 */ 
class Solution2 {

    int findDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(findDepth(root.left), findDepth(root.right));
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int diff = Math.abs(findDepth(root.left) - findDepth(root.right));
        return diff <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
}



// Better O(N) wala version
class Solution3 {

    int helper(TreeNode root) {
        if (root == null)
            return 0;
        int left = helper(root.left), right = helper(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return 1 + Math.max(left, right);
    }

    public boolean isBalanced(TreeNode root) {
        return helper(root) != -1;
    }
}
