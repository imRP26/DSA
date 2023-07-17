/*
 * https://leetcode.com/problems/add-two-numbers-ii/
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



/*
 * Messy Solution
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String n1 = "", n2 = "", n = "";
        ListNode h1 = l1, h2 = l2;
        while (h1 != null) {
            n1 = n1 + String.valueOf(h1.val);
            h1 = h1.next;
        }
        while (h2 != null) {
            n2 = n2 + String.valueOf(h2.val);
            h2 = h2.next;
        }
        System.out.println(n1);
        System.out.println(n2);
        while (n1.length() < n2.length())
            n1 = '0' + n1;
        while (n2.length() < n1.length())
            n2 = '0' + n2;
        int c = 0;
        for (int i = n1.length() - 1; i >= 0; i--) {
            int s = (n1.charAt(i) - '0') + (n2.charAt(i) - '0') + c;
            c = s / 10;
            s %= 10;
            n = String.valueOf(s) + n;
        }
        if (c != 0)
            n = String.valueOf(c) + n;
        ListNode head = new ListNode(-1), node = head;
        for (int i = 0; i < n.length(); i++) {
            node.next = new ListNode(n.charAt(i) - '0');
            node = node.next;
        }
        head = head.next;
        return head;
    }
}



/*
 * Approch of Reversal of Linked Lists from LC Official Editorial!
 */
class Solution {

    private ListNode reversal(ListNode currNode) {
        ListNode prevNode = null, nextNode;
        while (currNode != null) {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        return prevNode;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rev1 = reversal(l1), rev2 = reversal(l2);
        int sum = 0, carry = 0;
        ListNode res = new ListNode();
        while (rev1 != null || rev2 != null) {
            sum += (rev1 != null) ? rev1.val : 0;
            sum += (rev2 != null) ? rev2.val : 0;
            carry = sum / 10;
            res.val = sum % 10;
            ListNode node = new ListNode(carry);
            node.next = res;
            res = node;
            sum = carry;
            rev1 = (rev1 != null) ? rev1.next : null;
            rev2 = (rev2 != null) ? rev2.next : null;
        }
        return carry == 0 ? res.next : res;
    }
}



/*
 * Approach of Stacks from LC Official Editorial!
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>(), stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0, sum = 0;
        ListNode res = new ListNode();
        while (!stack1.empty() || !stack2.empty()) {
            sum += (stack1.empty()) ? 0 : stack1.pop();
            sum += (stack2.empty()) ? 0 : stack2.pop();
            carry = sum / 10;
            res.val = sum % 10;
            ListNode head = new ListNode(carry);
            head.next = res;
            res = head;
            sum = carry;
        }
        return carry == 0 ? res.next : res;
    }
}
