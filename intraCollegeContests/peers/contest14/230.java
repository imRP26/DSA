import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/kth-smallest-element-in-a-bst/
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
// My (Naive?) Solution - TC = O(n), SC = O(n)
class Solution1 {
    
    public void inorderDFS(TreeNode root, List<Integer> list) {
        if (root.left != null)
            inorderDFS(root.left, list);
        list.add(root.val);
        if (root.right != null)
            inorderDFS(root.right, list);
    }
    
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorderDFS(root, list);
        return list.get(k - 1);
    }
}



// Divide-And-Conquer + DFS (well, sort of)
class Solution2 { // TC = O(n) best case, O(n ^ 2) worst case; SC = O(1)

    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public int kthSmallest(TreeNode root, int k) {
        // count of nodes in the left subtree of root
        int count = countNodes(root.left);
        if (k <= count)
            return kthSmallest(root.left, k);
        if (k > count + 1) // 1 is counted as current node
            return kthSmallest(root.right, k - count - 1);
        return root.val;
    }
}



// DFS Inorder Iterative Traversal, TC = O(n)
class Solution3 {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (--k == 0)
                break;
            root = root.right;
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }
        return root.val;
    }
}



// Funky Recursive Solution
class Solution4 {

    public int inorderDFS(TreeNode root, int[] k) {
        if (root == null)
            return -1;
        int result = inorderDFS(root.left, k);
        if (result != -1)
            return result;
        if (k[0] == 1)
            return root.val;
        k[0]--;
        return inorderDFS(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        return inorderDFS(root, new int[]{k});
    }
}
