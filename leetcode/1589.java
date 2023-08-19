/*
 * https://leetcode.com/problems/maximum-sum-obtained-of-any-permutation/
 */



/*
 * Simple concept of Difference Arrays and a wee bit of Sorting!
 */
class Solution {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        long res = 0, mod = (long)1e9 + 7;
        long[] counts = new long[n];
        for (int[] req : requests) {
            counts[req[0]]++;
            if (req[1] + 1 < n)
                counts[req[1] + 1]--;
        }
        for (int i = 1; i < n; i++)
            counts[i] += counts[i - 1];
        Arrays.sort(counts);
        Arrays.sort(nums);
        for (int i = n - 1; i >= 0; i--) {
            res += nums[i] * counts[i];
            res %= mod;
        }
        return (int)res;
    }
}
