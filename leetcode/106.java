/*
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
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
 * Approach 1 from -> 
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/editorial/
 */
class Solution {
	
	private int postorderIndex;
	private Map<Integer, Integer> inorderIndexMap = new HashMap<>();
	
	private TreeNode helper(int leftInorderIndex, int rightInorderIndex, int[] inorder, int[] postorder) {
		if (leftInorderIndex > rightInorderIndex)
			return null;
		int rootValue = postorder[postorderIndex];
		TreeNode root = new TreeNode(rootValue);
		int inorderIndex = inorderIndexMap.get(rootValue);
		postorderIndex--;
		root.right = helper(inorderIndex + 1, rightInorderIndex, inorder, postorder);
		root.left = helper(leftInorderIndex, inorderIndex - 1, inorder, postorder);
		return root;
	}
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int numNodes = inorder.length;
		postorderIndex = numNodes - 1;
		int inorderIndex = 0;
		for (Integer val : inorder)
			inorderIndexMap.put(val, inorderIndex++);
		return helper(0, numNodes - 1, inorder, postorder);
    }
}
