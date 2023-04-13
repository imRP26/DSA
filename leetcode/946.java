/*
 * https://leetcode.com/problems/validate-stack-sequences/
 */



/*
 * Approach from LC Editorial - Stack Simulation
 */
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, n = pushed.length;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.empty() && i < n && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return i == n;
    }
}



/*
 * Using the pushed array as a stack, approach from -> 
 * https://leetcode.com/problems/validate-stack-sequences/solutions/197685/c-java-python-simulation-o-1-space/?orderBy=most_votes
 */
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = 0, j = 0, n = pushed.length;
        for (int x : pushed) {
            pushed[i++] = x;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                i--;
                j++;
            }
        }
        return j == n;
    }
}
