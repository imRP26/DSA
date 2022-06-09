/*
 * Question Link -> 
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */



 // 2-Pointers
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1, sum = 0;
        int[] result = new int[2];
        while (low < high) {
            sum = numbers[low] + numbers[high];
            if (sum == target) 
                break;
            if (sum < target)
                low ++;
            else
                high --;
        }
        result[0] = low + 1;
        result[1] = high + 1;
        return result;
    }
}
