import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/two-sum/
 */



 // Simple HashMap based solution
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i ++) {
            if (! map.containsKey(target - nums[i]))
                map.put(nums[i], i);
            else {
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                break;
            }
        }
        return result;
    }
}
