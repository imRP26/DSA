import java.util.*;

/*
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 */



// Use of Stacks and Ad-Hoc
class Solution1 {
    public String removeDuplicates(String s) {
        Stack<Character> charStack = new Stack<>();
		Stack<Integer> countStack = new Stack<>();
		for (char ch : s.toCharArray()) {
			if (charStack.size() > 0 && ch == charStack.peek())
				countStack.push(countStack.peek() + 1);
			else
				countStack.push(1);
			charStack.push(ch);
			if (countStack.peek() == 2) {
				for (int i = 0; i < 2; i++) {
					charStack.pop();
					countStack.pop();
				}
			}
		}
		StringBuilder result = new StringBuilder();
		while (charStack.size() > 0)
			result.append(charStack.pop());
		return result.reverse().toString();
    }
}



// An easier and cleaner method
class Solution2 {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.empty() && stack.peek() == ch) {
                while (!stack.empty() && stack.peek() == ch)
                    stack.pop();
            }
            else
                stack.push(ch);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.peek());
            stack.pop();
        }
        return sb.reverse().toString();
    }
}
