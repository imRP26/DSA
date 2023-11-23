/*
 * https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
 */



/*
 * Naive Solution!
 */
class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int rows = mat.length;
        int[][] rowMap = new int[rows][2];
        for (int i = 0; i < rows; i++) {
            int num1 = 0;
            for (int j : mat[i])
                num1 += j;
            rowMap[i] = new int[] {i, num1};
        }
        Arrays.sort(rowMap, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int[] res = new int[k];
        for (int i = 0; i < k; i++)
            res[i] = rowMap[i][0];
        return res;
    }
}



/*
 * 
 */
