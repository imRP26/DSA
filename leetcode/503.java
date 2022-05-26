import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/next-greater-element-ii/ 
*/



/*
 * Just think of the numbers as poles casting shadows (to their right, as we 
 * we see from left).
 * Let's say the next larger pole for a particular index is L. Because we are 
 * looking from the left side, when we see L we won't be able to see the 
 * poles on its right which are smaller than L because they are overshadowed.
 * But the poles on the right of L which are larger than L can still be seen 
 * because they are taller. That's why they are in stack.
 * The stack at any position is literally what we would see standing there 
 * looking at the right.
*/
// Monotonic Stack based solution - 
class Solution1 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, i;
        int[] result = new int[n];
		Stack<Integer> stack = new Stack<>();
		for (i = n - 1; i >= 0; i--)
			stack.push(nums[i]);
		for (i = n - 1; i >= 0; i --) {
			while (!stack.isEmpty() && stack.peek() <= nums[i])
				stack.pop();
			if (!stack.isEmpty())
				result[i] = stack.peek();
			else
				result[i] = -1;
			stack.push(nums[i]);
		}
		return result;
    }
}



// Inefficient Solution - looping twice the length of the input array
class Solution2 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
		int[] result = new int[n];
		Arrays.fill(result, -1);
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n * 2; i++) {
			while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n])
				result[stack.pop()] = nums[i % n];
			if (i < n)
				stack.push(i);
		}
		return result;
    }
}



// Another efficient solution same as Solution1, but using indices
class Solution3 {
	public int[] nextGreaterElements(int[] nums) {
		int n = nums.length, i;
		int[] result = new int[n];
		Arrays.fill(result, -1);
		Stack<Integer> stack = new Stack<>();
		for (i = n - 1; i >= 0; i--)
			stack.push(i);
		for (i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && nums[stack.peek()] <= nums[i])
				stack.pop();
			if (stack.isEmpty())
				result[i] = -1;
			else
				result[i] = nums[stack.peek()];
			stack.push(i);
		}
		return result;
	}
}



/* 
 * Solution involving DP :-
 * Idea is to use the result array to be returned to store info. rather than 
 * to use an extra stack. The result array is used to store index of the next 
 * larger element and replace it with actual values before returning it. 
 * In the 1st iteration, we move from right to left and find the next greater 
 * element assuming array to be non-cyclical.
 * Then, we do another iteration from right to left.
 * This time we assume the array to be cyclical and find the next greater 
 * element for those elements that didn't have any, when the array was 
 * considered to be non-cyclical.
*/
class Solution4 {
    public int[] nextGreaterElements(int[] nums) {
		// case when array is empty
		if (nums.length == 0)
			return nums;
	
		int n = nums.length, i, k;
		int[] result = new int[n];

		/* 
		 * assuming array to be non-cyclical, then the last element doesn't 
		 * have a "next greater element"
		*/
		result[n - 1] = -1; 
		
		if (n == 1) // for a single element array
			return result;
		
		for (i = n - 2; i >= 0; i--) {
			k = i + 1; // starting from the very next element
			
			/*
			 * keep tracking next larger element until its found or an element 
			 * is found with no "next greater element" 
			*/
			while (nums[k] <= nums[i] && result[k] != -1)
				k = result[k];
			
			// // if next larger element is found, store the index
			if (nums[k] > nums[i]) 
				result[i] = k;
			else // if not found, then it doesn't have a next larger element
				result[i] = -1;
		}
		
		/* 
		 * 2nd iteration, assuming cyclical array wherein the last element can 
		 * also have a "next greater element"
		*/
		for (i = n - 1; i >= 0; i--) {
			
			/* 
			 * if a "next greater element" already exists when a non-circular 
			 * array is assumed, then its bound to exist in case of a circular 
			 * array as well.
			*/
			if (result[i] != -1)
				continue;			
			
			k = (i + 1) % n; // % for the last element
			
			while (nums[k] <= nums[i] && result[k] != -1)
				k = result[k];
			
			if (nums[k] > nums[i])
				result[i] = k;
			else
				result[i] = -1;
		}
		
		// replacing indices with actual values
		for (i = 0; i < n; i++) {
			if (result[i] != -1)
				result[i] = nums[result[i]];
		}
		return result;
    }
}
