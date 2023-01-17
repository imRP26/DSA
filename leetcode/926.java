/*
 * https://leetcode.com/problems/flip-string-to-monotone-increasing/
 */



/*
 * Approach 1 (Sliding Window) from 
 * https://leetcode.com/problems/flip-string-to-monotone-increasing/solutions/2912351/flip-string-to-monotone-increasing/?orderBy=most_votes
 */
class Solution1 {
	public int minFlipsMonoIncr(String s) {
		int len = s.length(), low, high, numFlips = 0, result = 0;
		/*
		 * Assume that firstly, we keep the left window to be empty and keep on expanding 
		 * the right window to be the entire string.
		 * Since, we're concerned about the number of flips as number of 1's in the left 
		 * window + the number of 0's in the right window, so we initially increment the 
		 * number of flips for each '0' that we get.
		 */
		for (high = 0; high < len; high++) {
			if (s.charAt(high) == '0')
				result = Math.max(result, ++numFlips);
		}
		/*
		 * Now, the left window starts expanding, which was initially NULL / empty - 
		 * if an incoming character is '0', then the number of flips is reduced since 
		 * we only want 0's in the left window.
		 */
		for (low = 0; low < len; low++) {
			if (s.charAt(low) == '0')
				result = Math.min(result, --numFlips);
			else
				result = Math.min(result, ++numFlips);
		}
		return result;
	}
}



/*
 * Approach 2 (DP) from 
 * https://leetcode.com/problems/flip-string-to-monotone-increasing/solutions/2912351/flip-string-to-monotone-increasing/?orderBy=most_votes
 */
class Solution2 {
	public int minFlipsMonoIncr(String s) {
		/*
		 * dp[i] = minimum no. of flips to make the prefix of s of length i (substring 
		 * of indices [0, i) ) monotone increasing.
		 * dp[0] = 0, an empty string is considered as 'monotone increasing' by default.
		 * For i > 0, if s[i - 1] = '1', then dp[i] = dp[i - 1], since a '1' can always 
		 * be appended at the end of a pre-existing monotone increasing sequence to make 
		 * it monotonically increasing.
		 * For i > 0, if s[i - 1] = '0', then dp[i] = dp[i - 1] + 1, if we decide to 
		 * flip this bit, otherwise, dp[i] = num1, where num1 = no. of 1's that were 
		 * encountered in s[0 .. (i - 2)].
		 * Final result = dp[s.length()]
		 */
		int len = s.length(), numOnesSoFar = 0;
		int[] dp = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			if (s.charAt(i - 1) == '1') {
				dp[i] = dp[i - 1];
				numOnesSoFar++;
			}
			else
				dp[i] = Math.min(dp[i - 1] + 1, numOnesSoFar);
		}
		return dp[len];
	}
}



/*
 * Making the above DP as space-optimized, concept remains the same fully!
 */
class Solution3 {
	public int minFlipsMonoIncr(String s) {
		int result = 0, numOnesSeenSoFar = 0;
		for (int i = 1; i <= s.length(); i++) {
			if (s.charAt(i - 1) == '1')
				numOnesSeenSoFar++;
			else
				result = Math.min(result + 1, numOnesSeenSoFar);
		}
		return result;
	}
}
