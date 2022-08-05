import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/coin-change-2/
 */



// Naive Recursion
class Solution1 {

    public int recurse(int amount, int[] coins, int index) {
        if (index < 0 || amount < 0)
            return 0;
        if (amount == 0)
            return 1;
        return recurse(amount, coins, index - 1) + 
               recurse(amount - coins[index], coins, index);
    }

    public int change(int amount, int[] coins) {
        return recurse(amount, coins, coins.length - 1);
    }
}



// Top-Down 2-D DP a.k.a. Memoization
class Solution2 {

    public int memoize(int amount, int[] coins, int[][] dp, int index) {
        if (index == 0)
            return 0;
        if (amount == 0)
            return dp[index][amount] = 1;
        if (dp[index][amount] != -1)
            return dp[index][amount];
        if (amount < coins[index - 1])
            return dp[index][amount] = memoize(amount, coins, dp, index - 1);
        return dp[index][amount] = memoize(amount, coins, dp, index - 1) + memoize(amount - coins[index - 1], coins, dp, index);
    }
    
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        /*
         * in case of 0 (residual) amount, add 1 - logical while calculating and 
         * also can think that when there's no amount there's only 1 way of 
         * making it up - include no coins.
         */
        for (int i = 0; i <= n; i++) 
            dp[i][0] = 1;
        // when there's no coins, no amount can be made up
        for (int i = 0; i <= amount; i++)
            dp[0][i] = 0;
        memoize(amount, coins, dp, n);
        return dp[n][amount];
    }
}



// Bottom-Up 2-D DP
class Solution3 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        /*
         * The positioning of the loops here matters a lot.
         * In the question "Combination Sum 4", the outer loop contains 
         * amount / target and the inner loop iterates over coins / nums.
         * Its the reverse here, since here, we consider that (1, 1, 2) and 
         * (1, 2, 1) are the same, which isn't the case with Combination Sum 4.
         */
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i])
                    dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
