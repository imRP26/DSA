/*
 * https://cses.fi/problemset/task/1639
 */
import java.io.*;
import java.util.*;


public class EditDistance {

	/*
	 * DP State :-
	 * dp(i, j) = Edit Distance when the strings str1[0 ... i] and str2[0 ... j] 
	 *			  are taken into consideration.
	 * 
	 * DP Transition :-
	 * if str1[i] == str2[j]:
	 * 	   dp(i, j) = dp(i - 1, j - 1)
	 * else:
	 *	   dp(i, j) = 1 + min{dp(i - 1, j), dp(i, j - 1), dp(i - 1, j - 1)}	
	 * 
	 * Final Answer :-
	 * dp(len1 - 1, len2 - 1)
	 */

	private static int[][] dp;
	
	private static int memoize(String str1, int idx1, String str2, int idx2) {
		if (idx1 < 0)
			return 1 + idx2;
		if (idx2 < 0)
			return 1 + idx1;
		if (dp[idx1][idx2] != -1)
			return dp[idx1][idx2];
		if (str1.charAt(idx1) == str2.charAt(idx2))
			return dp[idx1][idx2] = memoize(str1, idx1 - 1, str2, idx2 - 1);
		int insertionCost = memoize(str1, idx1, str2, idx2 - 1);
		int deletionCost = memoize(str1, idx1 - 1, str2, idx2);
		int replacementCost = memoize(str1, idx1 - 1, str2, idx2 - 1);
		return dp[idx1][idx2] = 1 + Math.min(insertionCost, Math.min(deletionCost, replacementCost));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine().trim(), str2 = br.readLine().trim();
		int len1 = str1.length(), len2 = str2.length();
		dp = new int[len1][len2];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		memoize(str1, len1 - 1, str2, len2 - 1);
		System.out.println(dp[len1 - 1][len2 - 1]);
	}
}
