/*
 * https://leetcode.com/problems/132-pattern/
 */



/*
 * Approach 2 (Better Brute Force) from LC Official Editorial! - still TLE though...
 */
class Solution {
	public boolean find132pattern(int[] nums) {
		int n = nums.length, min_i = Integer.MAX_VALUE;
		for (int j = 0; j < n; j++) {
			min_i = Math.min(min_i, nums[j]);
			for (int k = j + 1; k < n; k++) {
				if (nums[k] < nums[j] && min_i < nums[k])
					return true;
			}
		}
		return false;
	}
}



/*
 * Approach of computing {i, j} intervals (Approach 3) from LC Official Editorial!
 */
class Solution {
    public boolean find132pattern(int[] nums) {
        List<int[]> intervals = new ArrayList<>();
		int k = 1, s = 0;
		while (k < nums.length) {
			if (nums[k] < nums[k - 1]) {
				if (s < k - 1)
					intervals.add(new int[] {nums[s], nums[k - 1]}); // {nums[i], nums[j]} pair
					s = k;
				}
				for (int[] interval : intervals) {
					if (nums[k] > interval[0] && nums[k] < interval[1])
						return true;
				}
				k++;
			}
		return false;
    }
}



/*
 * considering the indices i, j and k - suppose we're at index k.
 * Now we look back from index k towards the earlier indices, for 
 * index j, we need a value that's > nums[k]. So wouldn't it be better 
 * for us to have a track of values that are larger than the value at 
 * index k? A monotonically decreasing stack will definitely help us 
 * in this regard! 
 * We can start iterating from the beginning of the array, assuming each 
 * index to be our k index. 
 * If nums[k] >= stack.peek() -> pop off till either the stack becomes empty 
 								or the condition becomes False.
 * check for the required 132 pattern condition - if found, then just return TRUE.
 * Update currMin and keeping pair of values in stack - super duper awesome!
 * 
 * ALTERNATELY, just refer to Neetcode's solution video!
 */
class Solution {
    public boolean find132pattern(int[] nums) {
        int currMin = nums[0];
		Stack<int[]> stack = new Stack<>();
		for (int k = 1; k < nums.length; k++) {
			while (!stack.isEmpty() && nums[k] >= stack.peek()[0])
				stack.pop();
			//if (!stack.isEmpty() && nums[k] > stack.peek()[1])
			if (!stack.isEmpty() && nums[k] < stack.peek()[0] && nums[k] > stack.peek()[1])
				return true;
			stack.push(new int[] {nums[k], currMin});
			currMin = Math.min(currMin, nums[k]);
		}
		return false;
    }
}
