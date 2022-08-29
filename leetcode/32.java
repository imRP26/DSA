import java.util.*;

/*
 * https://leetcode.com/problems/longest-valid-parentheses/
 */



// Brute Force - O(N^3)
class Solution1 {
    
    boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == ')') {
                if (stack.empty() || stack.peek() != c)
                    return false;
                stack.pop();
            }
        }
        return stack.empty();
    }
    
    public int longestValidParentheses(String s) {
        int slen = s.length(), result = 0;
        for (int i = 0; i < slen; i++) {
            for (int j = i + 2; j <= slen; j += 2) {
                String s1 = s.substring(i, j);
                if (isValid(s1))
                    result = Math.max(result, s1.length());
            }
        }
        return result;
    }
}



/*
 * dp[i] = length of the longest valid substring ending at the i'th index
 * If s[i] == '(', then dp[i] = 0, since no string ending with '(' can be 
 * considered to be valid.
 * If s[i] == ')' and s[i - 1] = '(', then dp[i] = dp[i - 2] + 2.
 * If s[i] == ')' and s[i - 1] = ')' and if s[i - dp[i - 1] - 1] = '(', then 
 * dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2.
 */