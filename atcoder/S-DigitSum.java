/*
 * https://atcoder.jp/contests/dp/tasks/dp_s
 */
// Aise bakchod wale TCs mein code fat jaane se bohot boora lagta hai re baba!
import java.io.*;
import java.util.*;


public class Main {
	
    /*
     * DP State :-
     * dp(num, numDigits, modSum, tight) = number of integers <= num such that the sum of their digits 
     *                                     modulo 'd' so far has been 'modSum' with tightness constraint
     *                                     given as 'tight'.
     * 
     * DP Transition :-
     * dp(num, numDigits, modSum, tight) = Sigma {dp(num, numDigits - 1, (modSum + i) % d, tight & (i == lb))}, 
     *                                     for all 'i' between 0 and upperBound digit (both inclusive).
     * 
     * Base Case :-
     * Return 1 when numDigits = 0 and modSum = 0.
     * 
     * Final Answer :-
     * dp(num, num.length(), 0, 1) - dp("0", 1, 0, 1).
     */

	private static int d;
	private static final long mod = (long)1e9 + 7;
	private static long[][][] dp;
	
	private static long memoize(String num, int numDigits, int modSum, int tight) {
		if (numDigits == 0)
			return (modSum == 0) ? 1 : 0;
		if (dp[numDigits][modSum][tight] != -1)
			return dp[numDigits][modSum][tight];
		int ub = (tight == 1) ? num.charAt(num.length() - numDigits) - '0' : 9;
		long ans = 0;
		for (int i = 0; i <= ub; i++) {
			int msum = (modSum + i) % d;
			long val = (tight == 1 && i == ub) ? memoize(num, numDigits - 1, msum, 1) : memoize(num, numDigits - 1, msum, 0);
			ans = (ans + val) % mod;
		}
		return dp[numDigits][modSum][tight] = ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String k = br.readLine().trim();
		d = Integer.parseInt(br.readLine().trim());
		dp = new long[10001][101][2];
		for (long[][] dim1 : dp) {
			for (long[] dim2 : dim1)
				Arrays.fill(dim2, -1);
		}
		long res = memoize(k, k.length(), 0, 1);
		for (long[][] dim1 : dp) {
			for (long[] dim2 : dim1)
				Arrays.fill(dim2, -1);
		}
		long val = memoize("0", 1, 0, 1);
		res = (res - val + mod) % mod;
		System.out.println(res);
	}
}
