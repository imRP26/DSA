/*
 * https://cses.fi/problemset/task/1636/
 */
import java.io.*;
import java.lang.*;
import java.util.*;


public class CoinCombinations2 {
	public static void main(String[] ags) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]), x = Integer.parseInt(temp[1]), mod = (int)1e9 + 7;
		temp = br.readLine().trim().split(" ");
		int[] coins = new int[n];
		for (int i = 0; i < n; i++)
			coins[i] = Integer.parseInt(temp[i]);
		int[][] dp = new int[n + 1][x + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= x; j++) {
				dp[i][j] = dp[i - 1][j];
				int k = j - coins[i - 1];
				if (k >= 0)
					dp[i][j] = (dp[i][j] + dp[i][k]) % mod;
			}
		}
		System.out.println(dp[n][x]);
	}
}
