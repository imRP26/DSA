import java.util.*;


/*
 * Just be greedy and explore the 2 choices that you have...
 * Reference -> 
 * https://leetcode.com/problems/house-robber-ii/solutions/751859/house-robber-ii/?orderBy=most_votes
 */
class Solution {
    
    public int robAmount(int[] nums, int startIndex) {
		int n = nums.length, endIndex = (startIndex == 0) ? n - 2 : n - 1, robber1 = 0, robber2 = 0;
		for (int i = startIndex; i <= endIndex; i++) {
			int temp = Math.max(robber1 + nums[i], robber2);
			robber1 = robber2;
			robber2 = temp;
		}
		return robber2;
	}
    
    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(robAmount(nums, 0), robAmount(nums, 1));
    }
}
