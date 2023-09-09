/*
 * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
 */



/*
 * Approach 1 (DP) from LC Official Editorial!
 */
class Solution {
    /*
     * DP STATE :- 
     * dp[i] = minimum number of taps to be opened to water the garden from position 0 to position i.
     * dp[0] = 0, meaning no taps need to be opened to water a garden of length 0.
     * 
     * DP TRANSITION FUNCTION :-
     * low = max(0, i - ranges[i])
     * high = min(n, i + ranges[i])
     * dp[high] = min(dp[high], 1 + dp[j]), where low <= j <= high
     * 
     * FINAL ANSWER :-
     * dp[n]
     */
    public int minTaps(int n, int[] ranges) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 5);
        dp[0] = 0;
        for (int i = 0; i < ranges.length; i++) {
            int low = Math.max(0, i - ranges[i]), high = Math.min(n, i + ranges[i]);
            for (int j = low; j <= high; j++)
                dp[high] = Math.min(dp[high], 1 + dp[j]); 
        }
        return dp[n] > n ? -1 : dp[n];
    }
}



/*
 * Approach 2 (Greedy) from ->
 * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/solutions/823419/java-jump-game-ii-o-n-time-and-o-1-space/
 */
class Solution {
	public int minTaps(int n, int[] ranges) {
		for (int i = 0; i < ranges.length; i++) {
			int low = Math.max(0, i - ranges[i]), high = Math.min(n, i + ranges[i]);
			ranges[low] = Math.max(ranges[low], high);			
		}
		int currEnd = 0, taps = 0, currFarthest = 0;
		for (int i = 0; i < n; i++) {
			currFarthest = Math.max(currFarthest, ranges[i]);
			if (i == currEnd) {
				taps++;
				currEnd = currFarthest;
			}
		}
		return currEnd == n ? taps : -1;
	}
}
