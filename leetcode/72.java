/*
 * https://leetcode.com/problems/edit-distance/
 */



/*
 * Refer the approaches from Striver's video.
 */ 
class Solution {

    private int memoization(int i1, int i2, int[][] dp, String s1, String s2) {
        if (i1 < 0)
            return 1 + i2;
        if (i2 < 0)
            return 1 + i1;
        if (dp[i1][i2] != -1)
            return dp[i1][i2];
        if (s1.charAt(i1) == s2.charAt(i2))
            return dp[i1][i2] = memoization(i1 - 1, i2 - 1, dp, s1, s2);
        int insertionCost = memoization(i1, i2 - 1, dp, s1, s2);
        int deletionCost = memoization(i1 - 1, i2, dp, s1, s2);
        int replacementCost = memoization(i1 - 1, i2 - 1, dp, s1, s2);
        return dp[i1][i2] = 1 + Math.min(Math.min(insertionCost, deletionCost), replacementCost);
    }

    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        /*
        int[][] dp = new int[len1 + 1][len2 + 1];
        //for (int[] row : dp)
        //    Arrays.fill(row, -1);
        //return memoization(len1 - 1, len2 - 1, dp, word1, word2);
         for (int i = 0; i <= len1; i++) {
             for (int j = 0; j <= len2; j++) {
                 if (i == 0 || j == 0)
                    dp[i][j] = i + j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
             }
         }
         */
         int[][] dp = new int[2][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0)
                    dp[i % 2][j] = i + j;
                else if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i % 2][j] = dp[1 - (i % 2)][j - 1];
                else
                    dp[i % 2][j] = 1 + Math.min(dp[i % 2][j - 1], Math.min(dp[1 - (i % 2)][j], dp[1 - (i % 2)][j - 1]));
            }
        }
         return dp[len1][len2];
    }
}
