/*
 * https://leetcode.com/problems/inorder-successor-in-bst/
 */



/*
 * Approach for a generic binary tree from LC Official Editorial!
 */
class Solution {

    private TreeNode prevNode, inorderSuccNode;

    private void inorder(TreeNode node, TreeNode p) {
        if (node == null)
            return;
        inorder(node.left, p);
        if (prevNode == p && inorderSuccNode == null) {
            inorderSuccNode = node;
            return;
        }
        prevNode = node;
        inorder(node.right, p);
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            TreeNode node = p.right;
            while (node.left != null)
                node = node.left;
            inorderSuccNode = node;
        }
        else
            inorder(root, p);
        return inorderSuccNode;
    }
}



/*
 * Approach from LC Official Editorial that utilizes the properties of a BST!
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode inorderSucc = null;
        while (root != null) {
            if (p.val >= root.val)
                root = root.right;
            else {
                inorderSucc = root;
                root = root.left;
            }
        }
        return inorderSucc;
    }
}
