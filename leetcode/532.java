/*
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 */



/*
 * My simple approach of Sorting + HashMaps - the k == 0 wala TC gave it all!
 */
class Solution {
    public int findPairs(int[] nums, int k) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.put(nums[i], i);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            if (map.containsKey(nums[i] + k) && map.get(nums[i] + k) != i)
                res++;
        }
        return res;
    }
}



/*
 * Approach 2 of 2-Pointers from LC Official Editorial!
 */
class Solution {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0, n = nums.length, left = 0, right = 1;
        while (left < n && right < n) {
            int diff = nums[right] - nums[left];
            if (left == right || diff < k)
                right++;
            else if (diff > k)
                left++;
            else {
                res++;
                left++;
                while (left < n && nums[left] == nums[left - 1])
                    left++;
            }
        }
        return res; 
    }
}
