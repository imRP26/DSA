/*
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
 */



/*
 * Messy, Sliding Window Approach!
 */
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length, low = 0, high = 0, res = 0, num0 = 0;
        while (high < n) {
            if (nums[high] == 1 || ++num0 == 1)
                res = Math.max(res, high - low);
            else {
                while (low < high) {
                    if (nums[low] == 0)
                        break;
                    low++;
                }
                low++;
                num0--;
            }
            high++;
        }
        return res;
    }
}



/*
 * Cleaner Approach from LC Official Editorial
 */
class Solution {
    public int longestSubarray(int[] nums) {
        int zeroCount = 0, longestWindow = 0, low = 0, high = 0;
        while (high < nums.length) {
            zeroCount += (nums[high] == 0) ? 1 : 0;
            while (zeroCount > 1) {
                zeroCount -= (nums[low] == 0) ? 1 : 0;
                low++;
            }
            longestWindow = Math.max(longestWindow, high - low);
            high++;
        }
        return longestWindow;
    }
}



/*
 * DP Approach from -> 
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/solutions/708475/dynamic-programming-approach-o-n-time-o-1-space-with-explanation-c/
 */
class Solution {
    public int longestSubarray(int[] nums) {
        /*
         * dp1[i] = length of the longest subarray ending at nums[i] with all 1s, with 1 deletion used.
         * dp0[i] = length of the longest subarray ending at nums[i] with all 1s, with 0 deletions used.
         * Transition Functions :-
         * (1) if nums[i] == 0:
         *         dp1[i] = dp0[i - 1]
         *         dp0[i] = 0
         * (2) if nums[i] == 1:
         *         dp1[i] = max(1 + dp1[i - 1], dp0[i - 1])
         *         dp0[i] = 1 + dp0[i - 1]
         */
        int dp1 = 0, dp0 = (nums[0] == 1) ? 1 : 0, res = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                dp1 = dp0;
                dp0 = 0;
            }
            else {
                dp1 = Math.max(1 + dp1, dp0);
                dp0 = 1 + dp0;
            }
            res = Math.max(res, dp1);
        }
        return (res == Integer.MIN_VALUE) ? 0 : res;
    }
}
