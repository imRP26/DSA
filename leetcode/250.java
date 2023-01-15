/*
 * https://leetcode.com/problems/count-univalue-subtrees/
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



/*
 * DFS Approach from 
 * https://leetcode.com/problems/count-univalue-subtrees/solutions/246194/count-univalue-subtrees/
 */
class Solution1 {

	int result = 0;
	
	private boolean isUnivalueSubtree(TreeNode root) {
		if (root.left == null && root.right == null) {
			result++;
			return true;
		}
		boolean isUnivalue = true;
		if (root.left != null)
			isUnivalue = isUnivalueSubtree(root.left) && isUnivalue && root.left.val == root.val;
		if (root.right != null)
			isUnivalue = isUnivalueSubtree(root.right) && isUnivalue && root.right.val == root.val;
		if (!isUnivalue)
			return false;
		result++;
		return true;
	}

    public int countUnivalSubtrees(TreeNode root) {
    	if (root == null)
			return 0;
		isUnivalueSubtree(root);
		return result;
	}
}



/*
 * Approach 2 from 
 * https://leetcode.com/problems/count-univalue-subtrees/solutions/246194/count-univalue-subtrees/
 */

 