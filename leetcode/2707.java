/*
 * https://leetcode.com/problems/extra-characters-in-a-string/
 */



/*
 * Approach (Reversed) of Bottom-UP DP from -> 
 * https://leetcode.com/problems/extra-characters-in-a-string/solutions/3568582/2-approach-dp-memoization-trie-approach-detailed-explanation/
 */
class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        /*
		 * n = len(s)
		 * dp[i] = minimum number of extra chars. left over if the 
		 * substring s[i ... n - 1] is broken up optimally. 
		 */
		int n = s.length();
		Set<String> words = new HashSet<>();
		for (String w : dictionary)
			words.add(w);
		int[] dp = new int[n + 1];
		Arrays.fill(dp, n + 1);
		dp[n] = 0;
		for (int i = n - 1; i >= 0; i--) {
			dp[i] = 1 + dp[i + 1];
			for (int j = i + 1; j <= n; j++) {
				if (words.contains(s.substring(i, j)))
					dp[i] = Math.min(dp[i], dp[j]);
			}
		}
		return dp[0];
    }
}



/*
 * Approach of Bottom-UP DP from -> 
 * https://leetcode.com/problems/extra-characters-in-a-string/solutions/3568582/2-approach-dp-memoization-trie-approach-detailed-explanation/
 */
class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        int len = s.length();
        int[] dp = new int[len + 1];
        Arrays.fill(dp, len + 1);
        dp[0] = 0;
        Set<String> words = new HashSet<>();
        for (String w : dictionary)
            words.add(w);
        for (int i = 1; i <= len; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = i - 1; j >= 0; j--) {
                if (words.contains(s.substring(j, i)))
                    dp[i] = Math.min(dp[i], dp[j]);
            }
        }
        return dp[len];
    }
}



/*
 * 
 */
