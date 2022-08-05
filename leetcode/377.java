import java.util.*;

/*
 * https://leetcode.com/problems/combination-sum-iv/
 */



// Recursive Solution - as expected, TLE
class Solution1 {
	public int combinationSum4(int[] nums, int target) {
		if (target == 0)
			return 1;
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= target)
				result += combinationSum4(nums, target - nums[i]); 
		}
		return result;
	}
}



/*
 * Solution based upon memoization - 
 * TC = O(nums.length * target), SC = O(target).
 */
class Solution2 {

	int memoize(int[] nums, int target, int[] dp) {
		if (dp[target] != -1)
			return dp[target];
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= target)
				result += memoize(nums, target - nums[i], dp);
		}
		return (dp[target] = result);
	}

	public int combinationSum4(int[] nums, int target) {
		int[] dp = new int[target + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		return memoize(nums, target, dp);
	}
}



/*
 * Bottom-Up Tabulation :-
 * TC = O(nums.length * target), SC = O(target)
 * Refer to this link in order to understand how the ordering of loops (for 
 * target and for coins) can give varying answers and this is the basis of 
 * difference between this question and Coin Change 2.
 */
class Solution3 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i)
                    dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
}
