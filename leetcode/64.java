/*
 * https://leetcode.com/problems/minimum-path-sum/
 */



/*
 * My approach of Bottom-Up DP
 */
class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        for (int[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = grid[i][j];
                else if (i == 0)
                    dp[i][j] = Math.min(dp[i][j], grid[i][j] + dp[i][j - 1]);
                else if (j == 0)
                    dp[i][j] = Math.min(dp[i][j], grid[i][j] + dp[i - 1][j]);
                else
                    dp[i][j] = Math.min(dp[i][j], grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[rows - 1][columns - 1];
    }
}



/*
 * My Memoization wala approach!
 */
class Solution {

	private int[][] dp;

	private int memoize(int i, int j, int[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        if (i >= rows || j >= columns)
            return 0;
		if (dp[i][j] != -1)
			return dp[i][j];
		if (i < rows - 1 && j < columns - 1)
			dp[i][j] = grid[i][j] + Math.min(memoize(i + 1, j, grid), memoize(i, j + 1, grid));
		else if (i < rows - 1)
			dp[i][j] = grid[i][j] + memoize(i + 1, j, grid);
		else
			dp[i][j] = grid[i][j] + memoize(i, j + 1, grid);
		return dp[i][j];
	}

	public int minPathSum(int[][] grid) {
		int rows = grid.length, columns = grid[0].length;
        dp = new int[rows][columns];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		return memoize(0, 0, grid);
	}
}



/*
 * Aproach of 1D Bottom-Up DP from -> 
 * https://leetcode.com/problems/minimum-path-sum/editorial/
 */
class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        int[] dp = new int[columns];
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = columns - 1; j >= 0; j--) {
                if (i == rows - 1 && j != columns - 1)
                    dp[j] = grid[i][j] + dp[j + 1];
                else if (j == columns - 1 && i != rows - 1)
                    dp[j] = grid[i][j] + dp[j];
                else if (j != columns - 1 && i != rows - 1)
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                else
                    dp[j] = grid[i][j];
            }
        }
        return dp[0];
    }
}
