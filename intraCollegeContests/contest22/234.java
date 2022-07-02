import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/palindrome-linked-list/
 */



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// My Naive Solution using O(N) auxiliary space
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



/*
 * Concept of Fast and Slow Pointers - the slow pointer will reach the middle 
 * point of the linked list. Now, the 2nd half of the linked list is reversed 
 * and then compared with the 1st half.
 */
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



/*
 * "ref" points to the head node initially. Recursive calls are made until the 
 * last element is reached. 
 * Upon returning from each recurssion, equality with "ref" values is checked. 
 * "ref" values are updated to in each recurssion.
 */
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
