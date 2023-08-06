/*
 * https://leetcode.com/problems/find-the-safest-path-in-a-grid/
 */



/*
 * My Approach of Binary Search and Dijkstra from -> 
 * https://leetcode.com/problems/find-the-safest-path-in-a-grid/solutions/3870789/an-easy-approach-involving-modified-bfs-and-binary-search/
 */
class Solution {
    
    private int n;
    private int[][] dist, dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    private boolean isPossible(int x) {
        Queue<int[]> q = new LinkedList<>();
        if (dist[0][0] < x || dist[n - 1][n - 1] < x)
            return false;
        boolean[][] vis = new boolean[n][n];
        q.offer(new int[] {0, 0});
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x1 = temp[0], y1 = temp[1];
            if (vis[x1][y1])
                continue;
            vis[x1][y1] = true;
            for (int[] dir : dirs) {
                int x2 = x1 + dir[0], y2 = y1 + dir[1];
                if (x2 >= 0 && x2 < n && y2 >= 0 && y2 < n && !vis[x2][y2] && dist[x2][y2] >= x)
                    q.offer(new int[] {x2, y2});
            }
        }
        return vis[n - 1][n - 1];
    }
    
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int res = 0, low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        n = grid.size();
        dist = new int[n][n];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if (grid.get(i).get(j) == 1) {
                    q.offer(new int[] {i,  j});
                    dist[i][j] = 0;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x1 = temp[0], y1 = temp[1];
            for (int[] dir : dirs) {
                int x2 = dir[0] + x1, y2 = dir[1] + y1;
                if (x2 >= 0 && x2 < n && y2 >= 0 && y2 < n && dist[x2][y2] > 1 + dist[x1][y1]) {
                    q.offer(new int[] {x2, y2});
                    dist[x2][y2] = 1 + dist[x1][y1]; 
                }
            } 
        }
        for (int i = 0;  i < n; i++) {
            for (int j = 0; j < n; j++) {
                low = Math.min(low, dist[i][j]);
                high = Math.max(high, dist[i][j]);
            }
        }
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(mid)) {
                res = mid;
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return res;
    }
}
