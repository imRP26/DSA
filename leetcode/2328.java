/*
 * https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/
 */



/*
 * Approach 1 from LC Official Editorial!
 */
class Solution {
    public int countPaths(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        long res = 0, mod = (long)1e9 + 7;
        List<int[]> cells = new ArrayList<>();
        long[][] dp = new long[rows][cols];
        for (long[] row : dp)
            Arrays.fill(row, 1);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                cells.add(new int[] {grid[i][j], i, j});
        }
        Collections.sort(cells, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < rows * cols; i++) {
            int[] arr = cells.get(i);
            int val = arr[0], x = arr[1], y = arr[2];
            if (x >= 1 && val < grid[x - 1][y])
                dp[x - 1][y] = (dp[x - 1][y] + dp[x][y]) % mod;
            if (x + 1 < rows && val < grid[x + 1][y])
                dp[x + 1][y] = (dp[x + 1][y] + dp[x][y]) % mod;
            if (y >= 1 && val < grid[x][y - 1])
                dp[x][y - 1] = (dp[x][y - 1] + dp[x][y]) % mod;
            if (y + 1 < cols && val < grid[x][y + 1])
                dp[x][y + 1] = (dp[x][y + 1] + dp[x][y]) % mod;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                res = (res + dp[i][j]) % mod;
        }
        return (int)res;
    }
}



/*
 * Same approach as above, but using alternate syntaxes!
 */
class Solution {
    public int countPaths(int[][] grid) {
        int rows = grid.length, cols = grid[0].length, res = 0, mod = 1_000_000_007;
        int[][] dp = new int[rows][cols], directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, 1));
        int[][] cells = new int[rows * cols][2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int idx = i * cols + j;
                cells[idx][0] = i;
                cells[idx][1] = j;
            }
        } 
        Arrays.sort(cells, (a, b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]);
        for (int[] cell : cells) {
            int i = cell[0], j = cell[1], val = grid[i][j];
            for (int[] dir : directions) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < rows && y >= 0 && y < cols && val < grid[x][y]) {
                    dp[x][y] += dp[i][j];
                    dp[x][y] %= mod;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res += dp[i][j];
                res %= mod;
            }
        }
        return res;
    }
}



/*
 * Approach 2 from LC Official Editorial
 */
class Solution {

    private int rows, cols, mod = 1_000_000_007;
    private int[][] dp, directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int dfs(int[][] grid, int i, int j) {
        if (dp[i][j] != 0)
            return dp[i][j];
        int retVal = 1;
        for (int[] dir : directions) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] < grid[i][j]) {
                retVal += dfs(grid, x, y);
                retVal %= mod;
            }
        }
        return dp[i][j] = retVal;
    }

    public int countPaths(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        dp = new int[rows][cols];
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                res = (res + dfs(grid, i, j)) % mod;
        }
        return res;
    }
}
