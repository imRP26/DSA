/*
 * https://leetcode.com/problems/closest-binary-search-tree-value/
 */



// Simple simulation of computing lower bound using binary search!
class Solution {
    public int closestValue(TreeNode root, double target) {
        int res = (int)1e9 + 1;
        double minDiff = Double.MAX_VALUE;
        while (root != null) {
            double diff = Math.abs((double)root.val - target);
            if (diff < minDiff || (diff == minDiff && root.val < res)) {
                res = root.val;
                minDiff = diff;
            }
            if ((double)root.val >= target) // move to left child
                root = root.left;
            else // move to right child
                root = root.right;
        }
        return res;
    }
}
