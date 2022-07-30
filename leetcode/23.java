import java.util.*;

/*
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */



// Definition for singly-linked list
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



// My (Naive?) Approach -> TC = O(N log(N)), SC = O(N)
class Solution1 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        for (int i = 0; i < lists.length; i++) { // O(N log(N))
            ListNode temp = lists[i];
            while (temp != null) {
                priorityQueue.add(temp.val);
                temp = temp.next;
            }
        }
        if (priorityQueue.isEmpty())
            return null;
        ListNode head = new ListNode(priorityQueue.poll());
        ListNode temp = head;
        /*
         * According to Oracle documentation: "Implementation note: this implementation 
         * provides O(log(n)) time for the enqueing and dequeing methods (offer, 
         * poll, remove() and add); linear time for the remove(Object) and 
         * contains(Object) methods; and constant time for the retrieval methods 
         * (peek, element, and size)."
         */
        while (!priorityQueue.isEmpty()) {
            temp.next = new ListNode(priorityQueue.poll()); // O(1)
            temp = temp.next;
        }
        return head;
    }
}



// Same method, but another way of solving it
class Solution2 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        PriorityQueue<ListNode> minpq = new PriorityQueue<ListNode>(lists.length, (a, b) -> (a.val - b.val));
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (ListNode node : lists) {
            if (node != null)
                minpq.offer(node);
        }
        while (!minpq.isEmpty()) {
            tail.next = minpq.poll();
            tail = tail.next;
            if (tail.next != null)
                minpq.offer(tail.next);
        }
        return dummy.next;
    }
}



// Using Recursion
class Solution3 {

    ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null)
            return (list1 == null) ? list2 : list1;
        if (list1.val < list2.val) {
            list1.next = merge(list1.next, list2);
            return list1;
        }
        list2.next = merge(list1, list2.next);
        return list2;
    }

    ListNode partition(ListNode[] lists, int low, int high) {
        if (low == high)
            return lists[low];
        if (low > high)
            return null;
        int mid = low + (high - low) / 2;
        ListNode list1 = partition(lists, low, mid);
        ListNode list2 = partition(lists, mid + 1, high);
        return merge(list1, list2);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return partition(lists, 0, lists.length - 1);
    }
}
