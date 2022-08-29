import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/combination-sum/
*/



// Only backtracking solution
class Solution {
	
	public void backtrack(int[] candidates, int target, int index, 
						  List<List<Integer> > result, List<Integer> list) {
		if (target == 0) {
			List<Integer> linkedList = new LinkedList<>(list);
			result.add(linkedList);
			return;
		}
		for (int i = index; i < candidates.length; i++) {
			if (target < candidates[i])
				continue;
			list.add(candidates[i]);
			backtrack(candidates, target - candidates[i], i, result, list);
			list.remove(list.size() - 1);
		}
	}
	
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer> > result = new LinkedList<>();
		backtrack(candidates, target, 0, result, new LinkedList<>());
		return result;
    }
}