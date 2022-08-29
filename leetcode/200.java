import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/number-of-islands/
*/



// DFS Solution
class Solution1 {
	
	public void dfs(char[][] grid, boolean[][] visited, int row, int column, 
                    int rows, int columns) {
		if (row < 0 || column < 0 || row == rows || column == columns)
			return;
		visited[row][column] = true;
		if (!visited[row - 1][column] && grid[row - 1][column] == '1')
			dfs(grid, visited, row - 1, column, rows, columns);
		if (!visited[row + 1][column] && grid[row + 1][column] == '1')
			dfs(grid, visited, row + 1, column, rows, columns);
		if (!visited[row][column - 1] && grid[row][column - 1] == '1')
			dfs(grid, visited, row, column - 1, rows, columns);
		if (!visited[row][column + 1] && grid[row][column + 1] == '1')
			dfs(grid, visited, row, column + 1, rows, columns);
	}
	
    public int numIslands(char[][] grid) {
        int islands = 0, rows = grid.length, columns = grid[0].length;
		boolean[][] visited = new boolean[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (grid[i][j] == '0' || visited[i][j])
					continue;
				dfs(grid, visited, i, j, rows, columns);
				islands++;
			}
		}
		return islands;
    }
}



// A BFS Solution
class Solution2 {
	
	public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
			return 0;
		int result = 0, m = grid.length, n = grid[0].length;
		int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '0')
					continue;
				result++;
				Queue<int[]> queue = new LinkedList<>();
				queue.add(new int[] {i, j});
				grid[i][j] = '0';
				while (!queue.isEmpty()) {
					int[] current = queue.poll();
					for (int[] direction : directions) {
						int r = current[0] + direction[0], 
                            c = current[1] + direction[1];
						if (r >= 0 && r < m && c >= 0 && c < n && 
                            grid[r][c] == '1') {
							queue.add(new int[] {r, c});
							grid[r][c] = '0';
						}
					}
				}
			}
		}
		return result;
    }
}



// Another BFS Solution
class Point {
	int x, y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution3 {
	
	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public void bfs(char[][] grid, int x, int y) {
		grid[x][y] = '0';
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(x, y));
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int[] direction : directions) {
				int x1 = p.x + direction[0], y1 = p.y + direction[1];
				if (x1 >= 0 && y1 >= 0 && x1 < grid.length && 
                    y1 < grid[0].length && grid[x1][y1] == '1') {
					grid[x1][y1] = '0';
					queue.offer(new Point(x1, y1));
				}
			}
		}
	}
	
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
			return 0;
		int islands = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '0')
					continue;
				islands++;
				bfs(grid, i, j);
			}
		}
		return islands;
    }
}



// A Union Find Solution
class Solution4 {
	
	int[] componentSize;
	int[] id;
	int n, m, islandCount;
	
	public int find(int p) {
		while (id[p] != p)
			p = id[p];
		return p;
	}
	
	public void union(int p, int q) {
		int rootP = find(p), rootQ = find(q);
		if (rootP == rootQ)
			return;
		if (componentSize[rootP] < componentSize[rootQ]) {
			componentSize[rootQ] += componentSize[rootP];
			id[rootP] = id[rootQ];
		}
		else {
			componentSize[rootP] += componentSize[rootQ];
			id[rootQ] = id[rootP];
		}
		islandCount--;
	}
	
	public boolean inside(int x, int y) {
		return (x >= 0 && y >= 0 && x < n && y < m);
	}
	
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
			return 0;
		n = grid.length;
		m = grid[0].length;
		componentSize = new int[n * m];
		id = new int[n * m];
		for (int i = 0; i < n * m; i++) {
			id[i] = i;
			componentSize[i] = 1;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == '0')
					continue;
				islandCount++;
				int temp = i * m + j;
				if (inside(i + 1, j) && grid[i + 1][j] == '1')
					union(temp, temp + m);
				if (inside(i, j + 1) && grid[i][j + 1] == '1')
					union(temp, temp + 1);
			}
		}
		return islandCount;
    }
}
