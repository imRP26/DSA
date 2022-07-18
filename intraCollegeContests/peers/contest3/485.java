/* 
 * Question Link -> https://leetcode.com/problems/max-consecutive-ones/
 */



 // Simple Intuition
 class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0, currLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1)
                result = Math.max(result, ++currLen);
            else
                currLen = 0;
        }
        return result;
    }
}
