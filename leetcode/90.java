import java.util.*;

/*
 * https://leetcode.com/problems/subsets-ii/
 */



// Simple Backtracking
class Solution1 {
	
	public void backtrack(int[] nums, List<List<Integer> > result, List<Integer> temp, int index) {
		result.add(new LinkedList<>(temp));
		for (int i = index; i < nums.length; i++) {
			if (i > index && nums[i] == nums[i - 1]) // avoiding duplicates
				continue;
			temp.add(nums[i]);
			backtrack(nums, result, temp, i + 1);
			temp.remove(temp.size() - 1);
		}
	}
	
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
		List<List<Integer> > result = new LinkedList<>();
		backtrack(nums, result, new LinkedList<>(), 0);
		return result;
    }
}



// 
