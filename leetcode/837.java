/* 
 * https://leetcode.com/problems/new-21-game/
 */



/*
 * Refer to this video for the approach -> 
 * https://www.youtube.com/watch?v=eK5hY45Ma3s
 * This LC Editorial -> 
 * https://leetcode.com/problems/new-21-game/solutions/1253071/easy-to-understand-java-solution-with-probability-calculation-examples/
 */
/*
 * n = 10, k = 7, maxPts = 5
 */
class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (n >= k - 1 + maxPts)
            return 1.0;
        double[] dp = new double[n + 1];
        dp[0] = 1.0;
        double prev = 0.0;
        for (int i = 1; i <= k; i++) {
            prev += dp[i - 1] - ((i - maxPts - 1 >= 0) ? dp[i - maxPts - 1] : 0);
            dp[i] = prev * 1 / (maxPts * 1.0);
        }
        double result = dp[k];
        for (int i = k + 1; i <= n; i++) {
            prev -= (i - 1 - maxPts >= 0) ? dp[i - 1 - maxPts] : 0;
            dp[i] = prev * 1 / (maxPts * 1.0);
            result += dp[i];
        }
        return result;
    }
}
