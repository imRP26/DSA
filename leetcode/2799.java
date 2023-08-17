/*
 * https://leetcode.com/problems/count-complete-subarrays-in-an-array/
 */



/*
 * O(N^2) brute force solution!
 */
class Solution {
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (int num : nums)
            set1.add(num);
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            set2.clear();
            for (int j = i; j < n; j++) {
                set2.add(nums[j]);
                res += (set2.size() == set1.size()) ? 1 : 0;
            }
        }
        return res;
    }
}



/*
 * 
 */
