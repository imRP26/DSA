import java.util.*;

/*
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */



/*
 * 2 Pass approach using Stacks :- 
 * Find the index of the next smaller element on the left and on the right and 
 * then using the present index, find the area of the rectangle thus formed.
 * Video Link that can be referred :- 
 * https://www.youtube.com/watch?v=X0X6G-eWgQ8&list=PLgUwDviBIf0oSO572kQ7KCSvCUh1AdILj&index=11
 */
class Solution1 {
	public int largestRectangleArea(int[] heights) {
		int numHeights = heights.length, result = 0;
		Stack<Integer> stack = new Stack<>();
		int[] leftSmaller = new int[numHeights];
		int[] rightSmaller = new int[numHeights];
		for (int i = 0; i < numHeights; i++) {
			while (!stack.isEmpty() && heights[stack.peek()] >= heights[i])
				stack.pop();
			if (stack.isEmpty())
				leftSmaller[i] = 0;
			else
				leftSmaller[i] = stack.peek() + 1;
			stack.push(i);
		}
		while (!stack.isEmpty())
			stack.pop();
		for (int i = numHeights - 1; i >= 0; i--) {
			while (!stack.isEmpty() && heights[stack.peek()] >= heights[i])
				stack.pop();
			if (stack.isEmpty())
				rightSmaller[i] = numHeights - 1;
			else
				rightSmaller[i] = stack.peek() - 1;
			stack.push(i);
		}
		for (int i = 0; i < numHeights; i++)
			result = Math.max(result, heights[i] * (rightSmaller[i] - leftSmaller[i] + 1));
		return result;
	}
}



/*
 * 1 Pass Approach :-
 * 
 */

