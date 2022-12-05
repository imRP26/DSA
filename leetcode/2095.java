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

class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null)
            return null;
        ListNode slowPtr = head, fastPtr = head, prevSlowPtr = null;
        while (fastPtr != null && fastPtr.next != null) {
            prevSlowPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        prevSlowPtr.next = slowPtr.next;
        return head;
    }
}
