import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/longest-palindromic-subsequence/
 */



// Memoization using a 2-D DP array -> TC = O(n ^ 2), SC = O(n ^ 2)
class Solution1 {
    
    public int memoization(String s, int[][] dp, int low, int high) {
        if (low > high)
            return 0;
        if (dp[low][high] != -1)
            return dp[low][high];
        if (low == high)
            return dp[low][high] = 1;
        if (s.charAt(low) == s.charAt(high))
            return dp[low][high] = 2 + memoization(s, dp, low + 1, high - 1);
        return dp[low][high] = Math.max(memoization(s, dp, low + 1, high), 
                                        memoization(s, dp, low, high - 1));
    }
    
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int[] temp : dp)
            Arrays.fill(temp, -1);
        return memoization(s, dp, 0, len - 1);
    }
}



// Same solution as above but with a different form of input, good for practice
class Solution2 {

    public int memoization(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null)
            return memo[i][j];
        if (i > j)
            return 0;
        if (i == j)
            return memo[i][j] = 1;
        if (s.charAt(i) == s.charAt(j))
            return memo[i][j] = 2 + memoization(s, i + 1, j - 1, memo);
        return memo[i][j] = Math.max(memoization(s, i + 1, j, memo), 
                                     memoization(s, i, j - 1, memo));
    }

    public int longestPalindromeSubseq(String s) {
        return memoization(s, 0, s.length() - 1, 
                           new Integer[s.length()][s.length()]);
    }
}



// Bottom-Up DP using 2-D DP array
class Solution3 {
	public int longestPalindromeSubseq(String s) {
		int sLen = s.length();
		int[][] dp = new int[sLen][sLen];
		// Building the table now
        // len = length of the string under consideration
		for (int len = 1; len <= sLen; len++) {
			for (int i = 0; i <= sLen - len; i++) {
				int j = i + len - 1;
				if (i == j) // strings of length 1 are palindrome by default
					dp[i][j] = 1;
				if (s.charAt(i) == s.charAt(j))
					dp[i][j] = dp[i + 1][j - 1] + 2;
				else
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
			}
		}
		return dp[0][sLen - 1];
	}
}



// Bottom-Up DP using another approach
class Solution4 {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][len - 1];
    }
}



/*
 * The standard algorithm for computing a longest palindromic subsequence (LPS) 
 * of a given string S involves 1st computing a longest common subsequence (LCS) 
 * of S and its reverse S'. Often, this gives a correct LPS right away. 
 * e.g., it may be verified that "alala", "afafa", "aflfa" and "alfla" 
 * are all LCSes of "alfalfa" and its reverse "aflafla". 
 * However, this isn't always the case; e.g., "afala" and "alafa" are also LCSes 
 * of "alfalfa" and its reverse, yet neither is palindromic. 
 * Thus, any LPS of a string is an LCS of the string and its reverse, but the 
 * converse is false.
 */
class Solution5 {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len + 1][len + 1];
        String reverse = new StringBuilder(s).reverse().toString();
        for (int i = 1; i <= len; i++) {
            // for (int j = len; j >= 1; j--)
            for (int j = 1; j <= len; j++) {
                if (s.charAt(i - 1) == reverse.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[len][len];
    }
}



/*
 * Space Optimized Bottom-Up DP Approach :-
 * dp[i][j] only depends on dp[i+1][j-1] (down-left), dp[i+1][j] (down) and 
 * dp[i][j-1] (left). 
 * So if we reduce dp[n][m] to dp[m], it means that for dp[j], its down is 
 * itself, its left is dp[j-1]. Its down-left is a little tricky. As its 
 * down-left dp[i+1][j-1] is now dp[j-1], so we need to preserve it before 
 * updating to dp[j-1].
 */
