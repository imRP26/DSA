/*
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
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
 * Simple Approach of BFS
 */
class Solution {
    public int maxLevelSum(TreeNode root) {
        int maxSum = Integer.MIN_VALUE, sum = 0, level = 1, result = 0;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode dummy = new TreeNode((int)1e6);
        q.offer(root);
        q.offer(dummy);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == dummy) {
                if (sum > maxSum) {
                    maxSum = sum;
                    result = level;
                }
                level++;
                sum = 0;
                if (!q.isEmpty())
                    q.offer(dummy);
                continue;
            }
            sum += node.val;
            if (node.left != null)
                q.offer(node.left);
            if (node.right != null)
                q.offer(node.right);
        }
        return result;
    }
}



/*
 * Approach of DFS from Official LC Editorial!
 */
class Solution {

    private List<Integer> levelSum = new ArrayList<>();

    private void dfs(TreeNode root, int level) {
        if (root == null)
            return;
        if (level == levelSum.size())
            levelSum.add(root.val);
        else
            levelSum.set(level, levelSum.get(level) + root.val);
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    public int maxLevelSum(TreeNode root) {
        dfs(root, 0);
        int maxSum = Integer.MIN_VALUE, res = 0;
        for (int i = 0; i < levelSum.size(); i++) {
            if (levelSum.get(i) > maxSum) {
                maxSum = levelSum.get(i);
                res = i + 1;
            }
        }
        return res;
    }
}
