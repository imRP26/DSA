/*
 * https://leetcode.com/problems/stone-game-iv/
 */



/*
 * Naive (DP + Binary Search), got AC though!!
 */ 
class Solution {
    public boolean winnerSquareGame(int n) {
        List<Integer> perfectSquares = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int x = (int)Math.sqrt(i);
            if (x * x == i)
                perfectSquares.add(i);
        }
        int numPerfectSquares = perfectSquares.size();
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (Collections.binarySearch(perfectSquares, i) >= 0)
                dp[i] = true;
            else {
                boolean flag = false;
                for (int j = 0; j < numPerfectSquares; j++) {
                    int x = perfectSquares.get(j);
                    if (x > i)
                        break;
                    if (!dp[i - x]) {
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    dp[i] = true;
            }
        }
        return dp[n];
    }
}



/*
 * Refer this for the below mentioned solutions...
 * https://leetcode.com/problems/stone-game-iv/solutions/811582/stone-game-iv/?orderBy=most_votes
 * DFS + Memoization
 */
class Solution2 {

    private Map<Integer, Boolean> cache;

    private boolean dfs(int n) {
        if (cache.containsKey(n))
            return cache.get(n);
        for (int i = 1; i * i <= n; i++) {
            if (!dfs(n - i * i)) {
                cache.put(n, true);
                return true;
            }
        }
        cache.put(n, false);
        return false;
    }

    public boolean winnerSquareGame(int n) {
        cache = new HashMap<>();
        cache.put(0, false);
        return dfs(n);
    }
}



/*
 * Vanilla DP
 */
class Solution3 {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}



/*
 * Reverse Approach as above, but super fucking awesome!!
 */
class Solution4 {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            if (dp[i])
                continue;
            for (int j = 1; i + j * j <= n; j++)
                dp[i + j * j] = true;
        }
        return dp[n];
    }
}
