/*
 * https://leetcode.com/problems/number-of-zero-filled-subarrays/
 */



/*
 * My own naive approach, not bad though!
 */ 
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long result = 0, num0 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                num0++;
                continue;
            }
            result += num0 * (num0 + 1) / 2;
            num0 = 0;
        }
        if (num0 != 0)
            result += num0 * (num0 + 1) / 2;
        return result;
    }
}



/*
 * Official approach from LC editorial!
 */
class Solution {
	public long zeroFilledSubarray(int[] nums) {
		long result = 0, num0Subarrays = 0;
		for (int num : nums) {
			num0Subarrays = (num == 0) ? (num0Subarrays + 1) : 0;
			result += num0Subarrays;
		}
		return result;
	}
} 
 