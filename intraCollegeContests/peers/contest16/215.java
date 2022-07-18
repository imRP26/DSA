import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/kth-largest-element-in-an-array/
 */



// My naive method - TC = O(N log N), SC = O(1)
class Solution1 {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}



// Min-oriented Priority Queue 