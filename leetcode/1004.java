/*
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 */



/*
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 */
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length, low = 0, high, result = k, numZeroes = 0;
        for (high = 0; high < n; high++) {
            if (nums[high] == 0) {
                while (numZeroes == k) {
                    if (nums[low++] == 0)
                        numZeroes--;
                }
                numZeroes++;
            }
            result = Math.max(result, high - low + 1);
        }
        return result;
    }
} 
