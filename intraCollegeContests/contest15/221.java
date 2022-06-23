/*
 * Question Link -> https://leetcode.com/problems/maximal-square/
 */



/*
 * Method of Tabulation DP
 * Let dp[i][j] = maximum side length of the required square with bottom right 
 * corner as (i, j).
 * Now, if matrix[i][j] = 0, then dp[i][j] = 0, since this particular cell can't 
 * contribute to a sub-square consisting of all 1's.
 * Otherwise, dp[i][j] = min(dp[value present to the immediate left], 
 * dp[value present just immediately above], dp[value present in diagonal above-left]). 
 * This is because the building blocks of a square matrix of dimension 3 is square 
 * sub-matrices of dimension 2, and so on and so forth - thence justifying the 
 * use of DP, as it contains similar sub-problems.
 */
class Solution1 { // TC = O(m * n), SC = O(m * n)
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length, result = 0;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < columns; i++) {
            dp[0][i] = matrix[0][i] - '0';
            result = Math.max(result, dp[0][i]);
        }
        for (int i = 0; i < rows; i++) {
            dp[i][0] = matrix[i][0] - '0';
            result = Math.max(result, dp[i][0]);
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (matrix[i][j] == '0')
                    continue;
                dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                result = Math.max(result, dp[i][j]);
            }
        }
        return result * result;
    }
}



// Space-Optimized DP - TC = O(m * n), SC = O(2 * n) ~ O(n)
class Solution2 {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length, result = 0;
        int[][] dp = new int[2][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || j == 0 || matrix[i][j] == '0')
                    dp[i % 2][j] = matrix[i][j] - '0';
                else
                    dp[i % 2][j] = 1 + Math.min(dp[i % 2][j - 1], Math.min(dp[1 - i % 2][j - 1], dp[1 - i % 2][j]));
                result = Math.max(result, dp[i % 2][j]);
            }
        }
        return result * result;
    }
}



/*
 * Really good Leetcode discuss resource with visual explanations -> 
 * https://leetcode.com/problems/maximal-square/discuss/600149/Python-Thinking-Process-Diagrams-DP-Approach
 */



/*
 * Space-Optimized DP using 1 row in dp array - TC = O(m * n), SC = O(n)
 * upperleft -> dp[i - 1][j - 1], already computed in the previous iteration.
 * dp[j] (when used on the right side) -> dp[i - 1][j], upper element
 * dp[j - 1] -> dp[i][j - 1], left element
 */ 
class Solution3 {
    public int maximalSquare(int[][] matrix) {
        int[] dp = new int[matrix[0].length + 1];
        int upperLeft = 0, temp = 0, result = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(temp, Math.min(upperLeft, dp[j - 1]));
                    result = Math.max(result, dp[j]);
                }
                else
                    dp[j] = 0;
                upperLeft = temp;
            }
        }
        return result * result;
    }
}



/*
 * O(1) auxiliary space - but this will fail in case of inputs having a large, 
 * required square sub-matrix. 
 */
class Solution4 {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length, result = '0';
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] != '0' && i != 0 && j != 0)
                    matrix[i][j] = (char)(Math.min(Math.min(matrix[i - 1][j] - '0', 
                                                            matrix[i][j - 1] - '0'), 
                                                   matrix[i - 1][j - 1] - '0') + 1 + '0');
                result = Math.max(result, matrix[i][j]);
            }
        }
        return (result - '0') * (result - '0');
    }
}
