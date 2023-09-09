/*
 * https://cses.fi/problemset/task/1746/
 */
import java.io.*;
import java.util.*;


public class ArrayDescription {

    private static long[][] dp;
    private static final long mod = (long)1e9 + 7;

    /*
     * DP State :-
     * dp(i, x) = Number of valid arrays of size i such that the value present at the 
     *            (i - 1)'th position is x.
     * 
     * DP Transitions :-
     * dp(i, x) = 0, if nums[i - 1] != 0 && nums[i - 1] != x.
     *          = dp(i - 1, x - 1) + dp(i - 1, x) + dp(i - 1, x + 1), else.
     * 
     * Base Case :-
     * dp(1, x) = 1, if nums[0] == 0 or nums[0] == x.
     *          = 0, else.
     * 
     * Final Answer :-
     * dp(n, 1) + dp(n, 2) + dp(n, 3) + ... + dp(n, m)
     */

    private static long memoization(int n, int x, int m, int[] nums) {
        if (n < 1 || x < 1 || x > m) // index mismatches
            return 0;
        if (n == 1) // base case - size of array in consideration is 1
            return (nums[n - 1] == 0 || nums[n - 1] == x) ? 1 : 0;
        if (nums[n - 1] != 0 && nums[n - 1] != x) // if the previous element is neither 0 nor x, then the DP state becomes invalid
            return 0;
        if (dp[n][x] != -1) // Memoization
            return dp[n][x];
        long ans = memoization(n - 1, x - 1, m, nums) % mod; // Absolute difference <= 1
        ans = (ans + memoization(n - 1, x, m, nums)) % mod;
        ans = (ans + memoization(n - 1, x + 1, m, nums)) % mod;
        return dp[n][x] = ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int n = Integer.parseInt(temp[0]), m = Integer.parseInt(temp[1]);
        temp = br.readLine().trim().split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(temp[i]);
        dp = new long[n + 2][m + 2];
        for (long[] row : dp)
            Arrays.fill(row, -1);
        long res = 0;
        for (int x = 1; x <= m; x++)
            res = (res + memoization(n, x, m, nums)) % mod;
        System.out.println(res); 
    }  
}
