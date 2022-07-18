/* 
 * Question Link -> https://leetcode.com/problems/swap-nodes-in-pairs/
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
// Solution involving swapping of node values
class Solution1 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || (head != null && head.next == null))
            return head;
        ListNode node = head;
        while (node != null) {
            if (node.next == null)
                break;
            int temp = node.val;
            node.val = node.next.val;
            node.next.val = temp;
            node = node.next.next;
        }
        return head;
    }
}



// Approach not involving swapping values of adjacent nodes
class Solution2 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || (head != null && head.next == null))
            return head;
        ListNode currNode = head, nextNode = null, prevNode = null;
        head = null;
        while (currNode != null) {
            nextNode = currNode.next;
            if (nextNode == null)
                break;
            if (head == null)
                head = nextNode;
            if (prevNode != null)
                prevNode.next = nextNode; // for maintaining link
            currNode.next = nextNode.next;
            nextNode.next = currNode;
            prevNode = currNode;
            currNode = currNode.next;
        }
        return head;
    }
}



// Recursion
class Solution3 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode node = head.next;
        head.next = swapPairs(head.next.next);
        node.next = head;
        return node;
    }
}
