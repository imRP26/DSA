/*
 * https://leetcode.com/problems/maximize-the-topmost-element-after-k-moves/
 */



/*
 * Bloody Implementation!!
 * https://leetcode.com/problems/maximize-the-topmost-element-after-k-moves/solutions/1844089/simple-java-solution-removing-top-k-1-element-and-comparing-with-kth-elemt/
 */
class Solution {
    public int maximumTop(int[] nums, int k) {
        int n = nums.length, result = -1;
        if (n == 1 && k % 2 == 1)
            return result;
        for (int i = 0; i < Math.min(k - 1, n); i++)
            result = Math.max(result, nums[i]);
        if (k < n)
            result = Math.max(result, nums[k]); // since we need to find the max element at the "TOP"
        return result;
    }
}
