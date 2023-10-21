/*
 * https://www.spoj.com/problems/ASSIGN/
 */
import java.io.*;
import java.util.*;


class Solution {

    /*
     * DP State :-
     * dp(mask) = Number of assignments of subjects to students from indices '0' to 'i', 
     *            where i = number of set bits in the mask.
     * 
     * DP Transition :-
     * dp(mask) += dp(newMask), where newMask is obtained by OR-in dp(mask) with (1 << j), 
     *             where j ranges from 0 to n.
     * 
     * Base Case :-
     * dp((1 << n) - 1) = 1, meaning all subjects from 0 to n - 1 have been assigned to students, 
     *                       for the current configuration.
     * 
     * Final Solution :-
     * dp(0)
     */

	private static int[][] matrix;
	private static long[] dp;

	private static int numSetBits(int n) {
		int ans = 0;
		while (n > 0) {
			ans += (n % 2 == 1) ? 1 : 0;
			n /= 2;
		}
		return ans;
	}

	private static long memoize(int mask, int n) {
		if (mask == (1 << n) - 1)
			return dp[mask] = 1;
		if (dp[mask] != -1)
			return dp[mask];
		int i = numSetBits(mask);
		long ans = 0;
		for (int j = 0; j < n; j++) {
			if ((mask & (1 << j)) != 0 || matrix[i][j] == 0)
				continue;
			ans += memoize(mask | (1 << j), n);
		}
		return dp[mask] = ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine().trim());
			matrix = new int[n][n];
			for (int i = 0; i < n; i++) {
				String[] temp = br.readLine().trim().split(" ");
				for (int j = 0; j < n; j++)
					matrix[i][j] = Integer.parseInt(temp[j]);
			}
			dp = new long[1 << n];
			Arrays.fill(dp, -1);
			System.out.println(memoize(0, n));
		}
	}
}
