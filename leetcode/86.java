/*
 * https://leetcode.com/problems/partition-list/
 */



/**
 * Definition for singly-linked list.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {
    }
    ListNode(int val) { 
        this.val = val; 
    }
    ListNode(int val, ListNode next) { 
        this.val = val; 
        this.next = next; 
    }
}



/*
 * Naive Method
 */

class Solution {
    public ListNode partition(ListNode head, int x) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            if (node.val < x)
                list1.add(node.val);
            else
                list2.add(node.val);
            node = node.next;
        }
        list1.addAll(list2);
        ListNode res = new ListNode(), temp = res;
        for (int i = 0; i < list1.size(); i++) {
            temp.next = new ListNode(list1.get(i));
            temp = temp.next;
        }
        return res.next;
    }
}



/*
 * Simple List traversal approach from LC official editorial!
 * Line #71 is important, otherwise cycle tends to exist!
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode head1 = new ListNode(), head2 = new ListNode(), node1 = head1, node2 = head2;
        while (head != null) {
            if (head.val < x) {
                node1.next = head;
                node1 = node1.next;
            }
            else {
                node2.next = head;
                node2 = node2.next;
            }
            head = head.next;
        }
        node2.next = null;
        node1.next = head2.next;
        return head1.next;
    }
}
