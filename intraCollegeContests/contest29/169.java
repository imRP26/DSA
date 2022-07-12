/*
 * Question Link -> https://leetcode.com/problems/majority-element/
 */



/*
 * Boyer-Moore majority vote algorithm
 * Refer to Striver's Youtube video for understanding
 */
class Solution {
    public int majorityElement(int[] nums) {
        int result = 0, count = 0;
        for (int n : nums) {
            if (count == 0)
                result = n;
            if (n == result)
                count++;
            else
                count--;
        }
        return result;
    }
}
