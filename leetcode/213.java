/*
 * Just be greedy and explore the 2 choices that you have...
 */
class Solution {
    
    public int robAmount(int[] nums, int startIndex) {
		int n = nums.length, endIndex = (startIndex == 0) ? n - 2 : n - 1, rob1 = 0, rob2 = 0;
		for (int i = startIndex; i <= endIndex; i++) {
			int temp = Math.max(rob1 + nums[i], rob2);
			rob1 = rob2;
			rob2 = temp;
		}
		return rob2;
	}
    
    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(robAmount(nums, 0), robAmount(nums, 1));
    }
}
