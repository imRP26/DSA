import java.util.*;


/*
 * Easy, Stack
 */
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int num2 = stack.peek();
                stack.pop();
                int num1 = stack.peek();
                stack.pop();
                if (token.equals("+"))
                    stack.push(num1 + num2);
                else if (token.equals("-"))
                    stack.push(num1 - num2);
                else if (token.equals("*"))
                    stack.push(num1 * num2);
                else
                    stack.push(num1 / num2);
            }
            else
                stack.push(Integer.valueOf(token));
        }
        return stack.peek();
    }
}
