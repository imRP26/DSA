/*
 * https://leetcode.com/problems/sort-list/
 */



// 
class ListNode {
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
 


// Approach of Merge Sort
class Solution1 {

    ListNode merge(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode(0), temp = newHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                temp.next = head1;
                head1 = head1.next;
            }
            else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        if (head1 != null)
            temp.next = head1;
        if (head2 != null)
            temp.next = head2;
        return newHead.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slowPtr = head, fastPtr = head, prevPtr = null;
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        prevPtr.next = null;
        ListNode head1 = sortList(head), head2 = sortList(slowPtr);
        return merge(head1, head2);
    }
}



/*
 * Referred from the following link :-
 * https://leetcode.com/problems/sort-list/discuss/46767/Java-solution-with-strict-O(1)-auxiliary-space-complexity
 */
// TBD later, this question seems really complex!!
