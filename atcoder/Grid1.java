/*
 * https://atcoder.jp/contests/dp/tasks/dp_h
 */
import java.io.*;
import java.util.*;


public class Main {
    
    private static int mod = (int)1e9 + 7;
    private static char[][] grid;
    private static int[][] dp;
    
    /*
     * DP State :-
     * dp[i][j] = Number of ways of reaching the cell (i, j) from (0, 0)
     * 
     * DP Transitions :-
     * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     * 
     * Base Case :-
     * dp[0][0] = 1
     * 
     * Final Answer :-
     * dp[rows - 1][cols - 1]
     */

    private static int memoization(int i, int j) {
        if (i == 0 && j == 0)
            return dp[i][j] = 1;
        if (i < 0 || j < 0)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int ans = 0;
        if (i > 0 && grid[i - 1][j] == '.')
            ans = (ans + memoization(i - 1, j)) % mod;
        if (j > 0 && grid[i][j - 1] == '.')
            ans = (ans + memoization(i, j - 1)) % mod;
        return dp[i][j] = ans;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int rows = Integer.parseInt(temp[0]), cols = Integer.parseInt(temp[1]);
        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String row = br.readLine().trim();
            grid[i] = row.toCharArray();
        }
        dp = new int[rows][cols];
        /*
        for (int[] row : dp)
            Arrays.fill(row, -1);
        System.out.println(memoization(rows - 1, cols - 1));
        */
       for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = 1;
                else {
                    if (i > 0 && grid[i - 1][j] == '.')
                        dp[i][j] = (dp[i][j] + dp[i - 1][j]) % mod;
                    if (j > 0 && grid[i][j - 1] == '.')
                        dp[i][j] = (dp[i][j] + dp[i][j - 1]) % mod;
                }
            }
        }
        System.out.println(dp[rows - 1][cols - 1]);
    }
}
