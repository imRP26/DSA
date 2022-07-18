/*
 * Question Link -> 
 * https://binarysearch.com/problems/Linked-List-to-ZigZag-Tree-Path
 */



/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
/**
 * class LLNode {
 *   int val;
 *   LLNode next;
 * }
 */
// My (Naive, Recursive) Solution
class Solution1 {
    public Tree solve(LLNode node) {
        Tree root = new Tree();
        if (node == null) {
            root = null;
            return root;
        }
        root.val = node.val;
        if (node.next != null && node.val > node.next.val)
            root.left = solve(node.next);
        else if (node.next != null && node.val <= node.next.val)
            root.right = solve(node.next);
        return root;
    }
}



// A Better recursive approach
class Solution2 {
    public Tree solve(LLNode node) {
        if (node == null)
            return null;
        if (node.next == null)
            return new Tree(node.val);
        Tree root = new Tree(node.val);
        if (node.next.val < node.val)
            root.left = solve(node.next);
        else
            root.right = solve(node.next);
        return root;
    }
}



// An iterative approach
class Solution3 {
    public Tree solve(LLNode node) {
        if (node == null)
            return null;
        Tree root = new Tree();
        root.val = node.val;
        Tree currNode = root;
        node = node.next;
        while (node != null) {
            if (node.val < currNode.val) {
                currNode.left = new Tree();
                currNode.left.val = node.val;
                currNode = currNode.left;
            }
            else {
                currNode.right = new Tree();
                currNode.right.val = node.val;
                currNode = currNode.right;
            }
            node = node.next;
        }
        return root;
    }
}
