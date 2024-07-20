/*
 * https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/
 */



/*
 * Approach 1 from LC Official Editorial!
 * TC = O(rows * columns), SC = O(rows + columns)
 */
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int rows = rowSum.length, cols = colSum.length;
        int[][] result = new int[rows][cols];
        int[] currRowSum = new int[rows], currColSum = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // The reason why 'min' is taken can be understood from the 1st example itself!
                result[i][j] = Math.min(rowSum[i] - currRowSum[i], colSum[j] - currColSum[j]);
                currRowSum[i] += result[i][j];
                currColSum[j] += result[i][j];
            }
        }
        return result;
    }
}



/*
 * Approach 2 from LC Official Editorial!
 * TC = O(rows * columns), SC = O(1)
 */
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int rows = rowSum.length, cols = colSum.length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= result[i][j];
                colSum[j] -= result[i][j];
            }
        }
        return result;
    }
}



/*
 * Approach 3 from LC Official Editorial!
 * TC = O(min(rows, columns)), SC = O(1)
 */
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int rows = rowSum.length, cols = colSum.length, i = 0, j = 0;
        int[][] result = new int[rows][cols];
        while (i < rows && j < cols) {
            result[i][j] = Math.min(rowSum[i], colSum[j]);
            rowSum[i] -= result[i][j];
            colSum[j] -= result[i][j];
            i += rowSum[i] == 0 ? 1 : 0;
            j += colSum[j] == 0 ? 1 : 0;
        }
        return result;
    }
}
