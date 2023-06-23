/*
 * https://leetcode.com/problems/coin-change/
 */



/*
 * Approach 1 of BackTracking from LC Official Editorial!
 */
class Solution {

    private int coinChange(int idx, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (idx < coins.length && amount > 0) {
            int maxVal = amount / coins[idx], minCost = Integer.MAX_VALUE;
            for (int i = 0; i <= maxVal; i++) {
                if (amount >= i * coins[idx]) {
                    int res = coinChange(idx + 1, coins, amount - i * coins[idx]);
                    if (res != -1)
                        minCost = Math.min(minCost, res + i);
                }
            }
            return minCost == Integer.MAX_VALUE ? -1 : minCost;
        }
        return -1;
    }

    public int coinChange(int[] coins, int amount) {
        return coinChange(0, coins, amount);
    }
}



/*
 * Approach of DP with Memoization from LC Official Editorial!
 */
class Solution {

    private int coinChange(int[] coins, int remAmount, int[] count) {
        if (remAmount < 0)
            return -1;
        if (remAmount == 0)
            return 0;
        if (count[remAmount - 1] != 0)
            return count[remAmount - 1];
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, remAmount - coin, count);
            if (res >= 0 && res < minCoins)
                minCoins = 1 + res;
        }
        return count[remAmount - 1] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        return coinChange(coins, amount, new int[amount]);
    }
}



/*
 * Slight optimization from above -> included sorting
 */
class Solution {

    private int coinChange(int[] coins, int remAmount, int[] count) {
        if (remAmount < 0)
            return -1;
        if (remAmount == 0)
            return 0;
        if (count[remAmount - 1] != 0)
            return count[remAmount - 1];
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin > remAmount)
                break;
            int res = coinChange(coins, remAmount - coin, count);
            if (res >= 0 && res < minCoins)
                minCoins = 1 + res;
        }
        return count[remAmount - 1] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        Arrays.sort(coins);
        return coinChange(coins, amount, new int[amount]);
    }
}



/*
 * Approach of DP with Tabulation from LC official editorial!
 */
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[1 + amount];
        Arrays.fill(dp, 1 + amount);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i)
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}



/*
 * Approach of BFS from -> 
 * https://leetcode.com/problems/coin-change/solutions/77409/evolve-from-brute-force-to-optimal-a-review-of-all-solutions/
 */
class Solution {
    public int coinChange(int[] coins, int amount) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int res = 0;
        Set<Integer> seen = new HashSet<>();
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                int amnt = q.poll();
                if (amnt == amount)
                    return res;
                if (seen.contains(amnt))
                    continue;
                seen.add(amnt);
                for (int c : coins) {
                    if (c + amnt <= amount)
                        q.offer(c + amnt);
                }
            }
            res++;
        }
        return -1;
    }
}



/*
 * Approach of Double-Ended BFS from -> 
 * https://leetcode.com/problems/coin-change/solutions/77409/evolve-from-brute-force-to-optimal-a-review-of-all-solutions/
 */
class Solution {
    public int coinChange(int[] coins, int amount) {
        Set<Integer> small = new HashSet<>(), large = new HashSet<>();
        small.add(0);
        large.add(amount);
        int steps = 0;
        boolean isBegin = true;
        boolean[] seen = new boolean[1 + amount];
        while (!small.isEmpty() && !large.isEmpty()) {
            if (small.size() > large.size()) {
                Set<Integer> temp = small;
                small = large;
                large = temp;
                isBegin = !isBegin;
            }
            Set<Integer> next = new HashSet<>();
            for (int val : small) {
                if (large.contains(val))
                    return steps;
                if (val < 0 || val > amount || seen[val])
                    continue;
                seen[val] = true;
                for (int c : coins)
                    next.add(isBegin ? val + c : val - c);
            }
            small = next;
            steps++;
        }
        return -1;
    }
}
