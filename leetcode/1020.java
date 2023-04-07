/*
 * https://leetcode.com/problems/number-of-enclaves/
 */



/*
 * My simple BFS based solution!!
 */
class Solution {

    private int rows, columns;
    private boolean[][] visited;
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private boolean isValidGridCell(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < columns;
    }

    private int bfs(int x, int y, int[][] grid) {
        boolean retVal = true;
        int cells = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            x = temp[0];
            y = temp[1];
            if (visited[x][y])
                continue;
            if (x == 0 || y == 0 || x == rows - 1 || y == columns - 1)
                retVal = false;
            visited[x][y] = true;
            cells++;
            for (int[] direction : directions) {
                int x1 = x + direction[0], y1 = y + direction[1];
                if (isValidGridCell(x1, y1) && !visited[x1][y1] && grid[x1][y1] == 1)
                    queue.offer(new int[] {x1, y1});
            }
        }
        return (retVal) ? cells : 0;
    }

    public int numEnclaves(int[][] grid) {
        rows = grid.length;
        columns = grid[0].length;
        visited = new boolean[rows][columns];
        int result = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                if (!visited[i][j] && grid[i][j] == 1)
                    result += bfs(i, j, grid);
            }
        }
        return result;
    }
}



/*
 * My Simple DFS Solution
 */
class Solution {

    private int rows, columns, cells;
    private boolean[][] visited;
    private boolean isBoundaryCell;
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < columns;
    }

    private void dfs(int x, int y, int[][] grid) {
        if (visited[x][y])
            return;
        visited[x][y] = true;
        cells++;
        if (x == 0 || x == rows - 1 || y == 0 || y == columns - 1)
            isBoundaryCell = true;
        for (int[] direction : directions) {
            int x1 = x + direction[0], y1 = y + direction[1];
            if (isValidCell(x1, y1) && grid[x1][y1] == 1 && !visited[x1][y1])
                dfs(x1, y1, grid);
        }
    }

    public int numEnclaves(int[][] grid) {
        rows = grid.length;
        columns = grid[0].length;
        int result = 0;
        visited = new boolean[rows][columns];
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    cells = 0;
                    isBoundaryCell = false;
                    dfs(i, j, grid);
                    if (!isBoundaryCell)
                        result += cells;
                }
            }
        }
        return result;
    }
}



/*
 * Awesomely Simple DFS Solution
 */
class Solution {

	private int rows, columns;

	private void dfs(int x, int y, int[][] grid) {
		if (x >= 0 && x < rows && y >= 0 && y < columns && grid[x][y] == 1) {
			grid[x][y] = 0;
			dfs(x + 1, y, grid);
			dfs(x - 1, y, grid);
			dfs(x, y + 1, grid);
			dfs(x, y - 1, grid);
		}
	}

	public int numEnclaves(int[][] grid) {
		rows = grid.length;
		columns = grid[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (i == 0 || j == 0 || i == rows - 1 || j == columns - 1)
					dfs(i, j, grid);
			}
		}
		int result = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (grid[i][j] == 1)
					result++;
			}
		}
		return result;
	}
}
