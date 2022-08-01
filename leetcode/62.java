import java.util.*;

/*
 * https://leetcode.com/problems/unique-paths/
 */



/*
 * My Simple Version - just the method of doing combinatorics
 * We have to traverse a total of (m + n - 2) right or down steps, out of which 
 * we take either (m - 1) right steps or (n - 1) down steps.
 */
class Solution1 {
    public int uniquePaths(int m, int n) {
        long result = 1;
        for (long i = m + n - 2, j = 1; i > Math.max(m - 1, n - 1); i--, j++) {
            result *= i;
            result /= j;
        }
        return (int)result;
    }
}



/*
 * DP Approach :- 
 * Since the robot can only move right and down, when it arrives at a point, it 
 * either arrives from left or above. If we use dp[i][j] for the number of 
 * unique paths to arrive at the point (i, j), then the state equation is 
 * dp[i][j] = dp[i][j - 1] + dp[i - 1][j]. 
 * Moreover, we have the base cases dp[0][j] = dp[i][0] = 1 for all valid i and 
 * j.
 */
class Solution2 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
        return dp[m - 1][n - 1];
    }
}



/*
 * The above solution runs in O(m * n) time and costs O(m * n) space. However, 
 * we can notice that each time when we update dp[i][j], we only need dp[i - 1][j] 
 * (at the previous row) and dp[i][j - 1] (at the current row). So we can reduce 
 * the memory usage to just two rows (O(n)).
 */
class Solution3 {
    public int uniquePaths(int m, int n) {
        int[] prev = new int[n];
        Arrays.fill(prev, 1);
        int[] curr = new int[n];
        Arrays.fill(curr, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
                // dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                curr[j] = prev[j] + curr[j - 1];
            for (int j = 1; j < n; j++) {
                int temp = curr[j];
                curr[j] = prev[j];
                prev[j] = temp;
            }
        }
        return prev[n - 1];
    }
}



/*
 * Upon further inspecting the above code, prev[j] is just the curr[j] before 
 * the update. So we can further reduce the memory usage to just 1 row.
 */
class Solution4 {
    public int uniquePaths(int m, int n) {
        int[] curr = new int[n - 1];
        Arrays.fill(curr, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
                curr[j] += curr[j - 1];
        }
        return curr[n - 1];
    }
}
