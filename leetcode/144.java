/*
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 */



/*
 * Stack-based Solution
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.val);
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
        return result;
    }
}



/*
 * Approach of Morris Traversal from 
 * https://leetcode.com/problems/binary-tree-preorder-traversal/solutions/2918429/binary-tree-preorder-traversal/
 */
class Solution2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode currNode = root, lastNode;
        while (currNode != null) {
            if (currNode.left == null) {
                result.add(currNode.val);
                currNode = currNode.right;
            }
            else {
                lastNode = currNode.left;
                while (lastNode.right != null && lastNode.right != currNode)
                    lastNode = lastNode.right;
                if (lastNode.right == null) {
                    result.add(currNode.val);
                    lastNode.right = currNode;
                    currNode = currNode.left;
                }
                else {
                    lastNode.right = null;
                    currNode = currNode.right;
                }
            }
        }
        return result;
    }
}
