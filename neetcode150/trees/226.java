import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/invert-binary-tree/
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
class Solution1 { // DFS-Based
    
    public void invertNodeChildren(TreeNode root) {
        if (root == null)
            return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertNodeChildren(root.left);
        invertNodeChildren(root.right);
    }
    
    public TreeNode invertTree(TreeNode root) {
        invertNodeChildren(root);
        return root;
    }
}



// Stack-based Solution
class Solution2 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);
        }
        return root;
    }
}



// BFS Solution
class Solution3 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
        return root;
    }
}
