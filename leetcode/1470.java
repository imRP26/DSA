/*
 * https://leetcode.com/problems/shuffle-the-array/
 */



/*
 * My Naive, somewhat complex solution!!
 */
class Solution1 {
    public int[] shuffle(int[] nums, int n) {
        int i = 0, j = n;
        int[] result = new int[2 * n];
        while (i < 2 * n && j < 2 * n) {
            result[i] = nums[i / 2];
            i++;
            result[i++] = nums[j++];
        }
        if (i < 2 * n)
            result[i] = nums[i / 2];
        return result;
    }
}



/*
 * Approach 1 from 
 * https://leetcode.com/problems/shuffle-the-array/solutions/2973933/shuffle-the-array/?orderBy=most_votes
 */
class Solution2 {
	public int[] shuffle(int[] nums, int n) {
		int[] result = new int[2 * n];
		for (int i = 0; i < n; i++) {
			result[2 * i] = nums[i];
			result[2 * i + 1] = nums[n + i];
		}
		return result;
	}
}



/*
 * WOWWW wala solution!!!
 * Bit Manipulation (Approach 2, inplace) from 
 * https://leetcode.com/problems/shuffle-the-array/solutions/2973933/shuffle-the-array/?orderBy=most_votes
 */
class Solution3 {
	public int[] shuffle(int[] nums, int n) {
		for (int i = n; i < 2 * n; i++) {
			int num2 = nums[i] << 10;
			nums[i - n] |= num2;
		}
		int allOnes = (int)Math.pow(2, 10) - 1;
		for (int i = n - 1; i >= 0; i--) {
			int num2 = nums[i] >> 10, num1 = nums[i] & allOnes;
			nums[2 * i] = num1;
			nums[2 * i + 1] = num2;
		}
		return nums;
	}
}
