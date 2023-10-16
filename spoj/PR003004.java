/*
 * https://www.spoj.com/problems/PR003004/
 */
// Referred from Kartik Arora's solution video!
import java.io.*;
import java.lang.*;
import java.util.*;


class Solution {

	private static long[][] dp;

	private static long sumContri(String num, int numDigits, int tight) {
		if (tight == 0)
			return (long)Math.pow(10, numDigits);
		if (numDigits == 0)
			return 1;
		int ub = num.charAt(num.length() - numDigits) - '0';
		long ans = 0;
		for (int digit = 0; digit <= ub; digit++)
			ans += (digit == ub && tight == 1) ? sumContri(num, numDigits - 1, 1) : sumContri(num, numDigits - 1, 0);
		return ans;
	}

	private static long memoize(String num, int numDigits, int tight) {
		if (numDigits == 0)
			return 0;
		if (dp[numDigits][tight] != -1)
			return dp[numDigits][tight];
		int ub = tight == 1 ? num.charAt(num.length() - numDigits) - '0' : 9;
		long ans = 0;
		for (int digit = 0; digit <= ub; digit++) {
			ans += digit * ((tight == 1 && digit == ub) ? sumContri(num, numDigits - 1, 1) : sumContri(num, numDigits - 1, 0));
			ans += (tight == 1 && digit == ub) ? memoize(num, numDigits - 1, 1) : memoize(num, numDigits - 1, 0);
		}
		return dp[numDigits][tight] = ans;		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		dp = new long[20][2];
		while (t-- > 0) {
			String[] temp = br.readLine().trim().split(" ");
			long num1 = Long.parseLong(temp[0]), num2 = Long.parseLong(temp[1]);
			for (long[] row : dp)
				Arrays.fill(row, -1);
			num1 -= num1 > 0 ? 1 : 0;
			String n1 = String.valueOf(num1), n2 = String.valueOf(num2);
			long val2 = memoize(n2, n2.length(), 1);
			for (long[] row : dp)
				Arrays.fill(row, -1);
			long val1 = memoize(n1, n1.length(), 1);
			System.out.println(val2 - val1);
		}		
	}
}
