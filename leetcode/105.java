/*
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
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
 * Approach from -> 
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/editorial/
 */
class Solution {
	
	int preorderIndex;
	Map<Integer, Integer> inorderIndexMap = new HashMap<>();
	
	private TreeNode helper(int[] preorder, int leftIndex, int rightIndex) {
		if (leftIndex > rightIndex)
			return null;
		int rootValue = preorder[preorderIndex++];
		TreeNode root = new TreeNode(rootValue);
		int inorderIndex = inorderIndexMap.get(rootValue);
		root.left = helper(preorder, leftIndex, inorderIndex - 1);
		root.right = helper(preorder, inorderIndex + 1, rightIndex);
		return root;
	}
	
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
		int inorderIndex = 0, numNodes = inorder.length;
		for (Integer val : inorder)
			inorderIndexMap.put(val, inorderIndex++);
		return helper(preorder, 0, numNodes - 1);
    }
}
