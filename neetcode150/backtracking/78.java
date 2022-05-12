import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/subsets/
*/



// Bitmasking
class Solution1 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer> > result = new LinkedList<>();
		int i, x, j, n = nums.length, bitmask = (int)Math.pow(2, n);
		for (i = 0; i < bitmask; i++) {
			x = i;
			j = 0;
			List<Integer> temp = new LinkedList<>();
			while (x > 0) {
				if ((x & 1) == 1)
					temp.add(nums[j]);
				x /= 2;
				j++;
			}
			result.add(temp);
		}
		return result;
    }
}



// Backtracking
class Solution2 {
	/*
    NEED TO REVISIT THE FOLLOWING DOUBT SOON... 
    Reason for list.add(new ArrayList<>(tempList)) is that if you do not make 
    new list, all the tempList added in main list would be pointing to same 
    tempList and as all of them are pointing to same list, all will get 
    updated with same values. 
    By putting tempList in constructor he is making new list having same 
    values as tempList.
    When function is called initially you need on initialize the list so that 
    you can add values to it that why when calling backtrack function in 
    starting its using backtrack(list, new ArrayList<>(), s, 0).
    */
	public void backtrack(List<List<Integer> > result, int[] nums, int index, 
						  List<Integer> temp) {
		result.add(new LinkedList<>(temp));
		for (int i = index; i < nums.length; i++) {
			temp.add(nums[i]);
			backtrack(result, nums, i + 1, temp);
			temp.remove(temp.size() - 1);
        }
	}
	
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer> > result = new LinkedList<>();
		backtrack(result, nums, 0, new LinkedList<>());
		return result;
    }
}