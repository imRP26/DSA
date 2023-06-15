/*
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */



/*
 * Definition for a binary tree node.
 */ 
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
 



/*
 * Inorder Traversal using a globally-defined List
 */
class Solution {

    private List<Integer> list = new ArrayList<>();

    private void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++)
            res = Math.min(res, list.get(i) - list.get(i - 1));
        return res;
    }
}



/*
 * Approach 3 of official LC Editorial
 */
class Solution {

    private int res = Integer.MAX_VALUE;
    private TreeNode prevNode;

    private void inorder(TreeNode node) {
        if (node == null)
            return;
        inorder(node.left);
        if (prevNode != null)
            res = Math.min(res, node.val - prevNode.val);
        prevNode = node;
        inorder(node.right);
    }

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return res;
    }
}



/*
 * Iterative Inorder Traversal using a Stack, approach referred from -> 
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/editorial/comments/1928517
 */
class Solution {
    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prevNode = null, currNode = root;
        int minDiff = Integer.MAX_VALUE;
        while (currNode != null || !stack.isEmpty()) {
            if (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            }
            else {
                currNode = stack.pop();
                if (prevNode != null)
                    minDiff = Math.min(minDiff, currNode.val - prevNode.val);
                prevNode = currNode;
                currNode = currNode.right;
            }
        }
        return minDiff;
    }
}
