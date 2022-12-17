import java.util.*;


/*
 * Naive Version -> Costly Pop() and Peek() 
 */
class MyQueue1 {

    Stack<Integer> stack1, stack2;

    public MyQueue1() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void push(int x) {
        stack1.push(x);
    }
    
    public int pop() {
        while (!stack1.empty()) {
            stack2.push(stack1.peek());
            stack1.pop();
        }
        int x = stack2.peek();
        stack2.pop();
        while (!stack2.empty()) {
            stack1.push(stack2.peek());
            stack2.pop();
        }
        return x;
    }
    
    public int peek() {
        while (!stack1.empty()) {
            stack2.push(stack1.peek());
            stack1.pop();
        }
        int x = stack2.peek();
        while (!stack2.empty()) {
            stack1.push(stack2.peek());
            stack2.pop();
        }
        return x;
    }
    
    public boolean empty() {
        return stack1.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */



/*
 * Naive Version -> Costly Push()
 * Required Solution...
 * https://leetcode.com/problems/implement-queue-using-stacks/solutions/127533/implement-queue-using-stacks/?orderBy=most_votes
 */ 
class MyQueue2 {

    Stack<Integer> stack1, stack2;

    public MyQueue2() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void push(int x) {
        while (!stack1.empty()) {
            stack2.push(stack1.peek());
            stack1.pop();
        }
        stack2.push(x);
        while (!stack2.empty()) {
            stack1.push(stack2.peek());
            stack2.pop();
        }
    }
    
    public int pop() {
        int x = stack1.peek();
        stack1.pop();
        return x;
    }
    
    public int peek() {
        int x = stack1.peek();
        return x;
    }
    
    public boolean empty() {
        return stack1.empty();
    }
}
