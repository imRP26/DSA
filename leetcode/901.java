import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/online-stock-span/
*/



// My Naive Approach
class StockSpanner1 {

    int[] stockPrices;
    int lastIndex = 0;
    
    public StockSpanner1() {
        stockPrices =  new int[10002];
    }
    
    public int next(int price) {
        stockPrices[lastIndex] = price;
        int result = 0, i = lastIndex;
        while (i >= 0 && price >= stockPrices[i]) {
            i--;
            result++;
        }
        lastIndex++;
        return result;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */



/* 
 * Approach using Monotonic Stacks (Best Approach) :-
 * We should have a stack of a pair of 
 * <current  price, maximum number of consecutive days). 
 * This is to avoid the dependence upon access to indicies.
 * In the below method, we don't go through comparing the current price with 
 * every index's price, we just go through some of them.
*/
class StockSpanner2 {

    Stack<int[]> stack;

    public StockSpanner2() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && price >= stack.peek()[0])
            span += stack.pop()[1];
        stack.push(new int[] {price, span});
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */



// Same Approach as above, using LinkedList
class StockSpanner3 {

    LinkedList<Integer> prices;
    LinkedList<Integer> spans;

    public StockSpanner3() {
        prices = new LinkedList<>();
        spans = new LinkedList<>();
    }

    public int next(int price) {
        int span = 1, index = prices.size() - 1;
        while (index >= 0 && price >= prices.get(index)) {
            span += spans.get(index);
            index -= spans.get(index);
        }
        spans.add(span);
        prices.add(price);
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
