/*
 * https://leetcode.com/problems/best-team-with-no-conflicts/description/
 */



/*
 * My own approach that got AC!! - Basically sorting wrt ages and then applying LIS
 */ 
class Solution1 {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length, result = 0;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(new int[] {ages[i], scores[i]});
        // sorted in descending order wrt ages
        Collections.sort(list, (a, b) -> (a[0] == b[0]) ? b[1] - a[1] : b[0] - a[0]);
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = list.get(i)[1];
            for (int j = 0; j < i; j++) {
                if (list.get(j)[1] >= list.get(i)[1])
                    dp[i] = Math.max(dp[i], dp[j] + list.get(i)[1]);
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}



/*
 * Sorted in ascending order wrt ages
 */
class Solution2 {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length, result = 0;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(new int[] {ages[i], scores[i]});
        Collections.sort(list, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = list.get(i)[1];
            for (int j = 0; j < i; j++) {
                if (list.get(j)[1] <= list.get(i)[1])
                    dp[i] = Math.max(dp[i], dp[j] + list.get(i)[1]);
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}



/*
 * Top-Down DP Approach from (very very non-inuitive)
 * Kaafi kuch samajhna reh gaya!!
 * https://leetcode.com/problems/best-team-with-no-conflicts/solutions/2886659/best-team-with-no-conflicts/
 */
class Solution3 {

	private int memoization(Integer[][] dp, int[][] ageScorePairs, int prevIndex, int currIndex) {
		if (currIndex >= ageScorePairs.length)
			return 0;
		if (dp[prevIndex + 1][currIndex] != null)
			return dp[prevIndex + 1][currIndex];
		if (prevIndex == -1 || ageScorePairs[currIndex][1] >= ageScorePairs[prevIndex][1])
			return dp[prevIndex + 1][currIndex] = Math.max(memoization(dp, ageScorePairs, prevIndex, currIndex + 1), ageScorePairs[currIndex][1] + memoization(dp, ageScorePairs, currIndex, currIndex + 1));
		return dp[prevIndex + 1][currIndex] = memoization(dp, ageScorePairs, prevIndex, currIndex + 1);
	}

	public int bestTeamScore(int[] scores, int[] ages) {
		int n = scores.length, result = 0;
		int[][] ageScorePairs = new int[n][2];
		for (int i = 0; i < n; i++) {
			ageScorePairs[i][0] = ages[i];
			ageScorePairs[i][1] = scores[i];
		}
		Arrays.sort(ageScorePairs, (a, b) -> (a[0] == b[0]) ? (a[1] - b[1]) : (a[0] - b[0]));
		Integer[][] dp = new Integer[n][n];
		return memoization(dp, ageScorePairs, -1, 0);
 	}
}



/*
 * Approach of Binary Indexed Tree (BIT) from 
 * https://leetcode.com/problems/best-team-with-no-conflicts/solutions/2886659/best-team-with-no-conflicts/
 */
