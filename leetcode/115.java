/*
 * https://leetcode.com/problems/distinct-subsequences/
 */



/*
 * Just Refer to Striver's video for all the wonderful approaches and their explanations!
 * https://www.youtube.com/watch?v=nVG7eTiD2bY
 */
class Solution {

    private int len1, len2;
    private long[][] dp;

    /*
     * DP State :-
     * dp[i][j] = Number of distinct subsequences of s2[0 : j] in s1[0 : i].
     * 
     * DP Transitions :-
     * dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j], if s1[i] == s2[j]
     *          = dp[i - 1][j], else
     *
     * Final Answer :-
     * dp[len1 - 1][len2 - 1]
     */

    private long memoization(String s, int i, String t, int j) {
        if (j < 0)
            return 1;
        if (i < 0)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        if (s.charAt(i) == t.charAt(j))
            return dp[i][j] = memoization(s, i - 1, t, j - 1) + memoization(s, i - 1, t, j);
        return dp[i][j] = memoization(s, i - 1, t, j);
    } 

    private long tabulation(String s, String t) {
        for (int j = 0; j <= len2; j++)
            dp[0][j] = 0;
        for (int i = 0; i <= len1; i++)
            dp[i][0] = 1;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[len1][len2];
    }

    private long spaceOptimizedTabulation(String s, String t) {
        long[] prev = new long[len2 + 1], curr = new long[len2 + 1];
        prev[0] = curr[0] = 1;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++)
                curr[j] = (s.charAt(i - 1) == t.charAt(j - 1)) ? prev[j - 1] + prev[j] : prev[j];
            for (int j = 1; j <= len2; j++)
                prev[j] = curr[j];
        }
        return prev[len2];
    }

    private long superSpaceOptimizedTabulation(String s, String t) {
        long[] dp1 = new long[len2 + 1];
        dp1[0] = 1;
        for (int i = 1; i <= len1; i++) {
            for (int j = len2; j >= 1; j--) // think a bit as to why we reverse-traverse here, something along the lines of not modifying curr[], but only prev[]!
                dp1[j] = (s.charAt(i - 1) == t.charAt(j - 1)) ? dp1[j - 1] + dp1[j] : dp1[j];
        }
        return dp1[len2];
    }

    public int numDistinct(String s, String t) {
        len1 = s.length();
        len2 = t.length();
        dp = new long[len1 + 1][len2 + 1];
        for (long[] row : dp)
            Arrays.fill(row, -1);
        //long res = memoization(s, len1 - 1, t, len2 - 1);
        //long res = tabulation(s, t);
        //long res = spaceOptimizedTabulation(s, t);
        long res = superSpaceOptimizedTabulation(s, t);
        return res > Integer.MAX_VALUE ? -1 : (int)res;
    }
}
