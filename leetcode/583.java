/*
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 */



/*
 * Simple Approach of DP by Memoization + LCS wala Memoization DP
 */
class Solution {

    private int len1, len2;
    private int[][] dp;

    private int memoization(int i1, String w1, int i2, String w2) {
        if (i1 == len1)
            return len2 - i2;
        if (i2 == len2)
            return len1 - i1;
        if (dp[i1][i2] != -1)
            return dp[i1][i2];
        if (w1.charAt(i1) == w2.charAt(i2))
            return dp[i1][i2] = memoization(i1 + 1, w1, i2 + 1, w2);
        return dp[i1][i2] = 1 + Math.min(memoization(i1 + 1, w1, i2, w2), 
                                         memoization(i1, w1, i2 + 1, w2)); 
    }

    private int lcsMemoization(int i1, String w1, int i2, String w2) {
        if (i1 == len1 || i2 == len2)
            return 0;
        if (dp[i1][i2] != -1)
            return dp[i1][i2];
        if (w1.charAt(i1) == w2.charAt(i2))
            return dp[i1][i2] = 1 + lcsMemoization(i1 + 1, w1, i2 + 1, w2);
        return dp[i1][i2] = Math.max(lcsMemoization(i1 + 1, w1, i2, w2), 
                                     lcsMemoization(i1, w1, i2 + 1, w2));
    }

    public int minDistance(String word1, String word2) {
        len1 = word1.length();
        len2 = word2.length();
        dp = new int[len1][len2];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        //return memoization(0, word1, 0, word2);
        return len1 + len2 - 2 * lcsMemoization(0, word1, 0, word2);
    }
}



/*
 * Approach of Bottom-Up LCS DP from LC Official Editorial!
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return len1 + len2 - 2 * dp[len1][len2];
    }
}



/*
 * Ye wala approach isi file k Approach 1 ka chota modification hai bas!
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = len1; i >= 0; i--) {
            for (int j = len2; j >= 0; j--) {
                if (i == len1)
                    dp[i][j] = len2 - j;
                else if (j == len2)
                    dp[i][j] = len1 - i;
                else if (word1.charAt(i) == word2.charAt(j))
                    dp[i][j] = dp[i + 1][j + 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }
}



/*
 * Using O(linear space complexity)
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[2][len2 + 1];
        for (int i = len1; i >= 0; i--) {
            for (int j = len2; j >= 0; j--) {
                if (i == len1)
                    dp[i % 2][j] = len2 - j;
                else if (j == len2)
                    dp[i % 2][j] = len1 - i;
                else if (word1.charAt(i) == word2.charAt(j))
                    dp[i % 2][j] = dp[1 - (i % 2)][j + 1];
                else
                    dp[i % 2][j] = 1 + Math.min(dp[1 - (i % 2)][j], dp[i % 2][j + 1]);
            }
        }
        return dp[0][0];
    }
}
