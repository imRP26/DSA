import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/next-permutation/
*/



// Simple problem involving a similar concept to Monotonic Stacks.
class Solution {
    public void nextPermutation(int[] nums) {
    	int n = nums.length, i = n - 2, j = n - 1;
		if (n == 1)
			return;
        /*
         * Reason why we start our operation from the back-end of the array is 
         * place value.
         * For example, for the number 13542, we have :-
         * 13542 = 2 * 10^0 + 4 * 10^1 + 5 * 10^2 + 3 * 10^3 + 1 * 10^4
         * Usually the next higher number involves moving a digit having a 
         * greater magnitude to a place having a higher place value.
         * Now here, since we're concerned with just the next greater value, 
         * we (greedily) search from the back so that any manipulations can be 
         * done in digit positions having a lesser place value.
        */
		while (i >= 0 && nums[i] >= nums[i + 1])
			i--;
		if (i == -1) {
			Arrays.sort(nums);
			return;
		}
		while (j > i && nums[j] <= nums[i])
			j--;
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
        Arrays.sort(nums, i + 1, n);
    }
}



/*
 * Best Possible Explanation -> 
 * https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
 */
