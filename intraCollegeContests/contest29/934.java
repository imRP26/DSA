import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/shortest-bridge/
 */



// Multi-Source BFS
class Solution1 {

	List<int[]> bfs(int[][] grid, int x, int y) {
		List<int[]> list = new ArrayList<>();
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x, y});
		int len = grid.length;
		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
            x = cell[0];
            y = cell[1];
			if (grid[x][y] == 0)
				continue;
			grid[x][y] = 0;
			list.add(cell);
			if (y < len - 1 && grid[x][y + 1] == 1)
				queue.offer(new int[] {x, y + 1});
			if (x < len - 1 && grid[x + 1][y] == 1)
				queue.offer(new int[] {x + 1, y});
			if (y >= 1 && grid[x][y - 1] == 1)
				queue.offer(new int[] {x, y - 1});
			if (x >= 1 && grid[x - 1][y] == 1)
				queue.offer(new int[] {x - 1, y});
		}
		return list;
	}

    public int shortestBridge(int[][] grid) {
        int len = grid.length, result = Integer.MAX_VALUE;
		boolean flag = false;
		List<int[]> list1 = new ArrayList<>();
		List<int[]> list2 = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] == 0)
					continue;
				if (!flag) {
					list1 = bfs(grid, i, j);
					flag = true;
				}
				else {
					list2 = bfs(grid, i, j);
					break;
				}
			}
		}
		for (int i = 0; i < list1.size(); i++) {
			int x1 = list1.get(i)[0], y1 = list1.get(i)[1];
			for (int j = 0; j < list2.size(); j++) {
				int x2 = list2.get(j)[0], y2 = list2.get(j)[1];
				int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2) - 1;
				result = Math.min(result, dist);
			}
		}
		return result;
    }
}



/*
 * Use DFS to mark the 1st island to 2.
 * Continue traversing the 2D array to add every '1' (the 2nd island) into the 
   queue.
 * Perform BFS to find the shortest path needed.
 */
class Solution2 {

	void dfs(int[][] grid, int i, int j, int rows, int columns) {
		grid[i][j] = 2;
		if (i >= 1 && grid[i - 1][j] == 1)
			dfs(grid, i - 1, j, rows, columns);
		if (i + 1 < rows && grid[i + 1][j] == 1)
			dfs(grid, i + 1, j, rows, columns);
		if (j >= 1 && grid[i][j - 1] == 1)
			dfs(grid, i, j - 1, rows, columns);
		if (j + 1 < columns && grid[i][j + 1] == 1)
			dfs(grid, i, j + 1, rows, columns);
	}

	public int shortestBridge(int[][] grid) {
		int rows = grid.length, columns = grid[0].length;
		boolean found = false;
		Queue<int[]> queue = new LinkedList<>();
		int level = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (grid[i][j] == 1 && !found) {
					dfs(grid, i, j, rows, columns);
					found = true;
				}
				if (found && grid[i][j] == 1)
					queue.add(new int[] {i, j});
			}
		}
		int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			for (int i = 0; i < queueSize; i++) {
				int[] position = queue.poll();
				for (int j = 0; j < 4; j++) {
					int x = position[0] + direction[j][0], 
						y = position[1] + direction[j][1];
					if (x < 0 || y < 0 || x >= rows || y >= columns)
						continue;
					if (grid[x][y] == 2)
						return level;
					if (grid[x][y] == 1)
						continue;
					if (grid[x][y] == 0) {
						grid[x][y] = 1;
						queue.add(new int[] {x, y});
					}
				}
			}
			level++;
		}
		return -1;
	}
}
