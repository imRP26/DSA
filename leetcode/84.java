/*
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */



/*
 * 2 Pass approach using Stacks :- 
 * Find the index of the next smaller element on the left and on the right and 
 * then using the present index, find the area of the rectangle thus formed.
 * Referred from Striver's video!
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, result = Integer.MIN_VALUE;
        int[] leftSmaller = new int[n], rightSmaller = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i])
                stack.pop();
            leftSmaller[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }
        while (!stack.isEmpty())
            stack.pop();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i])
                stack.pop();
            rightSmaller[i] = stack.isEmpty() ? n - 1 : stack.peek() - 1;
            stack.push(i);
        }
        for (int i = 0; i < n; i++)
            result = Math.max(result, heights[i] * (rightSmaller[i] - leftSmaller[i] + 1));
        return result;
    }
}



/*
 * 1 Pass Approach :-
 * Referred from Striver's video!
 */

