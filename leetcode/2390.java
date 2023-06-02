/*
 * https://leetcode.com/problems/removing-stars-from-a-string/
 */



/*
 * Simple Stack based Solution
 */
class Solution {
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '*') {
                if (stack.empty())
                    continue;
                stack.pop();
            }
            else
                stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty())
            sb.insert(0, stack.pop());
        /*
         * while (!stack.isEmpty())
         *     sb.append(stack.pop());   
         * return sb.reverse().toString(); 
         */
        return sb.toString();
    }
}



/*
 * Using a StringBuilder - from LC Official Editorial
 */
class Solution {
	public String removeStars(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '*')
				sb.deleteCharAt(sb.length() - 1); // O(1)
			else 
				sb.append(s.charAt(i));
		}
		return sb.toString();
	}
}



/*
 * 
 */

