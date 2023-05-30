/*
 * https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/
 */



/*
 * My Greedy Solution -> Store the positions of the seats and then do multiplication for 
 * finding the number of way, between seat indices 'i' and 'i + 1', number of ways in which 
 * the plants can be divided = seats.get(i + 1) - seats.get(i).
 */
class Solution {
    public int numberOfWays(String corridor) {
        List<Integer> seats = new ArrayList<>();
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S')
                seats.add(i);
        }
        int numSeats = seats.size();
        if (numSeats < 2 || numSeats % 2 != 0)
            return 0;
        long result = 1, mod = (long)1e9 + 7;
        for (int i = 2; i < numSeats; i += 2)
            result = (result * (seats.get(i) - seats.get(i - 1))) % mod;
        return (int)result;
    }
}



/*
 * Approaach of DP from -> 
 * https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/solutions/1710038/dynamic-programming-solution-memoization-c/
 */
class Solution {

    private final long mod = (long)1e9 + 7;
    private long[][] dp = new long[100001][3];

    private long memoize(String s, int i, int k) {
        if (i >= s.length())
            return (k == 2) ? 1 : 0;
        if (dp[i][k] != -1)
            return dp[i][k];
        char c = s.charAt(i);
        if (k == 2) {
            if (c == 'P')
                return dp[i][k] = (memoize(s, i + 1, 0) % mod + memoize(s, i + 1, k) % mod) % mod;
            return dp[i][k] = memoize(s, i + 1, 1) % mod;
        }
        else if (c == 'S')
            return dp[i][k] = memoize(s, i + 1, k + 1) % mod;
        return dp[i][k] = memoize(s, i + 1, k) % mod;
    }

    public int numberOfWays(String corridor) {
        for (long[] row : dp)
            Arrays.fill(row, -1);
        return (int)memoize(corridor, 0, 0);
    }
}
