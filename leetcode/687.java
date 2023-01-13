/*
 * https://leetcode.com/problems/longest-univalue-path/
 */



/*
 * My Naive Solution, but AC nonetheless!! Need to think and visualize a bit more...
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
class Solution1 {

    int result = 1;

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int leftPath = dfs(root.left), rightPath = dfs(root.right), longestPath = 1;
        if (root.left != null && root.val == root.left.val)
            longestPath = Math.max(longestPath, leftPath + 1);
        if (root.right != null && root.val == root.right.val)
            longestPath = Math.max(longestPath, rightPath + 1);
        if (root.left != null && root.right != null && root.val == root.left.val && root.val == root.right.val)
            result = Math.max(result, 1 + leftPath + rightPath);
        result = Math.max(result, longestPath);
        return longestPath;
    }

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return result - 1;
    }
} 



/*
 * Simpler solution from 
 * https://leetcode.com/problems/longest-univalue-path/solutions/127406/longest-univalue-path/
 */
class Solution2 {
	
	int result = 0;
	
	private int dfs(TreeNode root) {
		if (root == null)
			return 0;
		int leftPathLength = dfs(root.left), rightPathLength = dfs(root.right);
		int crossLeftPath = 0, crossRightPath = 0;
		if (root.left != null && root.left.val == root.val)
			crossLeftPath += leftPathLength + 1;
		if (root.right != null && root.right.val == root.val)
			crossRightPath += rightPathLength + 1;
		result = Math.max(result, crossLeftPath + crossRightPath);
		return Math.max(crossLeftPath, crossRightPath);
	}
	
	public int longestUnivaluePath(TreeNode root) {
		dfs(root);
		return result;
	}
}
