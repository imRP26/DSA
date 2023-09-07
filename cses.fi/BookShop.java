/*
 * https://cses.fi/problemset/task/1158/
 */
import java.io.*;
import java.util.*;

public class BookShop {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int n = Integer.parseInt(temp[0]), x = Integer.parseInt(temp[1]);
        int[] pages = new int[n], prices = new int[n];
        temp = br.readLine().trim().split(" ");
        for (int i = 0; i < n; i++)
            prices[i] = Integer.parseInt(temp[i]);
        temp = br.readLine().trim().split(" ");
        for (int i = 0; i < n; i++)
            pages[i] = Integer.parseInt(temp[i]);
        /*
        int[][] dp = new int[n][x + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= x; j++) {
                if (i == 0) {
                    if (j >= prices[i])
                        dp[i][j] = pages[i];
                    continue;
                }
                dp[i][j] = dp[i - 1][j]; // not take
                if (j >= prices[i]) // take
                    dp[i][j] = Math.max(dp[i][j], pages[i] + dp[i - 1][j - prices[i]]);
            }
        }
        System.out.println(dp[n - 1][x]);
        */
        int[][] dp = new int[2][x + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= x; j++) {
                if (i == 0) {
                    if (j >= prices[i])
                        dp[i % 2][j] = pages[i];
                    continue;
                }
                dp[i % 2][j] = dp[1 - (i % 2)][j]; // not take
                if (j >= prices[i])
                    dp[i % 2][j] = Math.max(dp[i % 2][j], pages[i] + dp[1 - (i % 2)][j - prices[i]]);
            }
        }
        System.out.println(dp[(n - 1) % 2][x]);
    }  
}
