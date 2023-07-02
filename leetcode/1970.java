/*
 * https://leetcode.com/problems/last-day-where-you-can-still-cross/
 */



/*
 * Approach of BFS + Binary Search from LC Official Editorial
 */
class Solution {

    private boolean canCross(int row, int col, int[][] cells, int day) {
        int[][] grid = new int[row][col], dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < day; i++)
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        for (int i = 0; i < col; i++) {
            if (grid[0][i] == 0) {
                q.offer(new int[] {0, i});
                grid[0][i] = -1;
            }
        }
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int x = cell[0], y = cell[1];
            if (x == row - 1)
                return true;
            for (int[] dir : dirs) {
                int x1 = x + dir[0], y1 = y + dir[1];
                if (x1 >= 0 && x1 < row && y1 >= 0 && y1 < col && grid[x1][y1] == 0) {
                    grid[x1][y1] = -1;
                    q.offer(new int[] {x1, y1});
                }
            }
        }
        return false;
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int low = 0, high = row * col - 1, res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canCross(row, col, cells, mid)) {
                res = mid;
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return res;
    }
}



/*
 * Approach of DFS + Binary Search
 */
class Solution {

    private int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private boolean dfs(int x, int y, int row, int col, int[][] grid) {
        if (x == row - 1)
            return true;
        grid[x][y] = -1;
        for (int i = 0; i < 4; i++) {
            int x1 = x + directions[i][0], y1 = y + directions[i][1];
            if (x1 >= 0 && x1 < row && y1 >= 0 && y1 < col && grid[x1][y1] == 0 && dfs(x1, y1, row, col, grid))
                return true;
        }
        return false;
    }

    private boolean canCross(int[][] cells, int row, int col, int dayNum) {
        int[][] grid = new int[row][col];
        for (int i = 0; i < dayNum; i++)
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        for (int i = 0; i < col; i++) {
            if (grid[0][i] == 0 && dfs(0, i, row, col, grid))
                return true;
        }
        return false;
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int low = 0, high = row * col - 1, res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canCross(cells, row, col, mid)) {
                res = mid;
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return res;
    }
}



/*
 * Approach of DSU (on land cells) from LC Official Editorial
 */
class Solution {

    private int[] parent, rank;

    private int findParent(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void union(int x, int y) {
        int xPar = findParent(x), yPar = findParent(y);
        if (xPar == yPar)
            return;
        if (rank[xPar] > rank[yPar]) {
            rank[xPar] += rank[yPar];
            parent[yPar] = parent[xPar];
        }
        else {
            rank[yPar] += rank[xPar];
            parent[xPar] = parent[yPar];
        }
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        parent = new int[row * col + 1];
        for (int i = 0; i <= row * col; i++)
            parent[i] = i;
        rank = new int[row * col + 1];
        Arrays.fill(rank, 1);
        int[][] grid = new int[row][col], dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = cells.length - 1; i >= 0; i--) {
            int r = cells[i][0] - 1, c = cells[i][1] - 1, cellVal1 = r * col + c;
            grid[r][c] = 1; // here, land = 1 and water = 0
            for (int[] dir : dirs) {
                int r1 = r + dir[0], c1 = c + dir[1], cellVal2 = r1 * col + c1;
                if (r1 >= 0 && r1 < row && c1 >= 0 && c1 < col && grid[r1][c1] == 1)
                    union(cellVal1, cellVal2);
            }
            if (r == 0)
                union(0, cellVal1);
            if (r == row - 1)
                union(row * col, cellVal1);
            if (findParent(0) == findParent(row * col))
                return i;
        }
        return -1;
    }
}



/*
 * Approach of DSU on Water cells (Approach 4) from LC Official Editorial and also -> 
 * https://leetcode.com/problems/last-day-where-you-can-still-cross/solutions/1403988/python-3-union-find-join-the-water-not-the-land-explanation-with-pictures/
 * https://leetcode.com/problems/last-day-where-you-can-still-cross/solutions/1404313/two-union-find-approaches/
 */
class Solution {
	
	private int[] parent, rank;
	
	private int findParent(int x) {
		while (x != parent[x]) {
			parent[x] = parent[parent[x]];
			x = parent[x];
		}
		return x;
	}
	
	private void union(int u, int v) {
		int uPar = findParent(u), vPar = findParent(v);
		if (uPar == vPar)
			return;
		if (rank[uPar] >= rank[vPar]) {
			rank[uPar] += rank[vPar];
			parent[vPar] = parent[uPar];
		}
		else {
			rank[vPar] += rank[uPar];
			parent[uPar] = parent[vPar];
		}
	}
	
    public int latestDayToCross(int row, int col, int[][] cells) {
        parent = new int[row * col + 1];
		for (int i = 0; i <= row * col; i++)
			parent[i] = i;
		rank = new int[row * col + 1];
		Arrays.fill(rank, 1);
		int[][] grid = new int[row][col], dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
		for (int i = 0; i < cells.length; i++) {
			int r1 = cells[i][0] - 1, c1 = cells[i][1] - 1, val1 = r1 * col + c1;
			grid[r1][c1] = 1; // for connecting water cells
			for (int[] dir : dirs) {
				int r2 = r1 + dir[0], c2 = c1 + dir[1], val2 = r2 * col + c2;
				if (r2 >= 0 && r2 < row && c2 >= 0 && c2 < col && grid[r2][c2] == 1)
					union(val1, val2);
			}
			if (c1 == 0)
				union(0, val1);
			if (c1 == col - 1)
				union(row * col, val1);
			if (findParent(0) == findParent(row * col))
				return i;
		}
		return 0;
    }
}
