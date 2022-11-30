import java.util.*;


/*
 * Reduction to a classical problem :-
 * Its equivalent to finding any palindromic subsequence of length >= N-K.
 * We can just find the length of the longest palindromic subsequence and verify 
 * the length.
 */
class Solution1 {
    
    public int memoize(String s, int[][] dp, int i, int j) {
        if (i == j)
            return dp[i][j] = 1;
        if (i > j)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = 2 + memoize(s, dp, i + 1, j - 1);
        return dp[i][j] = Math.max(memoize(s, dp, i + 1, j), memoize(s, dp, i, j - 1));
    }
    
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        int x = memoize(s, dp, 0, n - 1);
        return (x >= n - k);
    }
}



/*
 * Bottom-Up Tabulation - see how the indices i & j are ordered
 */
class Solution2 {
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
    }
}
