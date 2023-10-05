/*
 * https://atcoder.jp/contests/dp/tasks/dp_l
 */
import java.io.*;
import java.util.*;


public class Main {

	/*
	 * X + Y = C, where C is a constant, since array elements are fixed!
	 * => Y = C - X
	 * So, if Taro wants to maximize (X - Y), it means that Taro wants to 
	 * maximize (2X - C) and Jiro wants to minimize (2X - C).
	 * Since C is anyways a constant, so it means that Taro wants to maximize 
	 * X and Jiro wants to minimize X.
	 * 
	 * DP State :-
	 * dp(l, r, p) = resulting value of X when the segment is from indices 
	 * 'l' to 'r' (inclusive) and its the turn of the p'th player 
	 * (0 - 1st player, 1 - 2nd player).
	 * 
	 * DP Transitions :-
	 * dp(l, r, 0) = max {nums[l] + dp(l + 1, r, 1), nums[r] + dp(l, r - 1, 1)}
	 * dp(l, r, 1) = min {dp(l + 1, r, 0), dp(l, r - 1, 0)}
	 * 
	 * Base Cases :-
	 * dp(i, i, 0) = nums[i]
	 * dp(i, i, 1) = 0
	 * 
	 * Final Answer :-
	 * 2 * dp(0, n - 1, 0) - sum of all array elements 
	 */

	private static long[][][] dp;
	private static int[] nums;

	private static long memoize(int low, int high, int player) {
		if (low > high)
			return 0;
		if (low == high)
			return dp[low][high][player] = (player == 0) ? nums[low] : 0;
		if (dp[low][high][player] != -1)
			return dp[low][high][player];
		long ans = 0;
		if (player == 0)
			ans = Math.max(nums[low] + memoize(low + 1, high, 1), nums[high] + memoize(low, high - 1, 1));
		else 
			ans = Math.min(memoize(low + 1, high, 0), memoize(low, high - 1, 0));
		return dp[low][high][player] = ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		long sum = 0;
		String[] temp = br.readLine().trim().split(" ");
		nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(temp[i]);
			sum += nums[i];
		}
		dp = new long[n][n][2];
		for (long[][] outerRow : dp) {
			for (long[] innerRow : outerRow)
				Arrays.fill(innerRow, -1);
		}
		long res = memoize(0, n - 1, 0);
		System.out.println(2 * res - sum);
	}
}
