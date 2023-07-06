/*
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 */



/*
 * Messy, 2-Pointer Approach!
 */
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int low = 0, high = 0, sum = 0, minLen = Integer.MAX_VALUE;
        while (high < nums.length) {
            sum += nums[high];
            if (sum >= target) {
                minLen = Math.min(minLen, high - low + 1);
                sum -= nums[low++];
                if (sum >= target)
                    minLen = Math.min(minLen, high - low + 1);
                while (low < high && sum - nums[low] >= target) {
                    sum -= nums[low++];
                    minLen = Math.min(minLen, high - low + 1);
                }
            }
            high++;
        }
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }
}



/*
 * Cleaner Approach from LC Official Editorial
 */
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int low = 0, high = 0, sum = 0, res = Integer.MAX_VALUE;
        while (high < nums.length) {
            sum += nums[high];
            while (sum >= target) {
                res = Math.min(res, high - low + 1);
                sum -= nums[low++];
            }
            high++;
        }
        return (res == Integer.MAX_VALUE) ? 0 : res;
    }
}
