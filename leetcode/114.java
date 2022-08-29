import java.util.*;

/*
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */



// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode() {
    }
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}



/*
 * Recursive Solution 1
 * Worst Case TC = O(N * N) and Best Case TC = O(N * log(N))
 * Basically, TC = O(N * H), where H = height of the tree.
 * Recursive equation for a balanced tree :- T(n) = 2 * T(n / 2) + O(n)
 *                    For an unbalanced tree :- T(n) = T(n - 1) + O(n)
 */
class Solution1 {
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        TreeNode leftSubtree = root.left, rightSubtree = root.right;
        flatten(leftSubtree);
        flatten(rightSubtree);
        root.left = null;
        root.right = leftSubtree;
        TreeNode currNode = root;
        while (currNode.right != null)
            currNode = currNode.right;
        currNode.right = rightSubtree;
    }
}



/*
 * Iterative Solution 1
 * TC = O(N), SC = O(H)
 */
class Solution2 {
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode currNode = stack.pop();
            if (currNode.right != null)
                stack.push(currNode.right);
            if (currNode.left != null)
                stack.push(currNode.left);
            if (!stack.isEmpty())
                currNode.right = stack.peek();
            currNode.left = null;
        }
    }
}



/*
 * Iterative Solution 2
 */
class Solution3 {
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prevNode = null, currNode = null;
        while (!stack.isEmpty()) {
            currNode = stack.pop();
            if (prevNode != null) {
                prevNode.right = currNode;
                prevNode.left = null;
            }
            if (currNode.right != null)
                stack.push(currNode.right);
            if (currNode.left != null)
                stack.push(currNode.left);
            prevNode = currNode;
        }
    }
}



/*
 * Morris Traversal -> SC = O(1), TC = O(N)
 * 1st, locate the last node in the left subtree - the last node of a preorder 
 * tree can be found by moving right as many times as possible from its root.
 * 
 */
class Solution4 {
    public void flatten(TreeNode root) {

    }
}
