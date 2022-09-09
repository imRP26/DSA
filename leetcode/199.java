import java.util.*;

/*
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */



// Definition for a Binary Tree Node
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



// My BFS Solution
class Solution1 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode prevNode = root;
        queue.offer(root);
        queue.offer(null);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root == null) {
                if (!queue.isEmpty())
                    queue.offer(null);
                result.add(prevNode.val);
                continue;
            }
            prevNode = root;
            if (root.left != null)
                queue.offer(root.left);
            if (root.right != null)
                queue.offer(root.right);
        }
        return result;
    }
}



// DFS Solution
class Solution2 {

    void rightView(TreeNode currNode, List<Integer> result, int currDepth) {
        if (currNode == null)
            return;
        if (currDepth == result.size())
            result.add(currNode.val);
        rightView(currNode.right, result, currDepth + 1);
        rightView(currNode.left, result, currDepth + 1);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }
}
