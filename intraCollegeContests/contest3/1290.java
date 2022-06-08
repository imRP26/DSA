/*
 * Question Link -> 
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
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
class Solution1 {
    
    public int listLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
    
    public int getDecimalValue(ListNode head) {
        int length = listLength(head) - 1, result = 0;
        while (head != null) {
            if (head.val == 1)
                result += Math.pow(2, length);
            length--;
            head = head.next;
        }
        return result;
    }
}



// A better solution
class Solution2 {
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            result *= 2;
            result += head.val;
            head = head.next;
        }
        return result;
    }
}
