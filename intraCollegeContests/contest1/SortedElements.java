import java.util.*;

/*
 * Question Link -> https://binarysearch.com/problems/Sorted-Elements
 */



// Simple Sorting, Simulation
class Solution {
    public int solve(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == arr[i])
                result++;
        }
        return result;
    }
}
