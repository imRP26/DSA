/*
 * https://leetcode.com/problems/delete-node-in-a-bst/
 */



/*
 * Approach of Recursion from LC Official Editorial!
 */
class TreeNode {
    int val;   
    TreeNode left, right;
    TreeNode() {
    }
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode (int val, TreeNode left, TreeNode right) {
        this.val = val;  
        this.left = left;
        this.right = right;
    }
}


class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (key > root.val)
            root.right = deleteNode(root.right, key);
        else if (key < root.val)
            root.left = deleteNode(root.left, key);
        else {
            if (root.left == null && root.right == null) // leaf node - simply delete
                root = null;
            /* 
             * right child exists - replace current node value with that of the inorder successor and 
             * then proceed ahead with deleting the inorder successor!
             */ 
            else if (root.right != null) {
                TreeNode succ = root.right;
                while (succ.left != null)
                    succ = succ.left;
                root.val = succ.val;
                root.right = deleteNode(root.right, root.val);
            }
            /* 
             * left child exists - replace current node value with that of the inorder predecessor and 
             * then proceed ahead with deleting the inorder predecessor!
             */
            else {
                TreeNode pred = root.left;
                while (pred.right != null)
                    pred = pred.right;
                root.val = pred.val;
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
}
