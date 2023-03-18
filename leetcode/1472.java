/*
 * https://leetcode.com/problems/design-browser-history/
 */



/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */ 



/*
 * My approach of using a HashMap, mapping being from browser-index to browser-url.
 */ 
class BrowserHistory {

	private int index = 0, lenHistory = 0;
	private Map<Integer, String> indexToBrowser;

    public BrowserHistory(String homepage) {
        this.indexToBrowser = new HashMap<>();
		this.indexToBrowser.put(this.index, homepage);
		this.lenHistory = this.index + 1;
    }
    
    public void visit(String url) {
        int currIndex = this.index + 1;
		while (this.indexToBrowser.containsKey(currIndex))
			this.indexToBrowser.remove(currIndex++);
		this.indexToBrowser.put(this.index + 1, url);
		this.index++;
		this.lenHistory = this.index + 1;
    }
    
    public String back(int steps) {
        this.index = Math.max(0, this.index - steps);
		return this.indexToBrowser.get(this.index);
    }
    
    public String forward(int steps) {
        this.index = Math.min(this.index + steps, this.lenHistory - 1);
		return this.indexToBrowser.get(this.index);
    }
}



/*
 * Approach 1 of Two Stacks from official LC Editorial -> 
 * https://leetcode.com/problems/design-browser-history/editorial/
 * In C++, the stack is implemented using vectors, the push operation is simply updating the stack 
 * pointer and copying the string. The underlying vector class takes care of the reallocation and 
 * copy of the string, so the push operation is still an O(1) operation.
 * Similarly, in the case of Java, Python and most languages, the push operation on stack implemented 
 * using a dynamic array or list is O(1) as it is only updating the stack pointer and not copying the string.
 */
class BrowserHistory {

    private Stack<String> history, future;
    private String current;

    public BrowserHistory(String homepage) {
        this.history = new Stack<>();
        this.future = new Stack<>();
        this.current = homepage;
    }
    
    public void visit(String url) {
        this.history.push(this.current);
        this.current = url;
        this.future = new Stack<>();
    }
    
    public String back(int steps) {
        while (steps > 0 && !this.history.empty()) {
            this.future.push(this.current);
            this.current = this.history.pop();
            steps--;
        }
        return this.current;
    }
    
    public String forward(int steps) {
        while (steps > 0 && !this.future.empty()) {
            this.history.push(this.current);
            this.current = this.future.pop();
            steps--;
        }
        return this.current;
    }
}



/*
 * Approach 2 of DoublyLinkedList from official LC Editorial
 */
class DLLNode {
    
    String data;
    DLLNode prev, next;

    DLLNode(String url) {
        this.data = url;
        this.prev = null;
        this.next = null;
    }
}


class BrowserHistory {

    private DLLNode dllHead, current;

    public BrowserHistory(String homepage) {
        this.dllHead = new DLLNode(homepage);
        this.current = this.dllHead;
    }
    
    public void visit(String url) {
        DLLNode newNode = new DLLNode(url);
        this.current.next = newNode;
        newNode.prev = this.current;
        this.current = newNode; 
    }
    
    public String back(int steps) {
        while (steps > 0 && this.current.prev != null) {
            this.current = this.current.prev;
            steps--;
        }
        return this.current.data;
    }
    
    public String forward(int steps) {
        while (steps > 0 && this.current.next != null) {
            this.current = this.current.next;
            steps--;
        }
        return this.current.data;
    }
}



/*
 * Approach 3 of Dynamic Array from official LC editorial
 */
class BrowserHistory {

    private List<String> visitedURLs;
    private int currURL, lastURL;

    public BrowserHistory(String homepage) {
        this.visitedURLs = new ArrayList<>(Arrays.asList(homepage));
        this.currURL = 0;
        this.lastURL = 0;
    }
    
    public void visit(String url) {
        this.currURL += 1;
        if (this.visitedURLs.size() > this.currURL)
            this.visitedURLs.set(currURL, url);
        else 
            this.visitedURLs.add(url);
        this.lastURL = this.currURL;
    }
    
    public String back(int steps) {
        this.currURL = Math.max(0, this.currURL - steps);
        return this.visitedURLs.get(this.currURL);
    }
    
    public String forward(int steps) {
        this.currURL = Math.min(this.lastURL, this.currURL + steps);
        return this.visitedURLs.get(this.currURL);
    }
}
