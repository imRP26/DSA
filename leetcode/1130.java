/*
 * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
 */



/*
 * Approach of DP from -> 
 * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/solutions/478708/rz-summary-of-all-the-solutions-i-have-learned-from-discuss-in-python/comments/1521589
 */
class Solution {

    /*
     * DP State :-
     * dp[i][j] = smallest possible sum of the values of all the non-leaf nodes when taking elements from index i to index j of arr.
     *
     * DP Transitions :-
     * dp[i][j] = min{dp[i][k] + dp[k + 1][j] + max(arr[i], ..., arr[k]) * max(arr[k + 1], ..., arr[j])}

     * Final Answer :-
     * dp[0][n - 1]
     */

    private int[][] dp;

    private int memoization(int i, int j, int[] arr) {
        if (i >= j)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) { // if k == j, then maxVal2 gets used as Integer.MIN_VALUE
            int maxVal1 = Integer.MIN_VALUE, maxVal2 = maxVal1;
            for (int idx = i; idx <= k; idx++)
                maxVal1 = Math.max(maxVal1, arr[idx]);
            for (int idx = k + 1; idx <= j; idx++)
                maxVal2 = Math.max(maxVal2, arr[idx]);
            dp[i][j] = Math.min(dp[i][j], memoization(i, k, arr) + memoization(k + 1, j, arr) + maxVal1 * maxVal2);
        }
        return dp[i][j];
    }

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return memoization(0, n - 1, arr);
    }
}




/*
 * Approach of Greedy (Approach 3) from -> 
 * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/solutions/478708/rz-summary-of-all-the-solutions-i-have-learned-from-discuss-in-python/comments/831933
 *
 * Explanations and Proofs for the Greedy Algorithm intuition -> 
 * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/solutions/340014/greedy-python-solution/comments/422237
 * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/solutions/746404/Explanation-of-the-O(N2)-greedy-algorithm/
 */

