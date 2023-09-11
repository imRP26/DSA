/*
 * https://leetcode.com/problems/binary-subarrays-with-sum/
 */



/*
 * Approach similar to Subarray sums of value k
 */
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int n : nums) {
            sum += n;
            res += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}



/*
 * Approach from Lee's solution having O(1) SC -> 
 * https://leetcode.com/problems/binary-subarrays-with-sum/solutions/186683/c-java-python-sliding-window-o-1-space/
 */
class Solution {

    private int numAtmostSums(int[] nums, int goal) {
        if (goal < 0)
            return 0;
        int res = 0, n = nums.length, low = 0, high = 0;
        while (high < n) {
            goal -= nums[high];
            while (goal < 0)
                goal += nums[low++];
            res += high - low + 1;
            high++;
        }
        return res;
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        return numAtmostSums(nums, goal) - numAtmostSums(nums, goal - 1);
    }
}
