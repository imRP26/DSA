/*
 * https://cses.fi/problemset/result/6340962/
 */
import java.io.*;
import java.lang.*;
import java.util.*;


public class DiceCombinations {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		long mod = (long)1e9 + 7, res = 0;
		long[] dp = new long[n + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= 6; j++) {
				if (i - j < 0)
					break;
				dp[i] = (dp[i] + dp[i - j]) % mod;
			} 
		}
		System.out.println(dp[n]);
	}
}
