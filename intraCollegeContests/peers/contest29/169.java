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



// Inefficient Solution using Divide-and-Conquer
class Solution2 {

    int countInRange(int[] nums, int num, int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (nums[i] == num)
                count++;
        }
        return count;
    }

    int recurse(int[] nums, int low, int high) {
        // Base case - the only element in an array of size 1 is the majority element
        if (low == high)
            return nums[low];
        int mid = low + (high - low) / 2, left = recurse(nums, low, mid), 
            right = recurse(nums, mid + 1, high);
        // if the 2 halves agree on the majority element, return it
        if (left == right)
            return left;
        // otherwise, count each element and return the winner
        int leftCount = countInRange(nums, left, low, high);
        int rightCount = countInRange(nums, right, low, high);
        return leftCount > rightCount ? left : right;
    }

    public int majorityElement(int[] nums) {
        return recurse(nums, 0, nums.length - 1);
    }
}
