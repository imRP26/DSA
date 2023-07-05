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
