/*
 * https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
 */



/*
 * Approach 1 from LC Official Editorial -> 
 * The approach boils down to fixing 2 rows and then converting this problem to the corresponding 
 * 1D problem - use of a hashmap and computing prefix sum values for all the submatrices helps 
 * in finding the counts.
 */
class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length, res = 0;
        int[][] prefixSum = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++)
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + matrix[i - 1][j - 1];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int row1 = 1; row1 <= rows; row1++) {
            for (int row2 = row1; row2 <= rows; row2++) {
                map.clear();
                map.put(0, 1);
                for (int col = 1; col <= cols; col++) {
                    int currSum = prefixSum[row2][col] - prefixSum[row1 - 1][col];
                    res += map.getOrDefault(currSum - target, 0);
                    map.put(currSum, 1 + map.getOrDefault(currSum, 0));
                }
            }
        }
        return res;
    }
}



/*
 * Approach 2 from LC Official Editorial - same approach as above, just now we're fixing 2 columns 
 * and the 1 row!
 */
class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length, res = 0;
        int[][] prefixSum = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++)
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + matrix[i - 1][j - 1];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int col1 = 1; col1 <= cols; col1++) {
            for (int col2 = col1; col2 <= cols; col2++) {
                map.clear();
                map.put(0, 1);
                for (int row = 1; row <= rows; row++) {
                    int currSum = prefixSum[row][col2] - prefixSum[row][col1 - 1];
                    res += map.getOrDefault(currSum - target, 0);
                    map.put(currSum, 1 + map.getOrDefault(currSum, 0));
                }
            }
        }
        return res;
    }
}
