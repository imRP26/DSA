/*
 * https://atcoder.jp/contests/dp/tasks/dp_n
 */
import java.io.*;
import java.util.*;;


public class Main {

	/*
	 * DP State :-
	 * dp(low, high) = answer for nums[low ... high]
	 * 
	 * DP Transition :-
	 * dp(low, high) = min{dp(low, x) + dp(x + 1, high) + intervalSum[low ... high]} for all x such that low <= x < high
	 * interValSum(low, high) = intervalSum(low + 1, high) + nums[low]
	 * 
	 * Base Case :-
	 * dp(i, i) = 0 for all i
	 * intervalSum(i, i) = nums[i] for all i
	 * 
	 * Final Answer :-
	 * dp(0, n - 1)
	 */

	private static int[] nums;
	private static long[] prefixSum;
	private static long[][] dp, intervalSum;

	private static long memoize(int left, int right) {
		if (left > right)
			return 0;
		if (left == right)
			return dp[left][left] = 0;
		if (dp[left][right] != (long)1e18)
		    return dp[left][right];
		for (int x = left; x < right; x++) {
			if (left == 0)
				dp[left][right] = Math.min(dp[left][right], memoize(left, x) + memoize(x + 1, right) + prefixSum[right]);
			else 
				dp[left][right] = Math.min(dp[left][right], memoize(left, x) + memoize(x + 1, right) + prefixSum[right] - prefixSum[left - 1]);
		}
		return dp[left][right];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		String[] temp = br.readLine().trim().split(" ");
		nums = new int[n];
		for (int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(temp[i]);
		prefixSum = new long[n];
		prefixSum[0] = nums[0];
		for (int i = 1; i < n; i++)
			prefixSum[i] = nums[i] + prefixSum[i - 1];
		dp = new long[n][n];
		for (long[] row : dp)
			Arrays.fill(row, (long)1e18);
		/*
		intervalSum = new long[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = 0;
			intervalSum[i][i] = nums[i];
		}
		for (int width = 2; width <= n; width++) {
			for (int low = 0, high = width - 1; high < n; low++, high++) {
				intervalSum[low][high] = nums[low] + intervalSum[low + 1][high];
				for (int i = low; i < high; i++)
					dp[low][high] = Math.min(dp[low][high], dp[low][i] + dp[i + 1][high] + intervalSum[low][high]);
			}
		}
		System.out.println(dp[0][n - 1]);
		*/
		System.out.println(memoize(0, n - 1));
	}
}
