/*
 * https://leetcode.com/problems/interleaving-string/
 */



/*
 * Firstly, let's look at if s1[0 - i], s2[0 - j] can be interleaved to s3[0 - k].
 * Start from indices 0, 0, 0 and compare s1[i] == s3[k] or s2[j] == s3[k].
 * Return valid only if either i or j match k and the remaining is also valid.
 * Only need to cache invalid[i][j] since in most of the cases s1[0 - i] and 
 * s2[0 - j] doesn't form s3[0 - k].
 */
class Solution {

    /*
     * DP State :-
     * dp[i][j] = TRUE, if s1[0 : i] and s2[0 : j] can together form s3[0 : k].
     *          = FALSE, otherwise.
     *
     * DP Transitions :-
     * dp[i][j] = dp[i + 1][j] || dp[i][j + 1]
     * 
     * Final Answer :-
     * dp[0][0]
     */

    private char[] c1, c2, c3;
    private Boolean[][] isValid;

    private boolean memoization(int i, int j, int k) {
        if (k == c3.length)
            return true;
        if (isValid[i][j] != null)
            return isValid[i][j];
        boolean validity = (i < c1.length && c1[i] == c3[k] && memoization(i + 1, j, k + 1)) || 
                           (j < c2.length && c2[j] == c3[k] && memoization(i, j + 1, k + 1));
        return isValid[i][j] = validity;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 + len2 != s3.length())
            return false;
        c1 = s1.toCharArray();
        c2 = s2.toCharArray();
        c3 = s3.toCharArray();
        isValid = new Boolean[len1 + 1][len2 + 1];
        isValid[len1][len2] = false;
        return memoization(0, 0, 0);
    }
}



/*
 * Complete explanation here -> 
 * https://leetcode.com/problems/interleaving-string/solution/
 */
class Solution2 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length())
            return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = true;
                else if (i == 0)
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                else if (j == 0)
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                else
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || 
                               (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[m][n];
    }
}



// Optimization of Space Complexity of above
class Solution3 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length())
            return false;
        boolean[] dp = new boolean[n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0)
                    dp[j] = true;
                else if (i == 0)
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                else if (j == 0)
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                else
                    dp[j] = (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1) || 
                             dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[n];
    }
}



/*
 * BFS Solution Intuition -> 
 * https://leetcode.com/problems/interleaving-string/discuss/31904/Summary-of-solutions-BFS-DFS-DP
 */
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3)
            return false;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        q.offer(new int[] {-1, -1});
        int k = 0;
        boolean[][] vis = new boolean[len1 + 1][len2 + 1];
        while (!q.isEmpty() && k < len3) {
            int[] temp = q.poll();
            int i = temp[0], j = temp[1];
            if (i >= len1 || j >= len2)
                continue;
            if (i == -1) {
                if (q.isEmpty())
                    break;
                q.offer(new int[] {-1, -1});
                k++;
                continue;
            }
            if (vis[i][j])
                continue;
            vis[i][j] = true;
            if (i < len1 && !vis[i + 1][j] && s1.charAt(i) == s3.charAt(k))
                q.offer(new int[] {i + 1, j});
            if (j < len2 && !vis[i][j + 1] && s2.charAt(j) == s3.charAt(k))
                q.offer(new int[] {i, j + 1});
        }
        return k == len3;
    }
}
