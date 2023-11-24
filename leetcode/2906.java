/*
 * https://leetcode.com/problems/construct-product-matrix/
 */



/*
 * Approach of Prefix and Suffix product arrays!
 */
class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        long prefix = 1, suffix = 1, mod = 12345;
        long[][] prefixProduct = new long[rows][cols], suffixProduct = new long[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                prefixProduct[i][j] = prefix;
                prefix = (prefix * grid[i][j]) % mod;
            }
        }
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                suffixProduct[i][j] = suffix;
                suffix = (suffix * grid[i][j]) % mod;
            }
        }
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                result[i][j] = (int)((prefixProduct[i][j] * suffixProduct[i][j]) % mod);
        }
        return result;
    }
}
