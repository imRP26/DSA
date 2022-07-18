/*
 * Question Link -> https://leetcode.com/problems/remove-nth-node-from-end-of-list/
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
// My Naive Solution - 2 Passes
class Solution1 {
    
    public int listLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = listLength(head);
        if (n == len)
            return head.next;
        int index = len - n, pos = 0;
        ListNode prevNode = null, currNode = head, nextNode = null;
        while (pos < index) {
            nextNode = currNode.next;
            prevNode = currNode;
            currNode = nextNode;
            pos++;
        }
        prevNode.next = currNode.next;
        return head;
    }
}



// 2-Pointers based solution - 1 pass
