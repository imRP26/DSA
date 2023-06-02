/*
 * https://leetcode.com/problems/the-number-of-beautiful-subsets/
 */



/*
 * Approach of Backtracking from -> 
 * https://leetcode.com/problems/the-number-of-beautiful-subsets/solutions/3314052/o-2-n-c-backtracking-solution/
 */
class Solution {

	private int result = 0;
	private Map<Integer, Integer> map = new HashMap<>();

	private void dfs(int[] nums, int index, int k) {
		if (index == nums.length)
			result++;
		else {
            int x = nums[index];
            int count1 = map.getOrDefault(x - k, 0), count2 = map.getOrDefault(x + k, 0);
			if (count1 == 0 && count2 == 0) {
				map.put(x, map.getOrDefault(x, 0) + 1); // either do backtracking with nums[index]
				dfs(nums, index + 1, k);
				map.put(x, map.get(x) - 1);
			}
			dfs(nums, index + 1, k); // OR do backtracking without nums[index]
		}
	}

    public int beautifulSubsets(int[] nums, int k) {
        dfs(nums, 0, k);
        return result - 1;
    }
}



/*
 * 
 */

 