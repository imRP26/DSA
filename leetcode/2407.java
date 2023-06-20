/*
 * https://leetcode.com/problems/longest-increasing-subsequence-ii/
 */



/*
 * Approach of Segment Tree from -> 
 * https://leetcode.com/problems/longest-increasing-subsequence-ii/solutions/2560010/c-segment-tree-with-illustration-explanation/
 */
class Solution {

    private int sz = (int)1e5 + 5; 
    /*
     * Here, the segment tree is used to keep track of the maximum length of all the 
     * valid numbers that can come before the current number in consideration.
     */
    private int[] segtree = new int[4 * sz];

    private int query(int i, int queryLeft, int queryRight, int left, int right) {
        if (left >= queryLeft && right <= queryRight)
            return segtree[i];
        if (left > queryRight || right < queryLeft)
            return Integer.MIN_VALUE;
        int mid = left + (right - left) / 2;
        return Math.max(query(2 * i + 1, queryLeft, queryRight, left, mid), query(2 * i + 2, queryLeft, queryRight, mid + 1, right));
    }

    private void update(int i, int left, int right, int pos, int val) {
        if (pos < left || pos > right)
            return;
        if (left == right) {
            segtree[i] = Math.max(segtree[i], val);
            return;
        }
        int mid = left + (right - left) / 2;
        update(2 * i + 1, left, mid, pos, val);
        update(2 * i + 2, mid + 1, right, pos, val);
        segtree[i] = Math.max(segtree[2 * i + 1], segtree[2 * i + 2]);
    }

    public int lengthOfLIS(int[] nums, int k) {
        for (int num : nums) {
            int lowerBound = Math.max(0, num - k), currMaxLen = 1 + query(0, lowerBound, num - 1, 0, sz - 1);
            update(0, 0, sz - 1, num, currMaxLen);
        }
        return segtree[0];
    }
}
