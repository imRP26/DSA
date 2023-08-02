/*
 * https://leetcode.com/problems/permutations/
 */



/*
 * Simple Backtracking
 */
class Solution {

    private int n;
    private List<List<Integer> > res = new ArrayList<>();

    private void backtrack(int[] nums, List<Integer> list, Set<Integer> set) {
        if (list.size() == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (set.contains(nums[i]))
                continue;
            set.add(nums[i]);
            list.add(nums[i]);
            backtrack(nums, list, set);
            set.remove(nums[i]);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        n = nums.length;
        backtrack(nums, new ArrayList<>(), new HashSet<>());
        return res;
    }
}
