import java.util.*;

/*
 * https://leetcode.com/problems/house-robber/
 */



// Refer to Striver's video on the same (DP-5) for some kickarse explanation
// Simple Memoization
class Solution1 {

	/*public int robAmount(int[] nums, int n) { -> Recursive approach, gives TLE
		if (n < 0)
			return 0;
		if (n == 0 || n == 1)
			return nums[n];
		return Math.max(nums[n] + robAmount(nums, n - 2), robAmount(nums, n - 1));
	}*/

	public int robAmount(int[] nums, int[] dp, int n) {
		if (n < 0)
			return 0;
		if (dp[n] != -1)
			return dp[n];
		if (n <= 1)
			return nums[n];
		dp[n] = Math.max(nums[n] + robAmount(nums, dp, n - 2), robAmount(nums, dp, n - 1));
		return dp[n];
	}

    public int rob(int[] nums) {
        int n = nums.length, dp[] = new int[n];
		Arrays.fill(dp, -1);
        return robAmount(nums, dp, n - 1);
		//return robAmount(nums, n - 1);
    }
}



// Bottom-Up
class Solution2 {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            int temp = nums[i];
            if (i > 1)
                temp += dp[i - 2];
            dp[i] = Math.max(temp, dp[i - 1]);
        }
        return dp[n - 1];
    }
}



// Space-Optimized
class Solution3 {
    public int rob(int[] nums) {
        int rob1 = 0, rob2 = 0;
		for (int n : nums) {
			int temp = Math.max(n + rob1, rob2);
			rob1 = rob2;
			rob2 = temp;
		}
		return rob2;
    }
}
