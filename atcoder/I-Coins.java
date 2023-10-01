/*
 * https://atcoder.jp/contests/dp/tasks/dp_i
 */
import java.io.*;
import java.util.*;


class Main {

    /*
     * DP State :-
     * dp[i][j] = probability of receiving 'j' heads till index 'i'
     *
     * DP Transitions :-
     * dp[i][j] = Probability of receiving a Heads at index 'i' 
                  + 
                  Probability of receiving a Tails at index 'i'
     * dp[i][j] = (1 - p[i]) * dp[i - 1][j] + p[i] * dp[i - 1][j - 1]
     * 
     * Base Case :-
     * dp[0][0] = 1 - p[0]
     * dp[0][1] = p[0]
     * 
     * Final Answer :-
     * Summation (dp[n - 1][i]), where i >= n / 2 + 1
     */

    private static double[] prob;
    private static double[][] dp;

    private static double memoization(int i, int j) {
        if (j > i + 1 || i < 0)
            return 0;
        if (dp[i][j] >= 0)
            return dp[i][j];
        dp[i][j] = (1 - prob[i]) * memoization(i - 1, j);
        if (j > 0)
            dp[i][j] += prob[i] * memoization(i - 1, j - 1);
        return dp[i][j];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        prob = new double[n];
        String[] temp = br.readLine().trim().split(" ");
        for (int i = 0; i < n; i++)
            prob[i] = Double.parseDouble(temp[i]);
        dp = new double[n][n + 1];
        for (double[] row : dp)
            Arrays.fill(row, -1);
        dp[0][0] = 1 - prob[0];
        dp[0][1] = prob[0];
        double res = 0;
        for (int i = n / 2 + 1; i <= n; i++)
            res += memoization(n - 1, i); // very very important for memoization!!!
        /*
        //BOTTOM-UP DP
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] += (1 - prob[i]) * dp[i - 1][j]; // tails
                if (j > 0) // heads
                    dp[i][j] += prob[i] * dp[i - 1][j - 1];
            }
        }
        for (int i = n / 2 + 1; i <= n; i++)
            res += dp[n - 1][i];
        */
        System.out.println(res);
    }
}
