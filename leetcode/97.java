import java.util.*;

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
class Solution1 {

    boolean dfs(char[] c1, char[] c2, char[] c3, int i, int j, int k, boolean[][] invalid) {
        if (invalid[i][j])
            return false;
        if (k == c3.length)
            return true;
        boolean valid = i < c1.length && c1[i] == c3[k] && dfs(c1, c2, c3, i + 1, j, k + 1, invalid) || 
                        j < c2.length && c2[j] == c3[k] && dfs(c1, c2, c3, i, j + 1, k + 1, invalid);
        if (!valid)
            invalid[i][j] = true;
        return valid;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length())
            return false;
        return dfs(c1, c2, c3, 0, 0, 0, new boolean[m + 1][n + 1]);
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
class Solution4 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3)
            return false;
        Deque<Integer> deque = new LinkedList<>();
        int matched = 0;
        deque.offer(0);
        Set<Integer> set = new HashSet<>();
        while (!deque.isEmpty() && matched < len3) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int p1 = deque.peek() / len3, p2 = deque.peek() % len3;
                deque.poll();
                /*
                 * we can represent a position in a matrix as (y * width + x) or 
                 * (x * height + y).
                 */
                if (p1 < len1 && s1.charAt(p1) == s3.charAt(matched)) {
                    int key = (p1 + 1) * len3 + p2;
                    if (!set.contains(key)) {
                        set.add(key);
                        deque.offer(key);
                    }
                }
                if (p2 < len2 && s2.charAt(p2) == s3.charAt(matched)) {
                    int key = p1 * len3 + (p2 + 1);
                    if (!set.contains(key)) {
                        set.add(key);
                        deque.offer(key);
                    }
                }
            }
            matched++;
        }
        return !deque.isEmpty() && matched == len3;
    }
}
