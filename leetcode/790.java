/*
 * https://leetcode.com/problems/domino-and-tromino-tiling/
 */



/*
 * Referenced Solution from -> 
 * https://leetcode.com/problems/domino-and-tromino-tiling/solutions/116664/schematic-explanation-of-two-equivalent-dp-recurrence-formula/
 */ 
class Solution1 {

    static final long mod = (int)1e9 + 7;

    public int numTilings(int n) {
        if (n <= 2)
            return n;
        long p0 = 1, p1 = 1, p2 = 2, result = 0;
        for (int i = 3; i <= n; i++) {
            result = (2 * p2 + p0) % mod;
            p0 = p1;
            p1 = p2;
            p2 = result;
        }
        return (int)result;
    }
}



/*
 * For more solutions, go through -> 
 * https://leetcode.com/problems/domino-and-tromino-tiling/solutions/1570448/domino-and-tromino-tiling/
 */



/*
 * Approach 4 (Matrix Exponentiation) from here -> 
 * https://leetcode.com/problems/domino-and-tromino-tiling/solutions/1570448/domino-and-tromino-tiling/
 */
class Solution2 {

    long mod = 1_000_000_007;

    private long[][] matrixMultiplication(long[][] array1, long[][] array2) {
        long[][] answer = new long[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                long sum = 0;
                for (int k = 0; k < 3; k++)
                    sum = (sum + array1[i][k] * array2[k][j]) % mod;
                answer[i][j] = sum;
            }
        }
        return answer;
    }

    private int matrixExponentiation(int expo) {
        long[][] matrix = {{1, 1, 2}, {1, 0, 0}, {0, 1, 1}};
        long[][] result = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        while (expo > 0) {
            if (expo % 2 == 1)
                result = matrixMultiplication(result, matrix);
            matrix = matrixMultiplication(matrix, matrix);
            expo /= 2;
        }
        long answer = (2 * result[0][0] + result[0][1] + result[0][2]) % mod;
        return (int)answer;
    }

    public int numTilings(int n) {
        if (n <= 2)
            return n;
        return matrixExponentiation(n - 2);
    }
}
