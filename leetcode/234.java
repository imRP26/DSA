import java.util.*;

/*
 * https://leetcode.com/problems/palindrome-linked-list/
 */



// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}



// Very Naive Solution
class Solution1 {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int n = list.size();
        for (int i = 0; i < n / 2; i++) {
            if (list.get(i) != list.get(n - i - 1))
                return false;
        }
        return true;
    }
}



// Reversing the 2nd half of the linked list
class Solution2 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode fastPtr = head, slowPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        ListNode prevNode = null, currNode = slowPtr, nextNode = null;
        while (currNode != null) {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        while (head != null && prevNode != null) {
            if (head.val != prevNode.val)
                return false;
            head = head.next;
            prevNode = prevNode.next;
        }
        return true;
    }
}



// Approach of Recursion - comparing elements from both ends of the linked list in a stepwise manner
class Solution3 {

	ListNode ref;

	public boolean checkForPalindrome(ListNode node) {
		if (node == null)
			return true;
		boolean result = checkForPalindrome(node.next);
		boolean isEqual = (ref.val == node.val) ? true : false;
		ref = ref.next;
		return result && isEqual;
	}

    public boolean isPalindrome(ListNode head) {
        ref = head;
		return checkForPalindrome(head);
    }
}
