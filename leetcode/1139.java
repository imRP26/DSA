/*
 * https://leetcode.com/problems/largest-1-bordered-square/
 */



/*
 * Approach from -> 
 * https://leetcode.com/problems/largest-1-bordered-square/solutions/345334/clean-java-dp-over-100-just-to-store-the-maximum-possibility/
 */
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int rows = grid.length, columns = grid[0].length, result = 0;
        int[][] horizontal = new int[rows + 1][columns + 1], vertical = new int[rows + 1][columns + 1];
        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= columns; column++) {
                if (grid[row - 1][column - 1] == 0)
                    continue;
                horizontal[row][column] = 1 + horizontal[row - 1][column];
                vertical[row][column] = 1 + vertical[row][column - 1];
                int sideLength = Math.min(horizontal[row][column], vertical[row][column]);
                for (int i = sideLength; i >= 1; i--) {
                    if (horizontal[row][column - i + 1] >= i && vertical[row - i + 1][column] >= i) {
                        result = Math.max(result, i * i);
                        break;
                    }
                }
            }
        }
        return result;
    }
}
