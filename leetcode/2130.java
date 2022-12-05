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


/*
 * Find the middle node and then reverse the 2nd half of the linekd list.
 */
class Solution {
    public int pairSum(ListNode head) {
        ListNode slowPtr = head, fastPtr = head, prevSlowPtr = head, nextSlowPtr = null;
        while (fastPtr != null && fastPtr.next != null) {
            prevSlowPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        if (fastPtr != null) {
            prevSlowPtr = slowPtr;
            slowPtr = slowPtr.next;
        }
        prevSlowPtr.next = null;
        while (slowPtr != null) {
            nextSlowPtr = slowPtr.next;   
            slowPtr.next = prevSlowPtr;
            prevSlowPtr = slowPtr;
            slowPtr = nextSlowPtr;
        }
        slowPtr = prevSlowPtr;
        fastPtr = head;
        int maxSum = Integer.MIN_VALUE;
        while (slowPtr != null && fastPtr != null) {
            maxSum = Math.max(maxSum, slowPtr.val + fastPtr.val);
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next;
        }
        return maxSum;
    }
}
