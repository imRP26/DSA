/*
 * https://atcoder.jp/contests/dp/tasks/dp_m
 */
import java.io.*;
import java.util.*;


public class Main {

	/*
	 * DP State :-
	 * dp(i, j) = number of ways of distributing j candies when we're at the x'th position.
	 * 
	 * DP Transition :-
	 * dp(i, j) = dp(i - 1, j) + dp(i - 1, j - 1) + dp(i - 1, j - 2) + ... + dp(i - 1, j - a[i])
	 * => dp(i, j) = prefixSum(j) - prefixSum(j - a[i] - 1)
	 * prefixSum will be built upon dp(i - 1).
	 * 
	 * Base Cases :-
	 * dp(i, 0) = 1 for all i
	 * dp(0, 0) = dp(0, 1) = dp(0, 2) = ... = dp(0, a[0]) = 1
	 * 
	 * Final Answer :-
	 * dp(n - 1, k)
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]), k = Integer.parseInt(temp[1]);
		final int mod = (int)1e9 + 7;
		temp = br.readLine().trim().split(" ");
		int[] nums = new int[n];
		for (int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(temp[i]);
		int[][] dp = new int[n][k + 1];
		for (int i = 0; i < n; i++) // default case when no further chocolates remain
			dp[i][0] = 1;
		for (int j = 0; j <= nums[0]; j++) // default case when only 1 child is there
			dp[0][j] = 1;
		for (int i = 1; i < n; i++) {
			int[] prefixSum = new int[k + 1];
			prefixSum[0] = dp[i - 1][0];
			for (int j = 1; j <= k; j++)
				prefixSum[j] = (prefixSum[j - 1] + dp[i - 1][j]) % mod;
			for (int j = 0; j <= k; j++) {
				if (j > nums[i])
					dp[i][j] = (prefixSum[j] + mod - prefixSum[j - nums[i] - 1]) % mod;
				else 
					dp[i][j] = prefixSum[j];
			}
		}
		System.out.println(dp[n - 1][k]);
	}
}
