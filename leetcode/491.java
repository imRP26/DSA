/*
 * https://leetcode.com/problems/non-decreasing-subsequences/
 */



/*
 * My naive solution that got AC!!
 * If we use Java 'HashSet' or Python 'set' (which are implemented as hash sets 
 * under the hood), the total time complexity is O((2^n) * n) because we add to 
 * the hash set O(2^n) sequences each having a length of O(n).
 * However, if we use C++ set (which is a red-black tree under the hood), the time 
 * complexity is O((2^n) * (n^2)).
 * Space Complexity = O((2^n) * n)
 * Detailed Reference from Backtracking (Approach 1) of 
 * https://leetcode.com/problems/non-decreasing-subsequences/solutions/2910678/increasing-subsequences/
 */
class Solution1 {

    Set<List<Integer> > set = new HashSet<>();

    private void backtracking(int index, List<Integer> list, int[] nums) {
        if (list.size() > 1)
            set.add(new ArrayList<>(list));
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[index] > nums[i])
                continue;
            list.add(nums[i]);
            backtracking(i, list, nums);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
            backtracking(i, list, nums);
            list.remove(list.size() - 1);
        }
        List<List<Integer> > result = new ArrayList<>();
        for (List<Integer> l : set)
            result.add(l);
        return result;    
    }
}



/*
 * Bitmasks (Approach 2) from 
 * https://leetcode.com/problems/non-decreasing-subsequences/solutions/2910678/increasing-subsequences/
 */
class Solution2 {
	public List<List<Integer> > findSubsequences(int[] nums) {
		int n = nums.length;
		Set<List<Integer> > set = new HashSet<>();
		for (int bitmask = 0; bitmask < (1 << n); bitmask++) {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (((bitmask >> i) & 1) == 1)
					list.add(nums[i]);
			}
			int sz = list.size();
            if (sz <= 1)
				continue;
			boolean isNonDecreasing = true;
			for (int i = 0; i < sz - 1; i++) {
				if (list.get(i) > list.get(i + 1)) {
					isNonDecreasing = false;
					break;
				}
			}
			if (isNonDecreasing)
				set.add(list);
		}
		return new ArrayList<>(set);
	}
}



/*
 * An even better way of implementing set from 
 * https://leetcode.com/problems/non-decreasing-subsequences/solutions/97147/java-solution-beats-100/?orderBy=most_votes
 */
class Solution3 {

	private void helper(LinkedList<Integer> list, int index, int[] nums, List<List<Integer> > result) {
		if (list.size() > 1)
			result.add(new LinkedList<>(list));
		Set<Integer> used = new HashSet<>();
		for (int i = index; i < nums.length; i++) {
			if (used.contains(nums[i]))
				continue;
			if (list.size() == 0 || nums[i] >= list.peekLast()) {
				used.add(nums[i]);
				list.add(nums[i]);
				helper(list, i + 1, nums, result);
				list.remove(list.size() - 1);
			}
		}
	}

	public List<List<Integer> > findSubsequences(int[] nums) {
		List<List<Integer> > result = new LinkedList<>();
		helper(new LinkedList<>(), 0, nums, result);
		return result;
	}
}
