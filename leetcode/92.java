/*
 * https://leetcode.com/problems/reverse-linked-list-ii/
 */



/**
 * Definition for singly-linkedlist
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { 
        this.val = val; 
    }
    ListNode(int val, ListNode next) { 
        this.val = val; 
        this.next = next; 
    }
}



/*
 * My Approach! (Iterative)
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int pos = 1;
        ListNode startNode = null, endNode = null, prevStartNode = null, nextEndNode = null, currNode = head, prevNode = null;
        while (currNode != null) {
            if (pos == left) {
                startNode = currNode;
                prevStartNode = prevNode;
            }
            if (pos == right) {
                endNode = currNode;
                nextEndNode = currNode.next;
            }
            prevNode = currNode;
            currNode = currNode.next;
            pos++;
        }
        currNode = startNode;
        prevNode = null;
        endNode = startNode;
        while (currNode != nextEndNode) {
            ListNode nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        if (prevStartNode != null)
            prevStartNode.next = prevNode;
        endNode.next = nextEndNode;
        return (startNode != head) ? head : prevNode;
    }
}



/*
 * Recursive Approach from LC Official Editorial!
 */
// will try this 1 out later, now it just seems too complicated and the iterative approach
// seems pretty much intuitive!
