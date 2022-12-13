import java.util.*;

/*
 * https://leetcode.com/problems/minimum-falling-path-sum-ii/
 */


// Easiest Hard problem
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length, result = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int minVal = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (k == j)
                        continue;
                    minVal = Math.min(minVal, grid[i - 1][k]);
                }
                grid[i][j] += minVal;
            }
        }
        for (int i = 0; i < n; i++)
            result = Math.min(result, grid[n - 1][i]);
        return result;
    }
}
