/*
 * https://leetcode.com/problems/add-one-row-to-tree/
 */



/*
 * Tension, too much tension over DS Assignment 2 - this caused it to take a slight 
 * hint and resulted in taking 53 minutes to solve this!
 * 
 * My own approach using BFS!!
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
class Solution {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1)
            return new TreeNode(val, root, null);
        int currDepth = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode currNode = q.poll();
                if (currDepth == depth - 1) {
                    currNode.left = new TreeNode(val, currNode.left, null);
                    currNode.right = new TreeNode(val, null, currNode.right);
                }
                else {
                    if (currNode.left != null)
                        q.offer(currNode.left);
                    if (currNode.right != null)
                        q.offer(currNode.right);
                }
            }
            if (++currDepth == depth)
                break;
        }
        return root;
    }
}



/*
 * Approach of simple DFS from LC Official Editorial!
 */
class Solution {

    private void insertion(TreeNode root, int val, int depth, int currDepth) {
        if (root == null || currDepth >= depth)
            return;
        if (currDepth == depth - 1) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
            return;
        }
        insertion(root.left, val, depth, currDepth + 1);
        insertion(root.right, val, depth, currDepth + 1);
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1)
            return new TreeNode(val, root, null);
        insertion(root, val, depth, 1);
        return root;
    }
}



/*
 * Approach of Iterative DFS using Stack from LC Official Editorial!
 */
class Solution {

    class Node {
        TreeNode node;
        int depth;
        Node(TreeNode tn, int d) {
            node = tn;
            depth = d;
        }
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1)
            return new TreeNode(val, root, null);
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 1));
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            if (n.node == null)
                continue;
            if (n.depth == depth - 1) {
                n.node.left = new TreeNode(val, n.node.left, null);
                n.node.right = new TreeNode(val, null, n.node.right);
            }
            else {
                stack.push(new Node(n.node.left, n.depth + 1));
                stack.push(new Node(n.node.right, n.depth + 1));
            }
        }
        return root;
    }
}
