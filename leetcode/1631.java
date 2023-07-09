/*
 * https://leetcode.com/problems/path-with-minimum-effort/
 */



/*
 * Approach of Binary Search + BFS, Binary Search + DFS
 */
class Solution {

    private int rows, cols;
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}, heights;

    private boolean bfs(int t) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[rows][cols];
        q.offer(new int[] {0, 0});
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x1 = temp[0], y1 = temp[1];
            if (x1 == rows - 1 && y1 == cols - 1)
                return true;
            if (vis[x1][y1])
                continue;
            vis[x1][y1] = true;
            for (int[] d : dirs) {
                int x2 = x1 + d[0], y2 = y1 + d[1];
                if (x2 >= 0 && x2 < rows && y2 >= 0 && y2 < cols && !vis[x2][y2] && Math.abs(heights[x1][y1] - heights[x2][y2]) <= t)
                    q.offer(new int[] {x2, y2});
            }
        }
        return false;
    }

    private boolean dfs(int t, int x, int y, boolean[][] vis) {
        if (x == rows - 1 && y == cols - 1)
            return true;
        vis[x][y] = true;
        for (int[] d : dirs) {
            int x1 = x + d[0], y1 = y + d[1];
            if (x1 >= 0 && x1 < rows && y1 >= 0 && y1 < cols && !vis[x1][y1] && Math.abs(heights[x][y] - heights[x1][y1]) <= t && dfs(t, x1, y1, vis))
                return true;
        }
        return false;
    }

    public int minimumEffortPath(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;
        this.heights = heights;
        int low = 0, high = (int)1e6, res = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            /*
            if (bfs(mid)) {
                res = mid;
                high = mid - 1;
            }
            */
            if (dfs(mid, 0, 0, new boolean[rows][cols])) {
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
 * Approach 2 of Dijkstra's Algorithm from LC Official Editorial!
 */
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length, cols = heights[0].length;
        int[][] diffMatrix = new int[rows][cols], dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};;
        for (int[] row : diffMatrix)
            Arrays.fill(row, Integer.MAX_VALUE);
        diffMatrix[0][0] = 0;
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        boolean[][] vis = new boolean[rows][cols];
        minPQ.offer(new int[] {0, 0, diffMatrix[0][0]});
        while (!minPQ.isEmpty()) {
            int[] cell = minPQ.poll();
            int x1 = cell[0], y1 = cell[1], diff = cell[2];
            if (x1 == rows - 1 && y1 == cols - 1)
                return diff;
            vis[x1][y1] = true;
            for (int[] d : dirs) {
                int x2 = x1 + d[0], y2 = y1 + d[1];
                if (x2 >= 0 && x2 < rows && y2 >= 0 && y2 < cols && !vis[x2][y2]) {
                    int maxDiff = Math.max(Math.abs(heights[x2][y2] - heights[x1][y1]), diffMatrix[x1][y1]);
                    if (diffMatrix[x2][y2] > maxDiff) {
                        diffMatrix[x2][y2] = maxDiff;
                        minPQ.offer(new int[] {x2, y2, maxDiff});
                    }
                }
            }
        }
        return diffMatrix[rows - 1][cols - 1];
    }
}
