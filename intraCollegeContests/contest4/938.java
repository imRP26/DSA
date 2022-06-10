import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/range-sum-of-bst/
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
// My (naive?) DFS Solution - TC = O(N)
class Solution1 {
    
    int nodeSum = 0;
    
    public void dfs(TreeNode root, int low, int high) {
        if (root == null)
            return;
        if (root.val >= low && root.val <= high)
            nodeSum += root.val;
        if (root.left != null && root.val >= low)
            dfs(root.left, low, high);
        if (root.right != null && root.val <= high)
            dfs(root.right, low, high);
    }
    
    public int rangeSumBST(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return nodeSum;
    }
}



// A smarter recursive solution
class Solution2 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null)
            return 0;
        int sum = 0;
        if (root.val >= low && root.val <= high)
            sum += root.val;
        if (root.val > low)
            sum += rangeSumBST(root.left, low, high);
        if (root.val < high)
            sum += rangeSumBST(root.right, low, high);
        return sum; 
    }
}



// Using Stack
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        Stack<TreeNode> stack = new Stack<>();
        int result = 0;
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (root == null)
                continue;
            if (root.val > low)
                stack.push(root.left);
            if (root.val < high)
                stack.push(root.right);
            if (root.val >= low && root.val <= high)
                result += root.val;
        }
        return result;
    }
}



// Using Deque
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
class Solution3 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        Deque<TreeNode> deque = new LinkedList<>();
        int result = 0;
        deque.addFirst(root); // O(1)
        while (!deque.isEmpty()) {
            root = deque.removeLast(); // O(1)
            if (root == null)
                continue;
            if (root.val > low)
                deque.addFirst(root.left); // O(1)
            if (root.val < high)
                deque.addFirst(root.right); // O(1)
            if (root.val >= low && root.val <= high)
                result += root.val;
        }
        return result;
    }
}
