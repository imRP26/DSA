import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/longest-common-subsequence/
 */



// Brute-Force Recursion - exponential time complexity
class Solution1 {

    private int longestCommonSubsequence(String text1, String text2, int i, int j) {
        if (i == text1.length() || j == text2.length())
            return 0;
        if (text1.charAt(i) == text2.charAt(j))
            return 1 + longestCommonSubsequence(text1, text2, i + 1, j + 1);
        return Math.max(longestCommonSubsequence(text1, text2, i + 1, j), 
                        longestCommonSubsequence(text1, text2, i, j + 1));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence(text1, text2, 0, 0);
    }
}



// Memoization
class Solution2 {

    int memoize(String text1, String text2, int[][] dp, int i, int j) {
        if (i < 0 || j < 0)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        if (text1.charAt(i) == text2.charAt(j))
            dp[i][j] = 1 + memoize(text1, text2, dp, i - 1, j - 1);
        else
            dp[i][j] = Math.max(memoize(text1, text2, dp, i - 1, j), memoize(text1, text2, dp, i, j - 1));
        return dp[i][j];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1][len2];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return memoize(text1, text2, dp, len1 - 1, len2 - 1);
    }
}


