/*
 * https://leetcode.com/problems/inorder-successor-in-bst-ii/
 */
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};



class Solution {
    public Node inorderSuccessor(Node node) {
        /*
         * trivial case -> node has a right child and hence its inorder successor is somewhere lower 
         * in the tree.
         */
        if (node.right != null) {
            node = node.right;
            while (node.left != null)
                node = node.left;
            return node;
        }
        /* node doesn't have a right child and hence its inorder successor is somewhere upper 
         * in the tree => the node lies in the left subtree of the ultimate parent and the 
         * actual answer is the ultimate parent!
         */
        while (node.parent != null && node == node.parent.right)
            node = node.parent;
        return node.parent;
    }
}
