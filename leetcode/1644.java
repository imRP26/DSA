/*
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 */



/*
 * Definition of a binary tree node
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { 
        val = x; 
    }
}



/*
 * Approach from LC Official Editorial!
 */
class Solution {

    private TreeNode lca(TreeNode node, TreeNode p, TreeNode q) {
        /*
         * So this is the stopping condition, wherein if we find any 1 of the nodes, then we return back!
         * Its implicitly assumed finally that if 'q' is absent from the remaining tree, i.e., the part of 
         * the tree excluding the subtree of 'p', then it must be present within the subtree of 'p', 
         * a dangerous assumption here since p and / or q can be altogether absent in the tree.
         */
        if (node == null || node == p || node == q)
            return node;
        TreeNode node1 = lca(node.left, p, q), node2 = lca(node.right, p, q);
        if (node1 != null && node2 != null)
            return node;
        return node1 == null ? node2 : node1;
    }

    private boolean dfs(TreeNode node, TreeNode target) {
        if (node == null)
            return false;
        if (node == target)
            return true;
        return dfs(node.left, target) || dfs(node.right, target);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = lca(root, p, q);
        if (res != p && res != q)
            return res;
        if (res == p && dfs(p, q))
            return res;
        if (res == q && dfs(q, p))
            return res;
        return null;
    }
}



/*
 * Approach 2 from LC Editorial, not an intuitive approach though! :P
 */
class Solution {

    private boolean nodesFound = false;

    private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null)
            return null;
        TreeNode left = dfs(node.left, p, q), right = dfs(node.right, p, q);
        int val = (node == p || node == q) ? 1 : 0;
        val += (left != null && right != null) ? 2 : (left != null || right != null) ? 1 : 0;
        nodesFound = (val >= 2) ? true : nodesFound;
        return ((left != null && right != null) || (node == p || node == q)) ? node : (left != null) ? left : right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = dfs(root, p, q);
        return nodesFound ? res : null;
    }
}
