/*
 * Question Link -> 
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 */



/*
 * 2-D DP Solution (mine)...
 * TC = O(m * n), SC = O(m * n)
 * Here, dp[i][j] = number of square submatrices having their bottom-right 
 *                  corner as (i, j).
 * How to build up the intuition for this -> Have a go at Example 1 given and 
 * progressively build up, starting from squares having side-length 1 to those 
 * having side-lengths 2, 3 and so on and so forth.
 */
class Solution1 {
    public int countSquares(int[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length, result = 0;
		int[][] dp = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (i == 0 || j == 0 || matrix[i][j] == 0)
					dp[i][j] = matrix[i][j];
                else
				    dp[i][j] = 1 + Math.min(dp[i][j - 1], 
							       Math.min(dp[i - 1][j - 1], dp[i - 1][j]));
                result += dp[i][j];
			}
		}
		return result;
    }
}



// Same concept as above, but space optimized DP
class Solution2 {
    public int countSquares(int[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length, result = 0;
		int[][] dp = new int[2][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (i == 0 || j == 0 || matrix[i][j] == 0)
					dp[i % 2][j] = matrix[i][j];
                else
				    dp[i % 2][j] = 1 + Math.min(dp[i % 2][j - 1], 
							       Math.min(dp[1 - i % 2][j - 1], dp[1 - i % 2][j]));
                result += dp[i % 2][j];
			}
		}
		return result;
    }
}



