/*
 * https://leetcode.com/problems/rotate-image/
 */



/*
 * Approach from Striver!
 */
class Solution {
    /*
     * Swap rows in complementary positions.
     * Compute Transpose.
     * OR
     * Compute Transpose.
     * Swap rows in complementary positions.
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int j = 0; j < n / 2; j++) {
            for (int i = 0; i < n; i++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
}
