import java.util.*;

/*
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 */



// Definition for singly-linked list.
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



// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {
    }
    TreeNode(int val) { 
        this.val = val; 
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}



// My Naive Solution - TC = O(N), SC = O(N)
class Solution1 {
    
    TreeNode helper(int low, int high, List<Integer> list) {
        if (low > high)
            return null;
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = helper(low, mid - 1, list);
        root.right = helper(mid + 1, high, list);
        return root;
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        ListNode currNode = head;
        List<Integer> list = new ArrayList<>();
        while (currNode != null) {
            list.add(currNode.val);
            currNode = currNode.next;
        }
        return helper(0, list.size() - 1, list);
    }
}



/*
 * Using slow and fast pointers to compute the mid node of the linked list
 * TC = O(N * log(N)), SC = O()
 */
class Solution2 {

    TreeNode toBST(ListNode head, ListNode tail) {
        ListNode slowPtr = head, fastPtr = head;
        if (head == tail)
            return null;
        while (fastPtr != tail && fastPtr.next != tail) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
        }
        TreeNode root = new TreeNode(slowPtr.val);
        root.left = toBST(head, slowPtr);
        root.right = toBST(slowPtr.next, tail);
        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        return toBST(head, null);
    }
}



// Same way of doing things as above - but modifying the original linked list
class Solution3 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);
        ListNode slowPtr = head, prevPtr = null, fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        prevPtr.next = null;
        TreeNode root = new TreeNode(slowPtr.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slowPtr.next);
        return root;
    }
}
