/*
 * Question Link -> https://leetcode.com/problems/sum-of-left-leaves/
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
// My naive solution
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
    
    int leftSum = 0;
    
    public void dfs(TreeNode root, boolean isLeftChild) {
        if (root == null)
            return;
        if (isLeftChild && root.left == null && root.right == null)
            leftSum += root.val;
        if (root.left != null)
            dfs(root.left, true);
        if (root.right != null)
            dfs(root.right, false);
    }
    
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root, false);
        return leftSum;
    }
}



// Recursion based solution not using any global variables
class Solution2 {

    public int dfs(TreeNode root, boolean isLeftChild) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null && isLeftChild)
            return root.val;
        return dfs(root.left, true) + dfs(root.right, false);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }
}



// Recursion based solution not involving any extra function
class Solution3 {
    public int sumOfLeaves(TreeNode root) {
        if (root == null)
            return 0;
        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null)
            sum += root.left.val;
        else
            sum += sumOfLeaves(root.left);
        sum += sumOfLeaves(root.right);
        return sum;
    }
}



// Iterative Solution
class Solution4 {
    public int sumOfLeftLeaves(TreeNode root) {
        int result = 0;
        if (root == null)
            return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            root = stack.pop();
            if (root == null)
                continue;
            if (root.left != null && root.left.left == null && root.left.right == null)
                result += root.left.val;
            else
                stack.push(root.left);
            stack.push(root.right);
        }
        return result;
    }
}
