import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/combination-sum-iii/
*/



// Only backtracking solution
class Solution {
	
	public void combination(List<List<Integer> > result, List<Integer> list, 
							int k, int start, int n) {
	    if (list.size() == k && n == 0) {
	    	List<Integer> linkedList = new LinkedList<>(list);
			result.add(linkedList);
			return;
	    }
		for (int i = start; i <= 9; i++) {
			list.add(i);
			combination(result, list, k, i + 1, n - i);
			list.remove(list.size() - 1);
		}
	}
	
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer> > result = new LinkedList<>();
		combination(result, new LinkedList<>(), k, 1, n);
		return result;
    }
}
