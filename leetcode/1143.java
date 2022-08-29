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
