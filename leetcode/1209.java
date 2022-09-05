import java.util.*;

/*
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 */



// Ad-Hoc + Stacks
class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Character> charStack = new Stack<>();
		Stack<Integer> countStack = new Stack<>();
		for (char ch : s.toCharArray()) {
			if (charStack.size() > 0 && charStack.peek() == ch)
				countStack.push(countStack.peek() + 1);
			else
				countStack.push(1);
			charStack.push(ch);
			if (countStack.peek() == k) {
				for (int i = 0; i < k; i++) {
					charStack.pop();
					countStack.pop();
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		while (charStack.size() > 0)
			sb.append(charStack.pop());
		return sb.reverse().toString();
    }
}
