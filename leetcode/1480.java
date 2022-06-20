/*
 * Question Link -> https://leetcode.com/problems/running-sum-of-1d-array/
 */



 // Beginning of Prefix Sum Marathon...
 class Solution {
    public int[] runningSum(int[] nums) {
        int prevSum = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] += prevSum;
            prevSum = nums[i];
        }
        return nums;
    }
}
