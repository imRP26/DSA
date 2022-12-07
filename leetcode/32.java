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
 * https://leetcode.com/problems/longest-valid-parentheses/solutions/127609/longest-valid-parentheses/comments/201028
 * https://leetcode.com/problems/longest-valid-parentheses/solutions/14132/o-n-solution-constant-o-1-space-no-dp-no-stack/
 * (Consider the example "(()" for understanding as to why traversals from both the sides is important)
 */
class Solution2 {
    public int longestValidParentheses(String s) {
        int leftBrackets = 0, rightBrackets = 0, result = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(')
                leftBrackets++;
            else
                rightBrackets++;
            if (leftBrackets == rightBrackets)
                result = Math.max(result, 2 * rightBrackets);
            else if (leftBrackets < rightBrackets)
                leftBrackets = rightBrackets = 0;
        }
        leftBrackets = rightBrackets = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ')')
                rightBrackets++;
            else   
                leftBrackets++;
            if (rightBrackets == leftBrackets)
                result = Math.max(result, 2 * leftBrackets);
            else if (leftBrackets > rightBrackets)
                leftBrackets = rightBrackets = 0;
        }
        return result;
    }
}



/*
 * Referenced from -> https://www.youtube.com/watch?v=zhsjU1Bh9jQ
 */
class Solution3 {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int len = s.length(), result = 0, index = -1;
        for (int i = 0; i < len; i++) {
            if (!stack.isEmpty() && s.charAt(i) == ')' && s.charAt(stack.peek()) == '(')
                stack.pop();
            else
                stack.push(i);
        }
        for (int i : stack) {
            result = Math.max(result, i - index - 1);
            index = i;
        }
        result = Math.max(result, len - index - 1);
        return result;
    }
}



/*
 * DP Based Solution, to be referred from :-
 * https://leetcode.com/problems/longest-valid-parentheses/solutions/127609/longest-valid-parentheses/
 * https://leetcode.com/problems/longest-valid-parentheses/solutions/14135/dp-code-with-no-ugly-conditions-check/
 * https://leetcode.com/problems/longest-valid-parentheses/solutions/14136/my-elegant-c-dp-solution-with-detailed-explanation/
 */
class Solution4 {
    public int longestValidParentheses(String s) {
        s = '*' + s;
        int len = s.length(), result = 0;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(')
                    dp[i] = dp[i - 2] + 2;
                else {
                    /*
                     * if current char is ')' and the 1st one before the last streak of valid 
                     * parentheses is '(', we add :- 
                     * (1) last streak before the new pair.
                     * (2) the streak inside the new pair.
                     * (3) the new pair.
                     */
                    int prev = i - dp[i - 1] - 1;
                    if (s.charAt(prev) == '(')
                        dp[i] = dp[prev - 1] + dp[i - 1] + 2;
                }
                result = Math.max(result, dp[i]);
            }
        }
        return result;
    }
}
