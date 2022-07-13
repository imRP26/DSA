import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/valid-parenthesis-string/
 */



// Greedy-Based Solution, refer to Neetcode's video
class Solution1 {
    public boolean checkValidString(String s) {
        int leftMin = 0, leftMax = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftMin++;
                leftMax++;
            }
            else if (c== ')') {
                leftMin--;
                leftMax--;
            }
            else {
                leftMin--;
                leftMax++;
            }
            if (leftMax < 0)
                return false;
            if (leftMin < 0)
                leftMin = 0;
        }
        return (leftMin == 0);
    }
}



/*
 * Solution Link -> 
 * https://leetcode.com/problems/valid-parenthesis-string/discuss/107572/Java-using-2-stacks.-O(n)-space-and-time-complexity.
 */
class Solution2 {
    public boolean checkValidString(String s) {
        Stack<Integer> leftID = new Stack<>();
        Stack<Integer> starID = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                leftID.push(i);
            else if (ch == '*')
                starID.push(i);
            else {
                if (leftID.isEmpty() && starID.isEmpty())
                    return false;
                if (!leftID.isEmpty())
                    leftID.pop();
                else
                    starID.pop();
            }
        }
        while (!leftID.isEmpty() && !starID.isEmpty()) {
            if (leftID.pop() > starID.pop())
                return false;
        }
        return leftID.isEmpty();
    }
}



/*
 * Recursion Approach - gives TLE, TC = O(3 ^ n), where n = |s|
 * Intuition: increment open variable when we get "(" and decrement it when ")".
 * Otherwise we just need to consider 3 cases: skip "*" symbol or substitute it 
 * with either closing or opening bracket, i.e. increase or decrease open.
 * Check for case when we want to decrement zero-valued open, that means that we 
 * want to put ")" before "(", which is unacceptable.
 */
class Solution3 {

    private boolean checkValidString(String s, int index, int open) {
        if (index == s.length())
            return open == 0;
        if (s.charAt(index) == '(')
            return checkValidString(s, index + 1, open + 1);
        else if (s.charAt(index) == ')')
            return open != 0 && checkValidString(s, index + 1, open - 1);
        else
            return checkValidString(s, index + 1, open) || 
                   checkValidString(s, index + 1, open + 1) || 
                   (open != 0 && checkValidString(s, index + 1, open - 1));
    }

    public boolean checkValidString(String s) {
        return checkValidString(s, 0, 0);
    }
}



// Memoization of the above recursive solution
class Solution4 {

    private boolean checkValidString(String s, int index, int open, Boolean[][] memo) {
        if (index == s.length())
            return open == 0;
        if (memo[index][open] != null)
            return memo[index][open];
        if (s.charAt(index) == '(')
            return memo[index][open] = checkValidString(s, index + 1, open + 1, memo);
        if (s.charAt(index) == ')')
            return memo[index][open] = (open != 0) && checkValidString(s, index + 1, open - 1, memo);
        return memo[index][open] = checkValidString(s, index + 1, open, memo) || 
                                   (open != 0) && checkValidString(s, index + 1, open - 1, memo) || 
                                   checkValidString(s, index + 1, open + 1, memo);
    }

    public boolean checkValidString(String s) {
        Boolean[][] memo = new Boolean[s.length()][s.length()];
        return checkValidString(s, 0, 0, memo);
    }
}
