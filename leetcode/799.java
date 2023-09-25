/*
 * https://leetcode.com/problems/champagne-tower/
 */



/*
 * Awesome AdHoc Approach from LC Official Editorial!
 */
class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[102][102];
        dp[0][0] = (double)poured;
        for (int row = 0; row <= query_row; row++) {
            for (int col = 0; col <= row; col++) {
                double q = (dp[row][col] - 1.0) / 2.0;
                if (q > 0) {
                    dp[row + 1][col] += q;
                    dp[row + 1][col + 1] += q;
                }
            }
        }
        return Math.min(1, dp[query_row][query_glass]);
    }
}
