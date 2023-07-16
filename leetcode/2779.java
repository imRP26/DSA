/*
 * https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/
 */



/*
 * Solution using the Difference Array Technique, for reference watch Priyansh's video!
 */
class Solution {
    public int maximumBeauty(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int x : nums) {
            map.put(x - k, map.getOrDefault(x - k, 0) + 1);
            map.put(x + k + 1, map.getOrDefault(x + k + 1, 0) - 1);
        }
        int res = 1, val = 0;
        for (int x : map.keySet()) {
            val += map.get(x);
            res = Math.max(res, val);
        }
        return res;
    }
}



/*
 * Approach of 2 Pointers from -> 
 * https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/solutions/3771337/sort-two-pointer-diff-less-than-2-k-very-simple-and-easy-to-understand/
 */
class Solution {
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums); // In the end, we need to consider subsequences, so order of elements doesn't matter
        int n = nums.length, low = 0, high = 0, res = 0;
        while (high < n) {
            if (nums[high] - nums[low] <= 2 * k)
                res = Math.max(res, high - low + 1);
            else {
                while (nums[high] - nums[low] > 2 * k)
                    low++;
            }
            high++;
        }
        return res;
    }
}
