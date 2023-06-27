/*
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 */



/*
 * Approach 1 of Naive Backtracking from LC Official Editorial!
 */
class Solution {

    private boolean backtracking(int[] nums, int count, int currSum, int k, int targetSum, boolean[] taken) {
        if (count == k - 1)
            return true;
        if (currSum > targetSum)
            return false;
        if (currSum == targetSum)
            return backtracking(nums, count + 1, 0, k, targetSum, taken);
        for (int i = 0; i < nums.length; i++) {
            if (!taken[i]) {
                taken[i] = true;
                if (backtracking(nums, count, currSum + nums[i], k, targetSum, taken))
                    return true;
                taken[i] = false;
            }
        }
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum % k != 0)
            return false;
        sum /= k;
        boolean[] taken = new boolean[nums.length];
        return backtracking(nums, 0, 0, k, sum, taken);
    }
}



/*
 * Approach 2 of Optimized Backtracking from LC Official Editorial
 */
class Solution {

    private boolean backtracking(int[] nums, int idx, int count, int currSum, int k, int targetSum, boolean[] taken) {
        if (count == k - 1)
            return true;
        if (currSum > targetSum)
            return false;
        if (currSum == targetSum)
            return backtracking(nums, 0, count + 1, 0, k, targetSum, taken);
        for (int i = idx; i < nums.length; i++) {
            if (taken[i])
                continue;
            taken[i] = true;
            if (backtracking(nums, i + 1, count, currSum + nums[i], k, targetSum, taken))
                return true;
            taken[i] = false;
        }
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length, targetSum = 0;
        for (int num : nums)
            targetSum += num;
        if (targetSum % k != 0)
            return false;
        targetSum /= k;
        Arrays.sort(nums);
        for (int i = 0; i < n / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[n - i - 1];
            nums[n - i - 1] = temp;
        }
        boolean[] taken = new boolean[n];
        return backtracking(nums, 0, 0, 0, k, targetSum, taken);
    }
}



/*
 * Approach 3 of Backtracking + Memoization from LC Official Editorial
 */
class Solution {

    private char[] taken;
    private Map<String, Boolean> dp = new HashMap<>();

    private boolean backtracking(int[] nums, int i, int count, int currSum, int k, int targetSum) {
        if (count == k - 1)
            return true;
        if (currSum > targetSum)
            return false;
        String takenStr = new String(taken);
        if (dp.containsKey(takenStr))
            return dp.get(takenStr);
        if (currSum == targetSum) {
            boolean ans = backtracking(nums, 0, count + 1, 0, k, targetSum);
            dp.put(takenStr, ans);
            return ans;
        }
        for (int j = i; j < nums.length; j++) {
            if (taken[j] == '1')
                continue;
            taken[j] = '1';
            if (backtracking(nums, j + 1, count, currSum + nums[j], k, targetSum))
                return true;
            taken[j] = '0';
        }
        dp.put(takenStr, false);
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, n = nums.length;
        for (int num : nums)
            sum += num;
        if (sum % k != 0)
            return false;
        sum /= k;
        Arrays.sort(nums);
        for (int i = 0; i < n / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[n - i - 1];
            nums[n - i - 1] = temp;
        }
        taken = new char[n];
        for (int i = 0; i < n; i++)
            taken[i] = '0';
        return backtracking(nums, 0, 0, 0, k, sum);
    }
}



/*
 * Approach 4 of Backtracking + DP with Memoization + Bitmasking from LC Official Editorial
 */
class Solution {

    private Map<Integer, Boolean> dp = new HashMap<>();

    private boolean backtracking(int[] nums, int i, int count, int currSum, int k, int targetSum, Integer bitmask) {
        if (count == k - 1)
            return true;
        if (currSum > targetSum)
            return false;
        if (dp.containsKey(bitmask))
            return dp.get(bitmask);
        if (currSum == targetSum) {
            boolean retVal = backtracking(nums, 0, count + 1, 0, k, targetSum, bitmask);
            dp.put(bitmask, retVal);
            return retVal;
        }
        for (int j = i; j < nums.length; j++) {
            if (((bitmask >> j) & 1) == 1)
                continue;
            bitmask = bitmask | (1 << j);
            if (backtracking(nums, j + 1, count, currSum + nums[j], k, targetSum, bitmask))
                return true;
            bitmask = bitmask ^ (1 << j);
        }
        dp.put(bitmask, false);
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, n = nums.length;
        for (int num : nums)
            sum += num;
        if (sum % k != 0)
            return false;
        sum /= k;
        Arrays.sort(nums);
        for (int i = 0; i < n / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[n - i - 1];
            nums[n - i - 1] = temp;
        }
        Integer bitmask = 0;
        return backtracking(nums, 0, 0, 0, k, sum, bitmask);
    }
}



/*
 * Approach 5 of Tabulation + Bitmasking from LC Official Editorial!
 */

