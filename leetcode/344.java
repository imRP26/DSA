import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/reverse-string/submissions/
*/



// Basic iterative 2-Pointers Technique
class Solution1 {
    public void reverseString(char[] s) {
        int low = 0, high = s.length - 1;
        while (low < high) {
            char ch = s[low];
            s[low] = s[high];
            s[high] = ch;
            low++;
            high--;
        }
    }
}



// Recursive 2-Pointers Technique
class Solution2 {
    
    public void recurse(char[] s, int low, int high) {
        if (low >= high)
            return;
        char temp = s[low];
        s[low] = s[high];
        s[high] = temp;
        recurse(s, ++low, --high);
    }
    
    public void reverseString(char[] s) {
        recurse(s, 0, s.length - 1);
    }
}



/* 
 * Another Recursive Solution :- 
 * Time Complexity = O(n * log (n))
 * Recurrene equation :- T(n) = 2 * T(n / 2) + O(n), O(n) is due to the fact 
 * that concatenation takes linear time.
 * Auxiliary space used is O(h), where h = log (n) - this is the depth  of the 
 * generated recursion tree. Space is needed for activation stack during 
 * recursion calls.
*/
class Solution3 {
	
	public String recurseReversal(String s) {
		int len = s.length();
		if (len <= 1)
			return s;
		String leftStr = s.substring(0, len / 2), rightStr = s.substring(len / 2, len);
		return recurseReversal(rightStr) + recurseReversal(leftStr);
	}
	
	public void reverseString(char[] s) {
		char[] s1 = recurseReversal(String.valueOf(s)).toCharArray();
        s = Arrays.copyOf(s1, s1.length);
	}
}



// Stack based reversal
class Solution4 {
	public void reverseString(char[] s) {
		Stack<Character> stack = new Stack<>();
		for (char ch : s)
			stack.push(ch);
		for (int i = 0; i < s.length; i++)
			s[i] = stack.pop();
	}
}
