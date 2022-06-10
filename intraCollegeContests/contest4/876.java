/*
 * Question Link -> https://leetcode.com/problems/middle-of-the-linked-list/
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
// My Naive Solution - 2 passes
class Solution1 {
    
    public int findLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
    
    public ListNode middleNode(ListNode head) {
        int length = findLength(head);
        int position = 0;
        while (true) {
            if (position == length / 2)
                return head;
            head = head.next;
            position++;
        }
    }
}



// Method of Slow and Fast Pointers - 1 pass only
class Solution2 {
    public ListNode middleNode(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        return slowPtr;
    }
}
