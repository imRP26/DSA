/*
 * https://cses.fi/problemset/task/2220/
 */
// Referred from Kartik Arora's video!
import java.io.*;
import java.util.*;


public class CountingNumbers {
	
	private static long[][][][] dp = new long[20][11][2][2];
	
	private static long memoize(String num, int numDigits, int prevDigit, int leadingZero, int tight) {
		if (numDigits == 0)
			return 1;
		if (dp[numDigits][prevDigit][leadingZero][tight] != -1)
			return dp[numDigits][prevDigit][leadingZero][tight];
		int ub = (tight == 1) ? num.charAt(num.length() - numDigits) - '0' : 9;
		long ans = 0;
		for (int i = 0; i <= ub; i++) {
			if (i == prevDigit && leadingZero == 0)
				continue;
			if (leadingZero == 1 && i == 0)
				ans += (tight == 1 && i == ub) ? memoize(num, numDigits - 1, i, 1, 1) : memoize(num, numDigits - 1, i, 1, 0);
			else
				ans += (tight == 1 && i == ub) ? memoize(num, numDigits - 1, i, 0, 1) : memoize(num, numDigits - 1, i, 0, 0);
		}
		return dp[numDigits][prevDigit][leadingZero][tight] = ans;
	} 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		long a = Long.parseLong(temp[0]), b = Long.parseLong(temp[1]);
		long a1 = (a > 0) ? a - 1 : a;
		for (long[][][] dim1 : dp) {
			for (long[][] dim2 : dim1) {
				for (long[] dim3 : dim2)
					Arrays.fill(dim3, -1);
			}
		}
		String s1 = String.valueOf(a1), s2 = String.valueOf(b);
		long val1 = memoize(s1, s1.length(), 10, 1, 1);
		for (long[][][] dim1 : dp) {
			for (long[][] dim2 : dim1) {
				for (long[] dim3 : dim2)
					Arrays.fill(dim3, -1);
			}
		}
		long val2 = memoize(s2, s2.length(), 10, 1, 1);
		if (a != 0)
			System.out.println(val2 - val1);
		else
			System.out.println(val2);
	}
}
