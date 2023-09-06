/*
 * https://leetcode.com/problems/split-linked-list-in-parts/
 */



/*
 * Approach from -> 
 * https://leetcode.com/problems/split-linked-list-in-parts/solutions/109296/java-c-clean-code/?envType=daily-question&envId=2023-09-06
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
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        int len = 0;
        ListNode temp = head, prev = null;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        int quo = len / k, rem = len % k;
        temp = head;
        for (int i = 0; i < k && temp != null; i++, rem--) {
            res[i] = temp;
            for (int j = 0; j < quo + (rem > 0 ? 1 : 0); j++) {
                prev = temp;
                temp = temp.next;
            }
            prev.next = null;
        }
        return res;
    }
}
