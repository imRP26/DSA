/*
 * https://cses.fi/problemset/task/1635
 */
import java.io.*;
import java.lang.*;
import java.util.*;


public class CoinCombinations1 {
	
	private static int[] dp;
	private final static int mod = (int)1e9 + 7;
	
	private static int memoization(int a, int[] coins) {
		if (dp[a] != -1)
			return dp[a];
		int ans = 0;
		for (int c : coins) {
			if (c > a)
				break;
			ans += memoization(a - c, coins);
			ans %= mod;
		}
		return dp[a] = ans;
	}
	
	public static void main(String[] ags) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]), x = Integer.parseInt(temp[1]);
		temp = br.readLine().trim().split(" ");
		int[] coins = new int[n];
		for (int i = 0; i < n; i++)
			coins[i] = Integer.parseInt(temp[i]);
		dp = new int[x + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		Arrays.sort(coins);
		System.out.println(memoization(x, coins));
	}
}
