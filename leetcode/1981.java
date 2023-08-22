/*
 * https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/
 */



/*
 * Simple Approach of DP using Memoization from -> 
 * https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/solutions/1418817/java-dp-memoization/
 */
class Solution {

    /*
     * DP State :-
     * dp[rowIdx][currSum] = Minimum difference (to be) obtained with the target for the row 'rowIdx' 
     * when the value of 'sum' obtained so far is 'currSum'.
     *
     * DP Transitions :-
     * dp[idx][currSum] = min{dp[idx + 1][currSum + mat[idx][i]] for all i between 0 and numColumns 
     * (inclusive)}
     *
     * Final Answer :-
     * dp[0][0] -> This is the state from which memoization is called initially!
     */

    private int memoization(int[][] mat, int idx, int target, int currSum, Integer[][] dp) {
        if (idx == mat.length)
            return Math.abs(currSum - target);
        if (dp[idx][currSum] != null)
            return dp[idx][currSum];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < mat[0].length; i++)
            res = Math.min(res, memoization(mat, idx + 1, target, currSum + mat[idx][i], dp));
        return dp[idx][currSum] = res;
    }

    public int minimizeTheDifference(int[][] mat, int target) {
        Integer[][] dp = new Integer[mat.length][4901];
        return memoization(mat, 0, target, 0, dp);
    }
}



/*
 * Another DP with memoization approach from -> 
 * https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/solutions/1418614/java-dp-code-with-proper-comments-and-explanation/
 */
class Solution {

    private int res = Integer.MAX_VALUE;
    private boolean[][] dp;

    private void memoization(int[][] mat, int rowIdx, int currSum, int target) {
        if (dp[rowIdx][currSum])
            return;
        if (rowIdx == mat.length - 1) {
            for (int i = 0; i < mat[0].length; i++)
                res = Math.min(res, Math.abs(currSum + mat[rowIdx][i] - target));
        }
        else {
            for (int i = 0; i < mat[0].length; i++)
                memoization(mat, rowIdx + 1, currSum + mat[rowIdx][i], target);
        }
        dp[rowIdx][currSum] = true;
    }

    public int minimizeTheDifference(int[][] mat, int target) {
        dp = new boolean[mat.length][4901];
        memoization(mat, 0, 0, target);
        return res;
    }
}



/*
 * An awesome ad-hoc Approach using HashSets from -> 
 * https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/solutions/1418747/easy-to-understand-java-solution-with-explanation/
 */
class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        Set<Integer> currSumSet = new HashSet<>(), newSumSet = new HashSet<>();
        currSumSet.add(0);
        for (int i = 0; i < mat.length; i++) { // O(rows)
            newSumSet = new HashSet<>();
            for (Integer v : currSumSet) { // O(rows * cols)
                for (int j = 0; j < mat[i].length; j++)
                    newSumSet.add(v + mat[i][j]);
            }
            // removal of all the elements > the smallest element > the target
            int minExceedingSum = Integer.MAX_VALUE;
            for (Integer v : newSumSet) { // O(rows * cols)
                if (v >= target)
                    minExceedingSum = Math.min(minExceedingSum, v);
            }
            currSumSet = new HashSet<>();
            for (Integer v : newSumSet) { // O(rows * cols)
                if (v <= minExceedingSum)
                    currSumSet.add(v);
            }
        }
        int minDiff = Integer.MAX_VALUE;
        for (Integer i : currSumSet) // O(rows * cols)
            minDiff = Math.min(minDiff, Math.abs(i - target));
        return minDiff;
    }
}
