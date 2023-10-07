/*
 * https://leetcode.com/problems/ones-and-zeroes/
 */



/*
 * Simple DP with Memoization!
 */
class Solution {

    /*
     * DP State :-
     * dp(i, rem0, rem1) = size of subset till index 'i' such that 'rem0' and 'rem1' more 0s and 1s
     *                     can be filled up respectively.
     * 
     * DP Transition :-
     * notPick = dp(i + 1, rem0, rem1) // ignoring the current element
     * pick = 1 + dp(i + 1, rem0 - num0, rem1 - num1), rem0 >= num0 and rem1 >= num1 // considering the current element 
     * dp(i, rem0, rem1) = max{ pick, notPick }
     * 
     * Base Case :-
     * dp(n, rem0, rem1) = 0
     * 
     * Final Answer :-
     * dp(0, m, n)
     */

    private int[][][] dp;
    private int[] num0, num1;
    private String[] strs;

    private int memoize(int i, int rem0, int rem1) {
        int n = strs.length;
        if (i == n)
            return 0;
        if (dp[i][rem0][rem1] != -1)
            return dp[i][rem0][rem1];
        int ans = memoize(i + 1, rem0, rem1);
        if (num0[i] <= rem0 && num1[i] <= rem1) // precomputation saves time here!
            ans = Math.max(ans, 1 + memoize(i + 1, rem0 - num0[i], rem1 - num1[i]));
        return dp[i][rem0][rem1] = ans;
    }

    public int findMaxForm(String[] strs, int m, int n) {
        this.strs = strs;
        int numStrs = strs.length;
        dp = new int[numStrs][m + 1][n + 1];
        for (int[][] row1 : dp) {
            for (int[] row2 : row1)
                Arrays.fill(row2, -1);
        }
        num0 = new int[numStrs];
        num1 = new int[numStrs];
        for (int i = 0; i < numStrs; i++) {
            for (char c : strs[i].toCharArray()) {
                if (c == '0')
                    num0[i]++;
                else
                    num1[i]++;
            }
        }
        return memoize(0, m, n);
    }
}
