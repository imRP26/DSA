import java.util.*;

/*
 * https://leetcode.com/problems/game-of-life/
 */



// Heavy Brute Force Simulation, but got AC :)
class Solution1 {
    
    int rows, columns;
    int[][] result;
    
    int helper(int[][] board, int i, int j, int liveOrDead) {
        int numLiveCells = 0;
        if (i >= 1 && j >= 1 && board[i - 1][j - 1] == 1)
            numLiveCells++;
        if (i >= 1 && board[i - 1][j] == 1)
            numLiveCells++;
        if (i >= 1 && j + 1 < columns && board[i - 1][j + 1] == 1)
            numLiveCells++;
        if (j >= 1 && board[i][j - 1] == 1)
            numLiveCells++;
        if (j + 1 < columns && board[i][j + 1] == 1)
            numLiveCells++;
        if (i + 1 < rows && j >= 1 && board[i + 1][j - 1] == 1)
            numLiveCells++;
        if (i + 1 < rows && board[i + 1][j] == 1)
            numLiveCells++;
        if (i + 1 < rows && j + 1 < columns && board[i + 1][j + 1] == 1)
            numLiveCells++;
        if (liveOrDead == 1)
            return (numLiveCells == 2 || numLiveCells == 3) ? 1 : 0;
        return (numLiveCells == 3) ? 1 : 0;
    }
    
    public void gameOfLife(int[][] board) {
        rows = board.length;
        columns = board[0].length;
        result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                result[i][j] = helper(board, i, j, board[i][j]);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                board[i][j] = result[i][j];
        }
    }
}



/*
 * Method of solving this question in place...
 * [2nd bit, 1st bit] = [next_state, current_state]
 * In the beginning, every cell is either "00" or "01", the 1st bit being 
 * independent of the next state.
 * All the cells change instantly from the 1st to the 2nd state, the state of 
 * the 2nd bit is set after counting the number of neighbors wrt the status of 
 * the 1st bit.
 * Since every cell's 2nd state is dead by default, so we don't consider the 
 * transition 01 -> 00.
 * At the end, every cell's 1st state is deleted by doing ">>1".
 * Transition "01 -> 11" : when board == 1 & liveCells == 2 or 3.
 * Transition "00 -> 10" : when board == 0 & liveCells == 3.
 * To get the current state, we do :- (board[i][j] & 1).
 * To get the next state, we do :- (board[i][j] >> 1).
 */
class Solution2 {

    int findLiveNeighbors(int[][] board, int x, int y) {
        int liveCells = 0, rows = board.length, columns = board[0].length;
        for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, rows - 1); i++) {
            for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, columns - 1); j++)
                liveCells += board[i][j] & 1;
        }
        liveCells -= board[x][y] & 1;
        return liveCells;
    }

    public void gameOfLife(int[][] board) {
        int rows = board.length, columns = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int liveCells = findLiveNeighbors(board, i, j);
                // for the transaction - 01 -> 11
                if (board[i][j] == 1 && (liveCells == 2 || liveCells == 3))
                    board[i][j] = 3; 
                // for the transaction - 00 -> 10
                if (board[i][j] == 0 && liveCells == 3)
                    board[i][j] = 2;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                board[i][j] >>= 1; // setting the next state as the current state
        }
    }
}



/*
 * This following solution gives WA on leetcode, so DO NOT FOLLOW it, but still 
 * keeping this for the SAKE OF LEARNING. 
 * Follow-Up Question -> Assuming that the board is infinite while # of live 
 * cells is finite, how to proceed the state transforming process?
 */
class Solution3 {

    /*
     * Given a set of live points, we're required to calculate the next state.
     * This approach can be used on an infinite number of points instead of being 
     * limited on a large 2D board.
     * Assuming the 2D board is sparse, this method can be more efficient
     * Since we are only given a set of points with no fields to store states, 
     * we can't do it in-place.
     * For each living point, we count the every surrounding 8 points around it, 
     * and accumulate the count to this point in a count table.
     * Through this table, we can determine the next live/dead points according 
     * to the counts.
     */
    Set<Map.Entry<Integer, Integer>> nextState(Set<Map.Entry<Integer, Integer>> livePoints) {
        Map<Map.Entry<Integer, Integer>, Integer> countLiveNeighbors = new HashMap<>();
        for (Map.Entry<Integer, Integer> livePoint : livePoints) {
            int x = livePoint.getKey(), y = livePoint.getValue();
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    Map.Entry<Integer, Integer> point = new AbstractMap.SimpleImmutableEntry<>(i, j);
                    if (!(i != x && j != y))
                        countLiveNeighbors.put(point, countLiveNeighbors.getOrDefault(point, 0) + 1);
                }
            }
        }
        Set<Map.Entry<Integer, Integer>> nextConfig = new HashSet<>();
        for (Map.Entry<Map.Entry<Integer, Integer>, Integer> entry : countLiveNeighbors.entrySet()) {
            Map.Entry<Integer, Integer> point = entry.getKey();
            int count = entry.getValue();
            if (count == 3 || (count == 2 && livePoints.contains(point)))
                nextConfig.add(point);
        }
        return nextConfig;
    }

    public void gameOfLife(int[][] board) {
        /*
         * AbstractMap.SimpleEntry is a built-in implementation. The downside 
         * is that it can not set its value, so we can not change its values 
         * later. Thus we can just use its immutable counterpart 
         * AbstractMap.SimpleImmutableEntry instead.
         */
        Set<Map.Entry<Integer, Integer>> livePoints = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                livePoints.add(new AbstractMap.SimpleImmutableEntry<>(i, j));
        }
        livePoints = nextState(livePoints);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (livePoints.contains(new AbstractMap.SimpleImmutableEntry<>(i, j)))
                    board[i][j] = 1;
                else
                    board[i][j] = 0;
            }
        }
    }
}
