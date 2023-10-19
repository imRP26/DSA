/*
 * https://leetcode.com/problems/backspace-string-compare/
 */



/*
 * Approach of Stack from LC Official Editorial!
 */
class Solution {

    private String helper(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != '#')
                stack.push(c);
            else if (!stack.isEmpty())
                stack.pop();
        }
        return String.valueOf(stack);
    }

    public boolean backspaceCompare(String s, String t) {
        return helper(s).equals(helper(t));
    }
}



/*
 * Approach of 2-Pointers from LC Official Editorial!
 */
class Solution {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1, sSkip = 0, tSkip = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    sSkip++;
                    i--;
                }
                else if (sSkip > 0) {
                    sSkip--;
                    i--;
                }
                else
                    break;
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    tSkip++;
                    j--;
                }
                else if (tSkip > 0) {
                    tSkip--;
                    j--;
                }
                else
                    break;
            }
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j))
                return false;
            if ((i >= 0) != (j >= 0))
                return false;
            i--;
            j--;
        }
        return true;
    }
}
