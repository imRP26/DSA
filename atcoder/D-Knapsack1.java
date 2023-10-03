/*
 * https://atcoder.jp/contests/dp/tasks/dp_d
 */



/*
 * Simple Knapsack - Memoization, Tabulation and 1D DP versions!!
 */
import java.io.*;
import java.util.*;


public class Main {
    
    private static int[] weights, values;
    private static long[][] dp;
    
    private static long memoize(int idx, int w) {
        if (idx < 0 || w <= 0)
            return 0;
        if (dp[idx][w] != -1)
            return dp[idx][w];
        long ans = memoize(idx - 1, w);
        if (weights[idx] <= w)
            ans = Math.max(ans, values[idx] + memoize(idx - 1, w - weights[idx]));
        return dp[idx][w] = ans;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int n = Integer.parseInt(temp[0]), w = Integer.parseInt(temp[1]);
        weights = new int[n];
        values = new int[n];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().trim().split(" ");
            weights[i] = Integer.parseInt(temp[0]);
            values[i] = Integer.parseInt(temp[1]);
        }
        /*
        dp = new long[n][w + 1];
        for (long[] row : dp)
            Arrays.fill(row, -1);
        System.out.println(memoize(n - 1, w));
        */
        /*
        dp = new long[n + 1][w + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = 0;
        for (int i = 0; i <= w; i++)
            dp[0][i] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                dp[i][j] = dp[i - 1][j];
                if (weights[i - 1] <= j)
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
            }
        }
        System.out.println(dp[n][w]);
        */
        long[] prevDP = new long[w + 1], currDP = new long[w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                currDP[j] = prevDP[j];
                if (weights[i - 1] <= j)
                    currDP[j] = Math.max(currDP[j], values[i - 1] + prevDP[j - weights[i - 1]]);
            }
            for (int j = 1; j <= w; j++)
                prevDP[j] = currDP[j];
        }
        System.out.println(currDP[w]);
    }
}
