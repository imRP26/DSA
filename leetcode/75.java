/*
 * https://leetcode.com/problems/sort-colors/
 */



/*
 * Simplest Counting based Approach
 */
class Solution {
    public void sortColors(int[] nums) {
        int num0 = 0, num1 = 0, num2 = 0;
        for (int n : nums) {
            num0 += (n == 0) ? 1 : 0;
            num1 += (n == 1) ? 1 : 0;
            num2 += (n == 2) ? 1 : 0;
        }
        for (int i = 0; i < nums.length; i++)
            nums[i] = (num0-- > 0) ? 0 : (num1-- > 0) ? 1 : 2;
    }
}
