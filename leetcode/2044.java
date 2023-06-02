/*
 * https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/
 */



/*
 * Brute Force Solution, TC = O(n * (2 ^ n)), SC = O(n)
 */
class Solution {
    public int countMaxOrSubsets(int[] nums) {
        Map<Integer, Integer> orCounts = new HashMap<>();
        int n = nums.length, maxOrValue = 0;
        for (int bitmask = 1; bitmask < (1 << n); bitmask++) {
            int j = bitmask, i = 0, orVal = 0;
            while (j > 0) {
                if (j % 2 == 1)
                    orVal |= nums[i];
                j /= 2;
                i++;
            }
            maxOrValue = Math.max(maxOrValue, orVal);
            orCounts.put(orVal, orCounts.getOrDefault(orVal, 0) + 1);
        }
        return orCounts.get(maxOrValue);
    }
}



/*
 * Awesome Recursion-only based Solution, TC = O(2^n) from -> 
 * https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/solutions/1525211/java-beats-100/
 */
class Solution {

    private int result = 0, target = 0;

    private void recurse(int[] nums, int idx, int mask) {
        if (mask == target)
            result++;
        for (int i = idx; i < nums.length; i++)
            recurse(nums, i + 1, mask | nums[i]);    
    }

    public int countMaxOrSubsets(int[] nums) {
        for (int n : nums)
            target |= n;
        recurse(nums, 0, 0);
        return result;
    }
}



/*
 * Same approach as above, but without using any global variables, TC = O(2 ^ n) -> 
 * https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/solutions/1525211/java-beats-100/comments/1137118
 */
class Solution {

    private int recurse(int[] nums, int idx, int currOR, int maxOR) {
        if (idx == nums.length)
            return currOR == maxOR ? 1 : 0;
        return recurse(nums, idx + 1, currOR, maxOR) + recurse(nums, idx + 1, currOR | nums[idx], maxOR);
    }

    public int countMaxOrSubsets(int[] nums) {
        int maxOR = 0;
        for (int n : nums)
            maxOR |= n;
        return recurse(nums, 0, 0, maxOR);
    }
}



/*
 * Approach of DP from -> 
 * https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/solutions/1525274/easy-c-solution-dp-with-comments/comments/1120805
 */
class Solution {

    private int maxOR;
    //dp[i][j] = count of OR value 'j' obtained in subsets till index 'i'
    private int[][] dp = new int[17][1<<17];

    private int helper(int[] nums, int i, int currOR) {
        int ans = 0;
        if (i == nums.length) {
            if (currOR == maxOR)
                return ++ans;
            return 0;
        }
        if (dp[i][currOR] != -1)
            return dp[i][currOR];
        ans += helper(nums, i + 1, currOR | nums[i]) + helper(nums, i + 1, currOR);
        return dp[i][currOR] = ans;
    }

    public int countMaxOrSubsets(int[] nums) {
        for (int[] row : dp)
            Arrays.fill(row, -1);
        for (int num : nums)
            maxOR |= num;
        return helper(nums, 0, 0);
    }
}
