import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/daily-temperatures/
*/



/*
 * Solution using Monotonic Stack Approach -> placing array index elements into
 * the stack.
*/
class Solution1 {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length, i;
		int[] result = new int[n];
		Stack<Integer> stack = new Stack<>();
		for (i = 0; i < n; i++) {
			while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
				int t = stack.pop();
				result[t] = i - t;
			}
			stack.push(i);
		}
		return result;
    }
}



// Solution modifying the input array
class Solution2 {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length, i, t;
		Stack<Integer> stack = new Stack<>();
		for (i = 0; i < n; i++) {
			while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
				t = stack.pop();
				temperatures[t] = i - t;
			}
			stack.push(i);
		}
        while (!stack.isEmpty())
            temperatures[stack.pop()] = 0;
		return temperatures;
    }
}
