import java.util.*;

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
// Simple LCA finding solution extended to multiple elements
class Solution {
    
    TreeNode findLCA(TreeNode root, Set<Integer> set) {
        if (root == null)
            return root;
        if (set.contains(root.val))
            return root;
        TreeNode leftSubtree = findLCA(root.left, set);
        TreeNode rightSubtree = findLCA(root.right, set);
        if (leftSubtree != null && rightSubtree != null)
            return root;
        return (leftSubtree == null) ? rightSubtree : leftSubtree;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<Integer> set = new HashSet<>();
        for (TreeNode node : nodes)
            set.add(node.val);
        return findLCA(root, set);
    }
}
