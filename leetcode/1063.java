/*
 * https://leetcode.com/problems/number-of-valid-subarrays/
 */



/*
 * Approach of Monotonic Stack from LC Official Editorial!
 */
class Solution {
    public int validSubarrays(int[] nums) {
        int n = nums.length, res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()])
                res += i - stack.pop();
            stack.push(i);
        }
        while (!stack.isEmpty())
            res += n - stack.pop();
        return res;
    }
}
