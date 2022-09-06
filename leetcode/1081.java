import java.util.*;

/*
 * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 */



// Similar to LC 316 - refer that
class Solution {
    public String smallestSubsequence(String s) {
        int[] charCounts = new int[26];
        boolean[] visited = new boolean[26];
        char[] stringChars = s.toCharArray();
        for (char ch : stringChars)
            charCounts[ch - 'a']++;
        Stack<Character> stack = new Stack<>();
        for (char ch : stringChars) {
            int index = ch - 'a';
            charCounts[index]--;
            if (visited[index])
                continue;
            while (!stack.isEmpty() && ch < stack.peek() && charCounts[stack.peek() - 'a'] != 0)
                visited[stack.pop() - 'a'] = false;
            visited[index] = true;
            stack.push(ch);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.insert(0, stack.pop());
        return sb.toString();
    }
}
