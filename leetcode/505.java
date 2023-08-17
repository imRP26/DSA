/*
 * https://leetcode.com/problems/the-maze-ii/
 */



/*
 * Approach of DFS from LC Official Editorial!
 */
class Solution {

    private int rows, cols;
    private int[][] dist, dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private void dfs(int[][] maze, int[] curr, int[] dest) {
        for (int[] dir : dirs) {
            int x = curr[0] + dir[0], y = curr[1] + dir[1], count = 0;
            while (x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
                count++;
            }
            if (dist[curr[0]][curr[1]] + count < dist[x - dir[0]][y - dir[1]]) {
                dist[x - dir[0]][y - dir[1]] = dist[curr[0]][curr[1]] + count;
                dfs(maze, new int[] {x - dir[0], y - dir[1]}, dest);
            }
        }
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        rows = maze.length;
        cols = maze[0].length;
        dist = new int[rows][cols];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;
        dfs(maze, start, destination);
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }
}



/*
 * Approach of BFS from LC Official Editorial!
 */
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length, cols = maze[0].length;
        int[][] dist = new int[rows][cols], dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            for (int[] dir : dirs) {
                int x = cell[0] + dir[0], y = cell[1] + dir[1], count = 0;
                while (x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (dist[x - dir[0]][y - dir[1]] > dist[cell[0]][cell[1]] + count) {
                    dist[x - dir[0]][y - dir[1]] = dist[cell[0]][cell[1]] + count;
                    q.offer(new int[] {x - dir[0], y - dir[1]});
                }
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }
}



/*
 * Approach of Dijkstra's algo and PriorityQueue from LC Official Editorial!
 */
class Solution {

    private int rows, cols;
    private int[][] dist, dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private void dijkstra(int[][] maze, int[] start) {
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        minPQ.offer(new int[] {start[0], start[1], 0});
        while (!minPQ.isEmpty()) {
            int[] cell = minPQ.poll();
            if (dist[cell[0]][cell[1]] < cell[2])
                continue;
            for (int[] dir : dirs) {
                int x = cell[0] + dir[0], y = cell[1] + dir[1], count = 0;
                while (x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (dist[cell[0]][cell[1]] + count < dist[x - dir[0]][y - dir[1]]) {
                    dist[x - dir[0]][y - dir[1]] = dist[cell[0]][cell[1]] + count;
                    minPQ.offer(new int[] {x - dir[0], y - dir[1], dist[x - dir[0]][y - dir[1]]});
                }
            }
        }
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        rows = maze.length;
        cols = maze[0].length;
        dist = new int[rows][cols];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;
        dijkstra(maze, start);
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }
}
