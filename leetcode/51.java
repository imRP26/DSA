/*
 * https://leetcode.com/problems/n-queens/
 */



/*
 * Approach of Backtracking from LC Official Editorial!
 */
class Solution {

    private int n;
    private List<List<String> > res = new ArrayList<>();

    private List<String> createBoard(char[][] state) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            String currRow = new String(state[row]);
            board.add(currRow);
        }
        return board;
    }

    private void backtrack(int row, Set<Integer> columns, Set<Integer> diagonals, Set<Integer> antiDiagonals, char[][] state) {
        if (row == n) {
            res.add(createBoard(state));
            return;
        }
        for (int col = 0; col < n; col++) {
            int currDiagonal = row - col, currAntiDiagonal = row + col; // very important trick!
            if (columns.contains(col) || diagonals.contains(currDiagonal) || antiDiagonals.contains(currAntiDiagonal))
                continue;
            state[row][col] = 'Q';
            columns.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);
            backtrack(row + 1, columns, diagonals, antiDiagonals, state);
            columns.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
            state[row][col] = '.';
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        char[][] emptyBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                emptyBoard[i][j] = '.';
        }
        backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>(), emptyBoard);
        return res;
    }
}
