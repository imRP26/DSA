/*
 * https://leetcode.com/problems/k-divisible-elements-subarrays/
 */



/*
 * Simple Approach of HashSet from -> 
 * https://leetcode.com/problems/k-divisible-elements-subarrays/solutions/1996325/c-set-clean-and-concise/
 * TC = O(N^2)
 */
class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        int n = nums.length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int j = i; j < n; j++) {
                count += nums[j] % p == 0 ? 1 : 0;
                if (count > k)
                    break;
                sb.append(String.valueOf(nums[j]) + '#');
                set.add(sb.toString());
            }
        }
        return set.size();
    }
}



/*
 * 
 */
