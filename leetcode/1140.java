/*
 * https://leetcode.com/problems/stone-game-ii/
 */



/*
 * DP with Memoization
 * https://www.youtube.com/watch?v=6hu5G-abkdg&t=0s
 * TC = O(N ^ 3)
 */
class Solution1 {

    private int numPiles;
    private int[][] dp;

    private int memoization(int[] piles, int i, int m) {
        if (i >= numPiles)
            return 0;
        if (dp[i][m] != Integer.MIN_VALUE)
            return dp[i][m];
        int answer = Integer.MIN_VALUE, totalStonesPicked = 0;
        for (int j = 0; j < 2 * m; j++) {
            if (i + j >= numPiles)
                break;
            totalStonesPicked += piles[i + j];
            answer = Math.max(answer, totalStonesPicked - memoization(piles, i + j + 1, Math.max(m, j + 1)));
        }
        return dp[i][m] = answer;
    }

    public int stoneGameII(int[] piles) {
        numPiles = piles.length;
        dp = new int[numPiles][3 * numPiles];
        for (int[] row : dp)
            Arrays.fill(row, Integer.MIN_VALUE);
        int val = memoization(piles, 0, 1), totalStones = 0;
        for (int p : piles)
            totalStones += p;
        return (val + totalStones) / 2;
    }
} 
