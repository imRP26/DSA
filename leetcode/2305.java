/*
 * https://leetcode.com/problems/fair-distribution-of-cookies/
 */



/*
 * Approach of Backtracking from LC Official Editorial
 */
class Solution {

    private int[] distribution;

    private int dfs(int i, int[] cookies, int zeroCount) {
        int n = cookies.length;
        if (n - i < zeroCount)
            return Integer.MAX_VALUE;
        if (i == n) {
            int unfairness = Integer.MIN_VALUE;
            for (int val : distribution)
                unfairness = Math.max(unfairness, val);
            return unfariness;
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < distribution.length; j++) {
            zeroCount -= distribution[j] == 0 ? 1 : 0;
            distribution[j] += cookies[i];
            res = Math.min(res, dfs(i + 1, cookies, zeroCount));
            distribution[j] -= cookies[i];
            zeroCount += distribution[j] == 0 ? 1 : 0;
        }
        return res;
    }

    public int distributeCookies(int[] cookies, int k) {
        distribution = new int[k];
        return dfs(0, cookies, k);
    }
}



/*
 * Approach 2 of DP with Bitmasking from -> 
 * https://leetcode.com/problems/fair-distribution-of-cookies/solutions/2144128/all-solutions-o-3-n-o-4-n-o-k-n-explained/
 * Very important explanation step -> 
 * https://leetcode.com/problems/fair-distribution-of-cookies/solutions/2144128/all-solutions-o-3-n-o-4-n-o-k-n-explained/comments/1508536
 */
class Solution {

    private int n, k;
	private int[] subsetSums;
	private int[][] dp;
    
    private int memoization(int idx, int mask) {
    	if (idx == k)
			return (mask == ((1 << n) - 1)) ? 0 : Integer.MAX_VALUE;
		if (dp[idx][mask] != -1)
			return dp[idx][mask];
		int ans = Integer.MAX_VALUE;
		for (int newMask = 0; newMask < (1 << n); newMask++) {
			if ((newMask & mask) == 0)
				ans = Math.min(ans, Math.max(subsetSums[newMask], memoization(idx + 1, newMask | mask)));
		}
		return dp[idx][mask] = ans;
    }

    public int distributeCookies(int[] cookies, int k) {
        n = cookies.length;
        this.k = k;
		subsetSums = new int[1 << n];
		for (int i = 0; i < (1 << n); i++) {
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0)
					subsetSums[i] += cookies[j];
			}
		}
        dp = new int[k][1 << n];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		return memoization(0, 0);
    }
}



/*
 * Approach 2 of DP with Bitmasking from -> 
 * https://leetcode.com/problems/fair-distribution-of-cookies/solutions/2144128/all-solutions-o-3-n-o-4-n-o-k-n-explained/
 * Very important explanation step -> 
 * https://leetcode.com/problems/fair-distribution-of-cookies/solutions/2144128/all-solutions-o-3-n-o-4-n-o-k-n-explained/comments/1508536
 */
class Solution {

    private int n, k;
    private int[] subsetSums;
    private int[][] dp;
 
    private int memoization(int idx, int mask) {
        if (idx == k)
            return (mask == ((1 << n) - 1)) ? 0 : (int)1e9;
        if (dp[idx][mask] != -1)
            return dp[idx][mask];
        int ans = (int)1e9, leftMask = (1 << n) - 1 - mask;
        // now make subsets of 'leftMask'
        for (int newMask = leftMask; newMask > 0; newMask = (newMask - 1) & leftMask)
            ans = Math.min(ans, Math.max(subsetSums[newMask], memoization(idx + 1, newMask | mask)));
        return ans;
    }

    public int distributeCookies(int[] cookies, int k) {
        this.k = k;
        n = cookies.length;
        subsetSums = new int[1 << n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0)
                    subsetSums[i] += cookies[j];
            }
        }
        dp = new int[k][1 << n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return memoization(0, 0);
    }
}
