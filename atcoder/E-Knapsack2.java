/*
 * https://atcoder.jp/contests/dp/tasks/dp_e
 */



/*
 * Approch referred from Luv's video -> https://www.youtube.com/watch?v=gHVtY5raAQg
 */
import java.io.*;
import java.util.*;


public class Main {
    
    /*
     * DP State :-
     * dp(i, j) = Minimum weight needed to make the value 'j' when scanning from indices 0 to 'i'.
     *
     * DP Transitions :-
     * dp(i, j) = min{dp(i - 1, j), weights[i] + dp(i - 1, j - values[i])} // don't choose, choose
     * 
     * Final Answer :-
     * Start iterating through the DP array from the end, and choose that value 'j' for which 
     * dp[n - 1][value] has the minimum weight value as < the given weight limit!
     */
    
    private static long[][] dp;
    private static int[] weights, values;

    private static long memoize(int i, int j) {
        if (j == 0)
            return 0;
        if (i < 0)
            return (long)1e10;
        if (dp[i][j] != -1)
            return dp[i][j];
        long ans = memoize(i - 1, j);
        if (j >= values[i])
            ans = Math.min(ans, weights[i] + memoize(i - 1, j - values[i]))
        return dp[i][j] = ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int n = Integer.parseInt(temp[0]), w = Integer.parseInt(temp[1]), sumVal = 0;
        weights = new int[n];
        values = new int[n];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().trim().split(" ");
            weights[i] = Integer.parseInt(temp[0]);
            values[i] = Integer.parseInt(temp[1]);
            sumVal += values[i];
        }
        dp = new long[n][sumVal + 1];
        for (long[] row : dp)
            Arrays.fill(row, -1);
        for (int i = sumVal; i >= 0; i--) {
            if (memoize(n - 1, i) <= w) {
                System.out.println(i);
                break;
            }
        }
    }
}
