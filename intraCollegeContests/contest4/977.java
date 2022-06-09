import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/squares-of-a-sorted-array/
 */



 // My initial naive solution -> TC = O(n log n)
 class Solution1 {
    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++)
            nums[i] = nums[i] * nums[i];
        Arrays.sort(nums);
        return nums;
    }
}



/*
 * Method of 2 Pointers :- 
 * The crux here is that the array is already sorted.
 * We're initially comparing the first and the last elements because after 
 * squaring, these have the possibility of being the elements highest in magnitude.
 * Both the extremes contain the max element after squaring, so we're inserting 
 * these elements to the last of the new array to make it sorted.
 */
class Solution2 {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length, low = 0, high = n - 1, index = n - 1;
		int[] result = new int[n];
		while (low <= high) {
			if (Math.abs(nums[low]) >= Math.abs(nums[high]))
				result[index--] = nums[low] * nums[low++];
			else
				result[index--] = nums[high] * nums[high--];
		}
		return result;
    }
}



/* 
 * Inefficient version of the above method -> 
 * Find the minimum element and let its index be i.
 * Initialize lowPtr = i - 1 and highPointer = i + 1.
 * Compare the absolute values present in the 2 indices (lowPtr & highPtr) and 
 * fill up the result array.
*/
class Solution3 {
    public int[] sortedSquares(int[] nums) {
        int minValue = Integer.MAX_VALUE, minPosition = 0, n = nums.length, low = 0, high = 0;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (Math.abs(nums[i]) < minValue) {
                minValue = Math.abs(nums[i]);
                minPosition = i;
                low = i - 1;
                high = i + 1;
            }
        }
        int index = 0;
        result[index++] = nums[minPosition] * nums[minPosition];
        System.out.println(minPosition + " " + low + " " + high);
        while (index < n && low >= 0 && high < n) {
            if (Math.abs(nums[low]) <= Math.abs(nums[high]))
                result[index++] = nums[low] * nums[low--];
            else
                result[index++] = nums[high] * nums[high++];
        }
        while (index < n && low >= 0)
            result[index++] = nums[low] * nums[low--];
        while (index < n && high < n)
            result[index++] = nums[high] * nums[high++];
        return result;
    }
}
