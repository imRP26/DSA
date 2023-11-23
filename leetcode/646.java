/* 
 * https://leetcode.com/problems/maximum-length-of-pair-chain/
 */



/*
 * My Approach of Sorting + DP
 */
class Solution {
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length, res = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        Arrays.sort(pairs, (a, b) -> (a[1] == b[1]) ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0])
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
