/*
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 */



// Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}



/*
 * Approach of HashTable from LC Official Editorial!
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB))
                break;
            headB = headB.next;
        }
        return headB;
    }
}



/*
 * Approach of 2-Pointers from LC Official Editorial!
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA, node2 = headB;
        while (node1 != node2) {
            node1 = (node1 != null) ? node1.next : headB;
            node2 = (node2 != null) ? node2.next : headA;
        }
        return node1;
    }
}
