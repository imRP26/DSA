/*
 * https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/
 */



/*
 * Simple DP by Memoization
 */
class Solution {

    private boolean memoization(int i, Boolean[] dp, int[] nums) {
        if (i < 0)
            return true;
        if (i == 0)
            return false;
        if (dp[i] != null)
            return dp[i];
        boolean ans1 = false, ans2 = false, ans3 = false;
        if (i >= 1) {
            if (nums[i - 1] == nums[i])
                ans1 = memoization(i - 2, dp, nums);
        }
        if (i >= 2) {
            if (nums[i - 2] == nums[i - 1] && nums[i - 1] == nums[i])
                ans2 = memoization(i - 3, dp, nums);
            if (nums[i] - nums[i - 1] == 1 && nums[i - 1] - nums[i - 2] == 1)
                ans3 = memoization(i - 3, dp, nums);
        }
        return dp[i] = ans1 | ans2 | ans3;
    }

    public boolean validPartition(int[] nums) {
        return memoization(nums.length - 1, new Boolean[nums.length], nums);
    }
}



/*
 * Bottom-Up DP from LC official Editorial!
 */
class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            if (i >= 1 && nums[i] == nums[i - 1])
                dp[j] |= dp[j - 2];
            if (i >= 2 && nums[i - 2] == nums[i - 1] && nums[i - 1] == nums[i])
                dp[j] |= dp[j - 3];
            if (i >= 2 && nums[i] - nums[i - 1] == 1 && nums[i - 1] - nums[i - 2] == 1)
                dp[j] |= dp[j - 3];
        }
        return dp[n];
    }
}



/*
 * Space-Optimized Bottom-Up DP from LC Official Editorial!
 */
class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[3];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            boolean ans = false;
            if (i >= 1 && nums[i] == nums[i - 1])
                ans |= dp[(j - 2) % 3];
            if (i >= 2 && nums[i - 2] == nums[i - 1] && nums[i - 1] == nums[i])
                ans |= dp[(j - 3) % 3];
            if (i >= 2 && nums[i - 2] + 1 == nums[i - 1] && nums[i - 1] + 1 == nums[i])
                ans |= dp[(j - 3) % 3];
            dp[j % 3] = ans;
        }
        return dp[n % 3];
    }
}
