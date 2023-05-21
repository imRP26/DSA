/*
 * https://leetcode.com/problems/maximum-rows-covered-by-columns/
 */



/*
 * Look at the constraints FFS - then simple application of bitmasking, followed by 
 * brute force!
 */
class Solution {
    public int maximumRows(int[][] matrix, int numSelect) {
        int rows = matrix.length, cols = matrix[0].length, bitmasks = (1 << cols) - 1, result = 0;
        Set<Integer> colIndices = new HashSet<>();
        for (int mask = bitmasks; mask >= 0; mask--) {
            colIndices.clear();
            int k = mask, colIndex = 0, numRows = 0;
            while (k > 0) {
                if (k % 2 == 1)
                    colIndices.add(colIndex);
                k /= 2;
                colIndex++;
            }
            if (colIndices.size() != numSelect)
                continue;
            for (int i = 0; i < rows; i++) {
                boolean flag = true;
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] == 1 && !colIndices.contains(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    numRows++;
            }
            result = Math.max(result, numRows);
        }
        return result;
    }
}
