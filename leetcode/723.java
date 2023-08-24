/*
 * https://leetcode.com/problems/candy-crush/
 */



/*
 * Approach 1 from LC Official Editorial! - simple simulation using modular steps!!
 */
class Solution {

    private int rows, cols;

    Set<Pair<Integer, Integer> > find(int[][] board) {
        Set<Pair<Integer, Integer> > crushedSet = new HashSet<>();
        for (int i = 1; i < rows - 1; i++) { // vertically adjacent candies
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 0)
                    continue;
                if (board[i][j] == board[i - 1][j] && board[i][j] == board[i + 1][j]) {
                    crushedSet.add(new Pair<>(i, j));
                    crushedSet.add(new Pair<>(i - 1, j));
                    crushedSet.add(new Pair<>(i + 1, j));
                }
            }
        }
        for (int i = 0; i < rows; i++) { // horizontally adjacent candies
            for (int j = 1; j < cols - 1; j++) {
                if (board[i][j] == 0)
                    continue;
                if (board[i][j] == board[i][j - 1] && board[i][j] == board[i][j + 1]) {
                    crushedSet.add(new Pair<>(i, j));
                    crushedSet.add(new Pair<>(i, j - 1));
                    crushedSet.add(new Pair<>(i, j + 1));
                }
            }
        }
        return crushedSet;
    }

    private void crush(int[][] board, Set<Pair<Integer, Integer> > crushedSet) {
        for (Pair<Integer, Integer> pair : crushedSet) {
            int row = pair.getKey(), col = pair.getValue();
            board[row][col] = 0;
        }
    }

    private void drop(int[][] board) {
        for (int j = 0; j < cols; j++) {
            int lowestZero = -1;
            for (int i = rows - 1; i >= 0; i--) {
                if (board[i][j] == 0)
                    lowestZero = Math.max(lowestZero, i);
                else if (lowestZero >= 0) {
                    int temp = board[i][j];
                    board[i][j] = board[lowestZero][j];
                    board[lowestZero][j] = temp;
                    lowestZero--;
                }
            }
        }
    }

    public int[][] candyCrush(int[][] board) {
        rows = board.length;
        cols = board[0].length;
        Set<Pair<Integer, Integer> > crushedSet = find(board);
        while (!crushedSet.isEmpty()) {
            crush(board, crushedSet);
            drop(board);
            crushedSet = find(board);
        }
        return board;
    }
}



/*
 * Approach 2 from LC Official Editorial - In-Place Modification!!
 */
class Solution {

    private int rows, cols;

    private boolean findAndCrush(int[][] board) {
        boolean complete = true;
        for (int i = 1; i < rows - 1; i++) { // vertically adjacent candies
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 0)
                    continue;
                if (Math.abs(board[i][j]) == Math.abs(board[i - 1][j]) && Math.abs(board[i][j]) == Math.abs(board[i + 1][j])) {
                    board[i][j] = -Math.abs(board[i][j]);
                    board[i - 1][j] = -Math.abs(board[i - 1][j]);
                    board[i + 1][j] = -Math.abs(board[i + 1][j]);
                    complete = false;
                }
            }
        }
        for (int i = 0; i < rows; i++) { // horizontally adjacent candies 
            for (int j = 1; j < cols - 1; j++) {
                if (board[i][j] == 0)
                    continue;
                if (Math.abs(board[i][j]) == Math.abs(board[i][j - 1]) && Math.abs(board[i][j]) == Math.abs(board[i][j + 1])) {
                    board[i][j] = -Math.abs(board[i][j]);
                    board[i][j - 1] = -Math.abs(board[i][j - 1]);
                    board[i][j + 1] = -Math.abs(board[i][j + 1]);
                    complete = false;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                board[i][j] = board[i][j] < 0 ? 0 : board[i][j];
        }
        return complete;
    }

    private void drop(int[][] board) {
        for (int j = 0; j < cols; j++) {
            int lowestZero = -1;
            for (int i = rows - 1; i >= 0; i--) {
                if (board[i][j] == 0)
                    lowestZero = Math.max(lowestZero, i);
                else if (lowestZero >= 0) {
                    int temp = board[i][j];
                    board[i][j] = board[lowestZero][j];
                    board[lowestZero][j] = temp;
                    lowestZero--;
                }
            }
        }
    }

    public int[][] candyCrush(int[][] board) {
        rows = board.length;
        cols = board[0].length;
        while (!findAndCrush(board))
            drop(board);
        return board;
    }
}
