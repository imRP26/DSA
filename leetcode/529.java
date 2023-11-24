/*
 * https://leetcode.com/problems/minesweeper/
 */



/*
 * Simple but highly likely to be error-prone, stay vigilant while solvign this and taking in all the 
 * (edge) cases - just apply BFS buddy!
 */
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int rows = board.length, cols = board[0].length;
        boolean[][] vis = new boolean[rows][cols];
        Queue<int[]> q = new LinkedList<>();
        q.offer(click);
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        vis[click[0]][click[1]] = true;
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int x = cell[0], y = cell[1];
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
                break;
            }
            int mines = 0;
            for (int[] dir : dirs) {
                int x1 = x + dir[0], y1 = y + dir[1];
                mines += (x1 >= 0 && x1 < rows && y1 >= 0 && y1 < cols && board[x1][y1] == 'M') ? 1 : 0;
            }
            board[x][y] = (mines > 0) ? (char)('0' + mines) : 'B';
            if (board[x][y] == 'B') {
                for (int[] dir1 : dirs) {
                    int x1 = x + dir1[0], y1 = y + dir1[1];
                    if (x1 >= 0 && x1 < rows && y1 >= 0 && y1 < cols && board[x1][y1] == 'E' && !vis[x1][y1]) {
                        mines = 0;
                        for (int[] dir2 : dirs) {
                            int x2 = x1 + dir2[0], y2 = y1 + dir2[1];
                            mines += (x2 >= 0 && x2 < rows && y2 >= 0 && y2 < cols && board[x2][y2] == 'M') ? 1 : 0;
                        }
                        if (mines > 0)
                            board[x1][y1] = (char)('0' + mines);
                        else {
                            board[x1][y1] = 'B';
                            q.offer(new int[] {x1, y1});
                        }
                        vis[x1][y1] = true;
                    }
                }
            }
        }
        return board;
    }
}
