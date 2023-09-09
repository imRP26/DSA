/*
 * https://leetcode.com/problems/sorting-three-groups/
 */



/*
 * FFS, try thinking about problems in alternate ways! - Simple LIS!
 * Number of indices that would need to be swapped will be those that don't participate 
 * to being in the LIS!
 */
class Solution {
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size(), res = 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums.get(j) <= nums.get(i))
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            res = Math.max(res, dp[i]);
        }
        return n - res;
    }
}



/*
 * Brute-Force Approach from -> 
 * https://leetcode.com/problems/sorting-three-groups/solutions/3932395/java-c-python-dp-6-lines-o-n/
 */
class Solution {
	public int minimumOperations(List<Integer> nums) {
		int n = nums.size(), res = n;
		/*
		 * Here, we assume that from nums[0] to nums[i - 1] are 0s, nums[i] to nums[j - 1] are 1s, 
         * nums[j] to nums[k - 1] are 2s.
		 */
		for (int i = 0; i <= n; i++) {
			for (int j = i; j <= n; j++) {
				int currOps = 0;
				for (int k = 0; k < n; k++) {
					if (k < i)
						currOps += (nums.get(k) != 1) ? 1 : 0;
					else if (k < j)
						currOps += (nums.get(k) != 2) ? 1 : 0;
					else
						currOps += (nums.get(k) != 3) ? 1 : 0;
				}
				res = Math.min(res, currOps);
			}
		}
		return res;
	}
}



/*
 * Approach of DP from -> 
 * https://leetcode.com/problems/sorting-three-groups/solutions/3932395/java-c-python-dp-6-lines-o-n/
 */
// will do this later!
