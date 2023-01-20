/*
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/d
 */



/*
 * Approach from 
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/2913063/subarray-sums-divisible-by-k/
 */
class Solution {
	public int subarraysDivByK(int[] nums, int k) {
		int n = nums.length, prefixMod = 0, result = 0;
		int[] modCount = new int[k];
		modCount[0] = 1;
		for (int num : nums) {
			prefixMod = (prefixMod + num) % k;
			if (prefixMod < 0)
				prefixMod += k;
			result += modCount[prefixMod];
			modCount[prefixMod]++;
		}
		return result;
	}
}
