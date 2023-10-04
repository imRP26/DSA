/*
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/
 */



/*
 * Recursive Solution Approach from LC Official Editorial!
 */
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        else
            root.left = insertIntoBST(root.left, val);
        return root;
    }
}



/*
 * Iterative Approach from LC Official Editorial!
 */
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = root;
        while (node != null) {
            if (val > node.val) {
                if (node.right == null) {
                    node.right = new TreeNode(val);
                    return root;
                }
                else
                    node = node.right;
            }
            else {
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    return root;
                }
                else
                    node = node.left;
            }
        }
        return new TreeNode(val);
    }
}
