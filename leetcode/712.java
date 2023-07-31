/*
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/
 */



/*
 * Simple Approach of DP by Memoization!
 */
class Solution {

    private int len1, len2;
    private int[][] dp;

    /*
     * DP State -> dp[i][j] = lowest ASCII sum to make s1[i:] and s2[j:] equal.
     * 
     * DP TRANSITIONS -> 
     * if (s1[i] == s2[j]) dp[i][j] = memoization(s1, s2, i + 1, j + 1);
     * dp[i][j] = Math.min(memoization(s1, s2, i + 1, j + 1), 
     *                     Math.min(memoization(s1, s2, i + 1, j), memoization(s1, s2, i, j + 1)))
     *  
     * FINAL ANSWER -> dp[0][0]
     */

    private int memoization(String s1, String s2, int i1, int i2) {
        if (i1 == len1 && i2 == len2)
            return 0;
        if (i1 == len1)
            return (int)(s2.charAt(i2)) + memoization(s1, s2, i1, i2 + 1);
        if (i2 == len2)
            return (int)(s1.charAt(i1)) + memoization(s1, s2, i1 + 1, i2);
        if (dp[i1][i2] != -1)
            return dp[i1][i2];
        int ans = Integer.MAX_VALUE;
        if (s1.charAt(i1) == s2.charAt(i2))
            ans = Math.min(ans, memoization(s1, s2, i1 + 1, i2 + 1));
        ans = Math.min(ans, Math.min((int)(s1.charAt(i1)) + memoization(s1, s2, i1 + 1, i2), (int)(s2.charAt(i2)) + memoization(s1, s2, i1, i2 + 1)));
        return dp[i1][i2] = ans;
    }

    public int minimumDeleteSum(String s1, String s2) {
        len1 = s1.length();
        len2 = s2.length();
        dp = new int[len1][len2];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return memoization(s1, s2, 0, 0);
    }
}



/*
 * Approach of Bottom-Up DP from LC Official Editorial!
 */
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        // when s2 is empty, so j == 0
        for (int i = 1; i <= len1; i++)
            dp[i][0] = (int)(s1.charAt(i - 1)) + dp[i - 1][0];
        // when s1 is empty, so i == 0
        for (int j = 1; j <= len2; j++)
            dp[0][j] = (int)(s2.charAt(j - 1)) + dp[0][j - 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min((int)(s1.charAt(i - 1)) + dp[i - 1][j], 
                                        (int)(s2.charAt(j - 1)) + dp[i][j - 1]);
            }
        }
        return dp[len1][len2];
    }
}



/*
 * Space-Optimized Approach of Bottom-Up DP from LC Official Editorial! - ye abhi thoda aur samajhna baaki 
 * reh gaya!
 */
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 < len2)
            return minimumDeleteSum(s2, s1);
        int[] currRow = new int[len2 + 1];
        for (int j = 1; j <= len2; j++) // i == 0
            currRow[j] = currRow[j - 1] + s2.charAt(j - 1);
        for (int i = 1; i <= len1; i++) {
            int diagVal = currRow[0];
            currRow[0] += s1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                int ans = 0;
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    ans = diagVal;
                else
                    ans = Math.min(s1.charAt(i - 1) + currRow[j], s2.charAt(j - 1) + currRow[j - 1]);
                diagVal = currRow[j];
                currRow[j] = ans;                
            }
        }
        return currRow[len2];
    }
}
