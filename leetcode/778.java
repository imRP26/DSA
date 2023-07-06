/*
 *  https://leetcode.com/problems/swim-in-rising-water/
 */



/*
 * BFS + Binary Search, DFS + Binary Search
 */ 
class Solution {

    private int n;
    private int[][] grid, dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};;

    private boolean canSwimBFS(int t) {
        if (grid[0][0] > t)
            return false;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        boolean[][] vis = new boolean[n][n];
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int x = cell[0], y = cell[1];
            if (x == n - 1 && y == n - 1)
                return true;
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            for (int[] dir : dirs) {
                int x1 = x + dir[0], y1 = y + dir[1];
                if (x1 >= 0 && x1 < n && y1 >= 0 && y1 < n && !vis[x1][y1] && grid[x1][y1] <= t)
                    q.offer(new int[] {x1, y1});
            }
        }
        return false;
    }

    private boolean canSwimDFSUtil(int x, int y, boolean[][] vis, int t) {
        if (x == n - 1 && y == n - 1)
            return true;
        vis[x][y] = true;
        for (int[] d : dirs) {
            int x1 = x + d[0], y1 = y + d[1];
            if (x1 >= 0 && x1 < n && y1 >= 0 && y1 < n && !vis[x1][y1] && grid[x1][y1] <= t && canSwimDFSUtil(x1, y1, vis, t))
                return true;
        }
        return false;
    }

    private boolean canSwimDFS(int t) {
        boolean[][] vis = new boolean[n][n];
        if (grid[0][0] > t)
            return false;
        return canSwimDFSUtil(0, 0, vis, t);
    }

    public int swimInWater(int[][] grid) {
        n = grid.length;
        this.grid = grid;
        int low = 0, high = n * n - 1, res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canSwimDFS(mid)) {
            //if (canSwimBFS(mid)) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res;
    }
}



/*
 * Approach of Dijkstra's algorithm from -> 
 * https://leetcode.com/problems/swim-in-rising-water/solutions/965631/java-3-clean-codes-dijkstra-s-algo-priorityqueue-and-binary-search/
 */
class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
		int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}, distances = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(distances[i], n * n);
		distances[0][0] = grid[0][0];
		TreeSet<int[]> treeset = new TreeSet<>((a, b) -> (a[2] == b[2]) ? (a[0] == b[0]) ? (a[1] - b[1]) : (a[0] - b[0]) : (a[2] - b[2]));
		treeset.add(new int[] {0, 0, grid[0][0]});
		while (!treeset.isEmpty()) {
			int[] temp = treeset.pollFirst();
			int i = temp[0], j = temp[1], t = temp[2];
			if (i == n - 1 && j == n - 1)
				break;
			for (int[] d : dirs) {
				int x = i + d[0], y = j + d[1];
				if (x < 0 || x == n || y < 0 || y == n)
					continue;
				int newDist = t + Math.max(0, grid[x][y] - t);
				if (newDist < distances[x][y]) {
					int[] key = {x, y, distances[x][y]};
					treeset.remove(key);
					key[2] = distances[x][y] = newDist;
					treeset.add(key);
				}
			}
		}
		return distances[n - 1][n - 1];
    }
}



/*
 * Approach of Priority Queue from -> 
 * https://leetcode.com/problems/swim-in-rising-water/solutions/965631/java-3-clean-codes-dijkstra-s-algo-priorityqueue-and-binary-search/
 */
class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
		int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		boolean[][] vis = new boolean[n][n];
		PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
		minPQ.offer(new int[] {0, 0, grid[0][0]});
		vis[0][0] = true;
		while (!minPQ.isEmpty()) {
			int[] temp = minPQ.poll();
			for (int[] d : dirs) {
				int x = d[0] + temp[0], y = d[1] + temp[1];
				if (x >= 0 && x < n && y >= 0 && y < n && !vis[x][y]) {
					vis[x][y] = true;
					int t = Math.max(temp[2], grid[x][y]);
					if (x == n - 1 && y == n - 1)
						return t;
					minPQ.offer(new int[] {x, y, t});
				}
			}
		}
		return 0;
    }
}
