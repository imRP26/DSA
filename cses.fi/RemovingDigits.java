/*
 * https://cses.fi/problemset/task/1637
 */
import java.io.*;
import java.lang.*;
import java.util.*;


public class RemovingDigits {
	
	private static int[] dp;
	
	private static int memoization(int n) {
		if (n == 0)
			return 0;
		if (dp[n] != -1)
			return dp[n];
		int ans = n + 1, num = n;
		while (num > 0) {
			int d = num % 10;
			if (d != 0)
				ans = Math.min(ans, 1 + memoization(n - d));
			num /= 10;
		}
		return dp[n] = ans;
	}
	
	public static void main(String[] ags) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		dp = new int[n + 1];
		Arrays.fill(dp, -1);
		System.out.println(memoization(n));
	}
}
