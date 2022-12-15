import java.util.*;

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
 * DFS + DP - Super intuitive solution
 */
class Solution {

    void initializeMap(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null)
            return;
        if (!map.containsKey(root))
            map.put(root, -1);
        initializeMap(root.left, map);
        initializeMap(root.right, map);
    }

    int dfs(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null)
            return 0;
        int val = map.get(root);
        if (val != -1)
            return val;
        int leftVal = 0, rightVal = 0;
        if (root.left != null)
            leftVal += dfs(root.left.left, map) + dfs(root.left.right, map);
        if (root.right != null)
            rightVal += dfs(root.right.left, map) + dfs(root.right.right, map);
        val = Math.max(dfs(root.left, map) + dfs(root.right, map), leftVal + rightVal + root.val);
        map.put(root, val);
        return val;
    }

    public int rob(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        initializeMap(root, map);
        return dfs(root, map);
    }
}



/*
 * Referenced from :- 
 * https://leetcode.com/problems/house-robber-iii/solutions/79330/step-by-step-tackling-of-the-problem/
 * rob(root) = this returns array of 2 elements
 * 1st element -> denotes the maximum amount of money that can be robbed, if root is not robbed.
 * 2nd element -> denotes the maximum amount of money that can be robbed, if root is robbed.
 */
class Solution2 {

    int[] helper(TreeNode root) {
        if (root == null)
            return new int[2];
        int[] leftSubtree = helper(root.left);
        int[] rightSubtree = helper(root.right);
        int[] result = new int[2];
        result[0] = Math.max(leftSubtree[0], leftSubtree[1]) + Math.max(rightSubtree[0], rightSubtree[1]);
        result[1] = root.val + leftSubtree[0] + rightSubtree[0];
        return result;
    }

    public int rob(TreeNode root) {
        int[] result = helper(root);
        return Math.max(result[0], result[1]);
    }
}
