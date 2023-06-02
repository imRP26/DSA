/* 
 * https://leetcode.com/problems/minimize-maximum-of-array/
 */



/*
 * Approach of Prefix Sum + Greedy
 * Referenced from Official LC Editorial
 */
class Solution {
    public int minimizeArrayValue(int[] nums) {
        long result = 0, prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            result = Math.max(result, (long)(Math.ceil(prefixSum / (i + 1.0))));
        }
        return (int)result;
    }
}



/*
 * 
 */

