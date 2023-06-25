/*
 * https://leetcode.com/problems/count-all-possible-routes/
 */



/*
 * Approach of DP with Memoization from LC Official Editorial
 */
class Solution {

    private int mod = 1_000_000_007;
    private int[][] dp = new int[100][201];

    private int memoization(int[] locations, int currCity, int finish, int remFuel) {
        if (remFuel < 0)
            return 0;
        if (dp[currCity][remFuel] != -1)
            return dp[currCity][remFuel];
        int ans = 0;
        if (currCity == finish)
            ans++;
        for (int i = 0; i < locations.length; i++) { 
            if (i == currCity)
                continue;
            ans += memoization(locations, i, finish, remFuel - Math.abs(locations[currCity] - locations[i]));
            ans %= mod;
        }
        return dp[currCity][remFuel] = ans;
    }

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return memoization(locations, start, finish, fuel);
    }
}



/*
 * Approach of DP with Tabulation from LC Official Editorial
 * If possible, try to recollect as to why the fuel loop is the outermost 1 - hint : 
 * we're going bottom-up!
 */
class Solution {
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length, mod = (int)1e9 + 7;
        int[][] dp = new int[n][1 + fuel];
        Arrays.fill(dp[finish], 1);
        for (int k = 0; k <= fuel; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j)
                        continue;
                    int fuelDiff = Math.abs(locations[i] - locations[j]);
                    if (fuelDiff <= k)
                        dp[i][k] = (dp[i][k] + dp[j][k - fuelDiff]) % mod;
                }
            }
        }
        return dp[start][fuel];
    }
}
