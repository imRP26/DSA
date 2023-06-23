/*
 * https://cses.fi/problemset/task/1634
 */
import java.io.*;
import java.lang.*;
import java.util.*;


public class MinimizingCoins {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]), x = Integer.parseInt(temp[1]);
		temp = br.readLine().trim().split(" ");
		int[] coins = new int[n], dp = new int[1 + x];
		for (int i = 0; i < n; i++)
			coins[i] = Integer.parseInt(temp[i]);
		Arrays.fill(dp, 1 + x);
		dp[0] = 0;
		for (int i = 1; i <= x; i++) {
			for (int j = 0; j < n; j++) {
				if (i >= coins[j])
					dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
			}
		}
		System.out.println(dp[x] > x ? -1 : dp[x]);
	}
}
