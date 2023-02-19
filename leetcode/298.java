/*
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
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
 * My Simple solution using Recursion, but still not satisfied with myself, as I wasn't 
 * fully sure if it would work or not
 * Approach of Top-Down DFS!!
 */
class Solution1 {

    int result = 0;

    private int dfs(TreeNode root, int len) {
        result = Math.max(result, len);
        int leftLength = 0, rightLength = 0;
        if (root.left != null) {
            if (root.left.val == root.val + 1)
                leftLength = dfs(root.left, len + 1);
            else
                leftLength = dfs(root.left, 1);
        }
        if (root.right != null) {
            if (root.right.val == root.val + 1)
                rightLength = dfs(root.right, len + 1);
            else
                rightLength = dfs(root.right, 1);
        }
        return 1 + Math.max(leftLength, rightLength);
    }

    public int longestConsecutive(TreeNode root) {
        dfs(root, 1);
        return result;
    }
}



/*
 * Another approach (cleaner) using the global variable, but Top-Down DFS!!
 */
class Solution2 {

	private int maxLength = 0;

	private void dfs(TreeNode root, TreeNode parent, int length) {
		if (root == null)
			return;
		length = (parent != null && root.val == parent.val + 1) ? length + 1 : 1;
		maxLength = Math.max(maxLength, length);
		dfs(root.left, root, length);
		dfs(root.right, root, length);
	}

	public int longestConsecutive(TreeNode root) {
		dfs(root, null, 0);
		return maxLength;
	}
}



/*
 * Top-Down DFS without the use of a global variable
 */
class Solution3 {

	private int dfs(TreeNode root, TreeNode parent, int length) {
		if (root == null)
			return length;
		length = (parent != null && root.val == parent.val + 1) ? length + 1 : 1;
		return Math.max(length, Math.max(dfs(root.left, root, length), dfs(root.right, root, length)));
	}

	public int longestConsecutive(TreeNode root) {
		return dfs(root, null, 0);
	}
}



/*
 * Approach of Bottom-Up DFS from -> 
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/solutions/127812/binary-tree-longest-consecutive-sequence/
 */
class Solution4 {

	private int maxLength = 0;

	private int dfs(TreeNode root) {
		if (root == null)
			return 0;
		int leftLength = dfs(root.left) + 1, rightLength = dfs(root.right) + 1;
		if (root.left != null && root.left.val != root.val + 1)
			leftLength = 1;
		if (root.right != null && root.right.val != root.val + 1)
			rightLength = 1;
		int length = Math.max(leftLength, rightLength);
		maxLength = Math.max(maxLength, length);
		return length;
	}

	public int longestConsecutive(TreeNode root) {
		dfs(root);
		return maxLength;
	}
}
