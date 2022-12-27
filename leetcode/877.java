/*
 * https://leetcode.com/problems/stone-game/
 */



/*
 * Just return True, since Alice controls the game, as referred from 
 * https://www.youtube.com/watch?v=uhgdXOlGYqE
 * https://leetcode.com/problems/stone-game/solutions/154610/dp-or-just-return-true/
 */ 
class Solution1 {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}



/*
 * Min-Max DP
 * https://leetcode.com/problems/stone-game/solutions/154716/stone-game/
 * https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/
 */
class Solution2 {

    private int[][] dp;

    private int memoization(int[] piles, int i, int j) {
        if (i >= piles.length || j < 0 || i > j)
            return 0;
        if (i == j)
            return dp[i][j] = piles[i];
        if (j == i + 1)
            return dp[i][j] = Math.max(piles[i], piles[j]);
        if (dp[i][j] != -1)
            return dp[i][j];
        int score1 = piles[i] + Math.min(memoization(piles, i + 2, j), memoization(piles, i + 1, j - 1));
        int score2 = piles[j] + Math.min(memoization(piles, i + 1, j - 1), memoization(piles, i, j - 2));
        return dp[i][j] = Math.max(score1, score2);
    }

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        int scoreAlice = memoization(piles, 0, n - 1), scoreBob = 0;
        for (int p : piles)
            scoreBob += p;
        scoreBob -= scoreAlice;
        return scoreAlice > scoreBob;
    }
}



/*
 * DP with Tabulation
 */
class Solution3 {
    public boolean stoneGame(int[] piles) {
        int n = piles.length, scoreBob = 0;
        for (int p : piles)
            scoreBob += p;
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j)
                    dp[i][j] = piles[i];
                else if (j == i + 1)
                    dp[i][j] = Math.max(piles[i], piles[j]);
                else {
                    int score1 = piles[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
                    int score2 = piles[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]);
                    dp[i][j] = Math.max(score1, score2);
                }
            }
        }
        scoreBob -= dp[0][n - 1];
        return dp[0][n - 1] > scoreBob;
    }
}
