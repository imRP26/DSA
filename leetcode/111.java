/*
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */



// Definition for a binary tree node
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
 * Simple Approach of BFS - consider the ordering of cases carefully when you check for 
 * the type of node 
 */
class Solution {
    public int minDepth(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1001);
        int minDepth = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(dummyNode);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null)
                return 0;
            if (node == dummyNode) {
                if (!q.isEmpty())
                    q.offer(dummyNode);
                minDepth++;
                continue;
            }
            if (node.left == null && node.right == null)
                break;
            if (node.left != null)
                q.offer(node.left);
            if (node.right != null)
                q.offer(node.right);
        }
        return minDepth;
    }
}



/*
 * Approach of DFS from -> 
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/solutions/36045/my-4-line-java-solution/
 * This is not a feasible solution though!!!
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftDepth = minDepth(root.left), rightDepth = minDepth(root.right);
        return (leftDepth == 0 || rightDepth == 0) ? 1 + leftDepth + rightDepth : 1 + Math.min(leftDepth, rightDepth);
    }
}
