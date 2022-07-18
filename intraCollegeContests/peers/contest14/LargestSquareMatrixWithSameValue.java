/*
 * Question Link -> 
 * https://binarysearch.com/problems/Largest-Square-Matrix-with-Same-Value
 */



// My initial DP Solution
class Solution1 {

    public boolean checkEquality(int i, int j, int[][] matrix) {
        return (matrix[i - 1][j - 1] == matrix[i - 1][j] && 
                matrix[i - 1][j] == matrix[i][j - 1] && 
                matrix[i][j - 1] == matrix[i][j]);
    }

    public int solve(int[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length, result = 1;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < columns; i++)
            dp[0][i] = 1;
        for (int i = 0; i < rows; i++)
            dp[i][0] = 1;
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (checkEquality(i, j, matrix))
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], 
                                   Math.min(dp[i - 1][j - 1], dp[i - 1][j]));
                else
                    dp[i][j] = 1;
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }
}



// More efficient solution in terms of space complexity
class Solution2 {

    public boolean checkEquality(int i, int j, int[][] matrix) {
        return (matrix[i - 1][j - 1] == matrix[i - 1][j] && 
                matrix[i - 1][j] == matrix[i][j - 1] && 
                matrix[i][j - 1] == matrix[i][j]);
    }

    public int solve(int[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length, result = 1;
        int[][] dp = new int[2][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || j == 0) {
                    dp[i % 2][j] = 1;
                    continue;
                }
                if (checkEquality(i, j, matrix))
                    dp[i % 2][j] = 1 + Math.min(dp[i % 2][j - 1], 
                                   Math.min(dp[1 - i % 2][j - 1], dp[1 - i % 2][j]));
                else
                    dp[i % 2][j] = 1;
                result = Math.max(result, dp[i % 2][j]);
            }
        }
        return result;
    }
}



// Even More efficient solution in terms of space complexity
class Solution3 {

    public boolean checkEquality(int i, int j, int[][] matrix) {
        return (matrix[i - 1][j - 1] == matrix[i - 1][j] && matrix[i - 1][j] == matrix[i][j - 1] && 
                matrix[i][j - 1] == matrix[i][j]);
    }

    public int solve(int[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length, upperLeft = 1, temp = 0, result = 1;
        int[] dp = new int[columns + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if (i == 1 || j == 1) {
                    dp[j] = 1;
                    continue;
                }
                temp = dp[j]; // upper element of previous row, analogous to dp[i - 1][j]
                if (checkEquality(i - 1, j - 1, matrix)) {
                    dp[j] = 1 + Math.min(temp, Math.min(upperLeft, dp[j - 1])); // immediate left element of the same row, analogous to dp[i][j - 1] 
                    result = Math.max(result, dp[j]);
                }
                else
                    dp[j] = 1;
                upperLeft = temp; // diagonal upper element of previous row, analogous to dp[i - 1][j - 1]
            }
        }
        return result;
    }
}
