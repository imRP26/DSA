/*
 * https://atcoder.jp/contests/dp/tasks/dp_c
 */
import java.io.*;
import java.util.*;


public class Main {

    /*
     * DP State :-
     * dp(i, j) = Maximum points gained by Taro from days 0 to i upon choosing option / activity j 
     *            on day i.
     * 
     * DP Transitions :-
     * dp(i, 0) = a[0] + max(dp(i - 1, 1), dp(i - 1, 2))
     * dp(i, 1) = b[0] + max(dp(i - 1, 0), dp(i - 1, 2))
     * dp(i, 2) = c[0] + max(dp(i - 1, 0), dp(i - 1, 1))
     * 
     * Final Answer :-
     * max[dp(n - 1, 0), max{dp(n - 1, 1), dp(n - 1, 2)}]
     */

    private static int[] a, b, c;
    private static int[][] dp; 

    private static int memoize(int i, int j) {
        if (i < 0)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        if (j == 0)
            return dp[i][j] = a[i] + Math.max(memoize(i - 1, 1), memoize(i - 1, 2));
        if (j == 1)
            return dp[i][j] = b[i] + Math.max(memoize(i - 1, 0), memoize(i - 1, 2));
        return dp[i][j] = c[i] + Math.max(memoize(i - 1, 0), memoize(i - 1, 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        a = new int[n];
        b = new int[n];
        c = new int[n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().trim().split(" ");
            a[i] = Integer.parseInt(temp[0]);
            b[i] = Integer.parseInt(temp[1]);
            c[i] = Integer.parseInt(temp[2]);
        }
        dp = new int[n][3];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        int res = Math.max(memoize(n - 1, 0), Math.max(memoize(n - 1, 1), memoize(n - 1, 2)));
        System.out.println(res);
    } 
}


