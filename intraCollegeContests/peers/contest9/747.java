/*
 * Question Link -> 
 * https://leetcode.com/problems/largest-number-at-least-twice-of-others/
 */



// 2 passes through the input array - TC = O(n), SC = O(1)
class Solution1 {
    public int dominantIndex(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 0;
        int maxElement = Integer.MIN_VALUE, maxPosition = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxElement) {
                maxElement = nums[i];
                maxPosition = i;
            }
        }
        for (int num : nums) {
            if (num == maxElement)
                continue;
            if (2 * num > maxElement)
                return -1;
        }
        return maxPosition;
    }
}



// Single Pass through the input array
class Solution2 {
    public int dominantIndex(int[] nums) {
        int maxElement = -1, maxIndex = -1, secondMax = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxElement) {
                secondMax = maxElement;
                maxElement = nums[i];
                maxIndex = i;
            }
            else if (nums[i] > secondMax)
                secondMax = nums[i];
        }
        return secondMax * 2 <= maxElement ? maxIndex : -1;
    }
}
