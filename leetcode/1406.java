/*
 * https://leetcode.com/problems/stone-game-iii/
 */



/*
 * Recursion (TLE), Memoization based DP (AC)
 */
class Solution1 {

    private int recursion(int[] stoneValue, int index) {
        int numStones = stoneValue.length;
        if (index >= numStones)
            return 0;
        int difference = Integer.MIN_VALUE;
        difference = Math.max(difference, stoneValue[index] - recursion(stoneValue, index + 1));
        if (index + 1 < numStones)
            difference = Math.max(difference, stoneValue[index] + stoneValue[index + 1] - recursion(stoneValue, index + 2));
        if (index + 2 < numStones)
            difference = Math.max(difference, stoneValue[index] + stoneValue[index + 1]+ stoneValue[index + 2] - recursion(stoneValue, index + 3));
        return difference;
    }

    private int[] dp;
    private int n;

    private int memoization(int[] stoneValue, int i) {
        if (i >= n)
            return 0;
        if (dp[i] != Integer.MIN_VALUE)
            return dp[i];
        int diff = Integer.MIN_VALUE;
        diff = Math.max(diff, stoneValue[i] - memoization(stoneValue, i + 1));
        if (i + 1 < n)
            diff = Math.max(diff, stoneValue[i] + stoneValue[i + 1] - memoization(stoneValue, i + 2));
        if (i + 2 < n)
            diff = Math.max(diff, stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - memoization(stoneValue, i + 3));
        return dp[i] = diff;
    }

    public String stoneGameIII(int[] stoneValue) {
        //int answer = recursion(stoneValue, 0);
        n = stoneValue.length;
        dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        int answer = memoization(stoneValue, 0);
        if (answer > 0) 
            return "Alice";
        if (answer == 0)
            return "Tie";
        return "Bob";
    }
}



/*
 * Bottom-Up DP
 */
class Solution2 {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 3];
        for (int i = n - 1; i >= 0; i--) {
            int diff = Integer.MIN_VALUE;
            diff = Math.max(diff, stoneValue[i] - dp[i + 1]);
            if (i + 1 < n)
                diff = Math.max(diff, stoneValue[i] + stoneValue[i + 1] - dp[i + 2]);
            if (i + 2 < n)
                diff = Math.max(diff, stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]);
            dp[i] = diff;
        }
        if (dp[0] > 0)
            return "Alice";
        if (dp[0] == 0)
            return "Tie";
        return "Bob";
    }
}
