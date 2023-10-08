/*
 * https://leetcode.com/problems/n-queens-ii/
 */



/*
 * Approach of Backtracking from LC Official Editorial!
 */
class Solution {

    private int n, res = 0;

    private void backtrack(int row, Set<Integer> columns, Set<Integer> diagonals, Set<Integer> antiDiagonals, char[][] board) {
        if (row == n) {
            res++;
            return;
        }
        for (int col = 0; col < n; col++) {
            int d = row - col, ad = row + col; // very important trick!
            if (columns.contains(col) || diagonals.contains(d) || antiDiagonals.contains(ad))
                continue;
            board[row][col] = 'Q';
            columns.add(col);
            diagonals.add(d);
            antiDiagonals.add(ad);
            backtrack(row + 1, columns, diagonals, antiDiagonals, board);
            board[row][col] = '.';
            columns.remove(col);
            diagonals.remove(d);
            antiDiagonals.remove(ad);
        }
    }

    public int totalNQueens(int n) {
        this.n = n;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                board[i][j] = '.';
        }
        backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>(), board);
        return res;
    }
}
