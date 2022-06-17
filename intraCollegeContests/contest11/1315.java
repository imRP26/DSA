import java.util.*;

/*
 * Question Link -> 
 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
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
// Simple simulation of the problem statement - level order traversal
// TC = O(n), SC = O(n)
class Solution1 {
    public int sumEvenGrandparent(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
		int result = 0;
		queue.offer(root);
		while (!queue.isEmpty()) {
			root = queue.poll();
			if (root == null)
				continue;
			if (root.val % 2 == 0) {
				if (root.left != null) {
					if (root.left.left != null)
						result += root.left.left.val;
					if (root.left.right != null)
						result += root.left.right.val;
				}
				if (root.right != null) {
					if (root.right.left != null)
						result += root.right.left.val;
					if (root.right.right != null)
						result += root.right.right.val;
				}
			}
			queue.offer(root.left);
			queue.offer(root.right);
		}
		return result;
    }
}



// DFS Version - TC = O(n), SC = O(n)
class Solution2 {
	
	int result = 0;
	
	public void dfs(TreeNode curr, TreeNode parent, TreeNode grandParent) {
		if (curr == null)
			return;
		if (grandParent != null && grandParent.val % 2 == 0)
			result += curr.val;
		dfs(curr.left, curr, parent);
		dfs(curr.right, curr, parent);
	}
	
    public int sumEvenGrandparent(TreeNode root) {
        dfs(root, null, null);
		return result;
    }
}
