import java.util.*;

/*
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 */



/* 
 * My Naive, although a bit hatke solution - Exhaustive BFS but return true if 
 * some cell has already been accepted
 */
class Solution1 {
    
    boolean bfs(int[][] heights, int i, int j, boolean[][] taken) {
        int numRows = heights.length, numColumns = heights[0].length;
        boolean[][] visited = new boolean[numRows][numColumns];
        for (boolean[] row : visited)
            Arrays.fill(row, false);
        Queue<int[]> queue = new LinkedList<>();
        boolean flagP = false, flagA = false;
        queue.offer(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            i = cell[0];
            j = cell[1];
            if (visited[i][j])
                continue;
            if (taken[i][j])
                return true;
            visited[i][j] = true;
            if (i == 0 || j == 0)
                flagP = true;
            if (i == numRows - 1 || j == numColumns - 1)
                flagA = true;
            if (flagP && flagA)
                return true;
            if (i >= 1 && !visited[i - 1][j] && heights[i][j] >= heights[i - 1][j])
                queue.offer(new int[] {i - 1, j});
            if (j >= 1 && !visited[i][j - 1] && heights[i][j] >= heights[i][j - 1])
                queue.offer(new int[] {i, j - 1});
            if (i + 1 < numRows && !visited[i + 1][j] && heights[i][j] >= heights[i + 1][j])
                queue.offer(new int[] {i + 1, j});
            if (j + 1 < numColumns && !visited[i][j + 1] && heights[i][j] >= heights[i][j + 1])
                queue.offer(new int[] {i, j + 1});
        }
        return flagP && flagA;
    }
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        int numRows = heights.length, numColumns = heights[0].length;
        boolean[][] taken = new boolean[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (bfs(heights, i, j, taken)) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    result.add(temp);
                    taken[i][j] = true;
                }
            }
        }
        return result;
    }
}



// Another wonderful solution using BFS - ye zyada sahi hai upar wale se!!
class Solution {
    
    void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited) {
        int numRows = heights.length, numColumns = heights[0].length;
        int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] direction : directions) {
                int i = cell[0], j = cell[1], x = i + direction[0], y = j + direction[1];
                if (x < 0 || x == numRows || y < 0 || y == numColumns || heights[x][y] < heights[i][j] || visited[x][y])
                    continue;
                visited[x][y] = true;
                queue.offer(new int[] {x, y});
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        int numRows = heights.length, numColumns = heights[0].length;
        boolean[][] pacific = new boolean[numRows][numColumns];
        boolean[][] atlantic = new boolean[numRows][numColumns];
        Queue<int[]> aQueue = new LinkedList<>();
        Queue<int[]> pQueue = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            pQueue.offer(new int[] {i, 0});
            pacific[i][0] = true;
            aQueue.offer(new int[] {i, numColumns - 1});
            atlantic[i][numColumns - 1] = true;
        }
        for (int i = 0; i < numColumns; i++) {
            pQueue.offer(new int[] {0, i});
            pacific[0][i] = true;
            aQueue.offer(new int[] {numRows - 1, i});
            atlantic[numRows - 1][i] = true;
        }
        bfs(heights, pQueue, pacific);
        bfs(heights, aQueue, atlantic);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    result.add(temp);
                }
            }
        }
        return result;
    }
}



// Solution involving DFS
class Solution3 {

    int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int rows, columns;

    void dfs(int[][] heights, boolean[][] visited, int height, int i, int j) {
        if (i < 0 || j < 0 || i == rows || j == columns || visited[i][j] || heights[i][j] < height)
            return;
        visited[i][j] = true;
        for (int[] direction : directions)
            dfs(heights, visited, heights[i][j], i + direction[0], j + direction[1]);
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        rows = heights.length;
        columns = heights[0].length;
        boolean[][] pacific = new boolean[rows][columns];
        boolean[][] atlantic = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            dfs(heights, pacific, Integer.MIN_VALUE, i, 0);
            dfs(heights, atlantic, Integer.MIN_VALUE, i, columns - 1);
        }
        for (int i = 0; i < columns; i++) {
            dfs(heights, pacific, Integer.MIN_VALUE, 0, i);
            dfs(heights, atlantic, Integer.MIN_VALUE, rows - 1, i);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    result.add(temp);
                }
            }
        }
        return result;
    }
}
