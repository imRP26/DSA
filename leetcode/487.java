/*
 * https://leetcode.com/problems/max-consecutive-ones-ii/
 */



/*
 * My Solution, a bit happy with myself!!
 */
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int low = 0, high, n = nums.length, numZeroes = 0, result = 1;
        for (high = 0; high < n; high++) {
            if (nums[high] == 0) {
                while (numZeroes > 0) {
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
  