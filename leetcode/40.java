import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/combination-sum-ii/
*/



// Backtracking Solution and a khurafati idea to avoid duplicates
class Solution {
	
	public void backtrack(int[] candidates, int target, List<List<Integer> > result, int index, List<Integer> list) {
		if (target == 0) {
			List<Integer> linkedList = new LinkedList<>(list);
			result.add(linkedList);
			return;
		}
		for (int i = index; i < candidates.length; i++) {
			/*
			 * In order to avoid duplicate combinations, if I've checked 
			 * candidates[i-1], while candidates[i] == candidates[i - 1], so that 
			 * can be skipped.
			 * As an example, if we have target = 14 and candidates - [1, 1, 6, 7], 
			 * then I've already reached my target by including [1 (the 1st one), 6, 7] 
			 * and I don't need to include the next 1 now, since that will be a 
			 * repetition.
			 */
			if ((target < candidates[i]) || (i > index && candidates[i] == candidates[i - 1]))
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
