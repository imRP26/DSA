import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/contiguous-array/
 */



/*
 * Simple HashMap based Solution
 * Treat 0 as -1 and 1 as itself - the question now changes to finding the 
 * length of the longest subarray with a sum of 0.
 */
class Solution {
    public int findMaxLength(int[] nums) {
        int result = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                sum -= 1;
            else
                sum += 1;
            if (map.containsKey(sum))
                result = Math.max(result, i - map.get(sum));
            else
                map.put(sum, i);
        }
        return result;
    }
}
