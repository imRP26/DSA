/*
 * https://leetcode.com/problems/remove-duplicate-letters/
 */



/*
 * Solution referred to from here :- 
 * https://leetcode.com/problems/remove-duplicate-letters/discuss/76769/Java-solution-using-Stack-with-comments
 */
class Solution {
    public String removeDuplicateLetters(String s) {
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
            stack.push(ch);
            visited[index] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.insert(0, stack.pop());
        return sb.toString();
    }
}
