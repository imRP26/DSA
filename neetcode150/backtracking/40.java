import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/combination-sum-ii/
*/



// Backtracking Solution and a khurafati idea to avoid duplicates
class Solution {
	
	public void backtrack(int[] candidates, int target, 
                          List<List<Integer> > result, int index, 
                          List<Integer> list) {
		if (target == 0) {
			List<Integer> linkedList = new LinkedList<>(list);
			result.add(linkedList);
			return;
		}
		for (int i = index; i < candidates.length; i++) {
			if ((target < candidates[i]) || 
                (i > index && candidates[i] == candidates[i - 1]))
				continue;
			list.add(candidates[i]);
			backtrack(candidates, target - candidates[i], result, i + 1, list);
			list.remove(list.size() - 1);
		}
	}
	
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer> > result = new LinkedList<>();
		Arrays.sort(candidates);
        backtrack(candidates, target, result, 0, new LinkedList<>());
		return result;
    }
}