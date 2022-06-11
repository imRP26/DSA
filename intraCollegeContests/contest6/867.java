/*
 * Question Link -> https://leetcode.com/problems/transpose-matrix/
 */



// My simple solution
class Solution1 {
    public int[][] transpose(int[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length;
        int[][] result = new int[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                result[j][i] = matrix[i][j];
        }
        return result;
    }
}



// Follow-up - efficient (kinda) solution for a square matrix
class Solution2 {
    public int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < rows; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }
}
