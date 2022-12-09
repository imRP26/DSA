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

/*
 * Pure DFS and Simulation
 */
class Solution1 {
	
	int result;

	private void helper(TreeNode node, int currMax, int currMin) {
		if (node == null)
			return;
		result = Math.max(result, Math.max(Math.abs(currMax - node.val), Math.abs(currMin - node.val)));
		currMax = Math.max(currMax, node.val);
		currMin = Math.min(currMin, node.val);
		helper(node.left, currMax, currMin);
		helper(node.right, currMax, currMin);
	}

	public int maxAncestorDiff(TreeNode root) {
		if (root == null)
			return 0;
		helper(root, root.val, root.val);
		return result;
	}
}


/*
 * Referred from the official LC solution
 * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/solutions/895566/maximum-difference-between-node-and-ancestor/
 */
class Solution2 {

    private int helper(TreeNode root, int currMax, int currMin) {
        if (root == null)
            return currMax - currMin;
        currMax = Math.max(currMax, root.val);
        currMin = Math.min(currMin, root.val);
        return Math.max(helper(root.left, currMax, currMin), helper(root.right, currMax, currMin));
    }

    public int maxAncestorDiff(TreeNode root) {
        if (root == null)
            return 0;
        return helper(root, root.val, root.val);
    }
}
