/*
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */



/*
 * Approach from -> 
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/solutions/957727/java-time-o-n-space-o-n/
 */
class Solution {
    
    TreeNode findLCA(TreeNode root, Set<Integer> set) {
        if (root == null || set.contains(root.val))
            return root;
        TreeNode left = findLCA(root.left, set), right = findLCA(root.right, set);
        return (left != null && right != null) ? root : (left != null) ? left : right;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<Integer> set = new HashSet<>();
        for (TreeNode node : nodes)
            set.add(node.val);
        return findLCA(root, set);
    }
}



/*
 * Generic LCA Solution!
 */
class Solution {

    private TreeNode lca(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null || node == p || node == q)
            return node;
        TreeNode left = lca(node.left, p, q), right = lca(node.right, p, q);
        return (left != null && right != null) ? node : (left != null) ? left : right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        int n = nodes.length;
        if (n == 1)
            return nodes[0];
        TreeNode res = lca(root, nodes[0], nodes[1]);
        for (int i = 2; i < n; i++)
            res = lca(root, res, nodes[i]);
        return res;
    }
}
