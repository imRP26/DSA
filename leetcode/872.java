/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


// Naive Solution
class Solution1 {

    public void dfs(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        dfs(root.left, list);
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        dfs(root.right, list);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        if (list1.size() != list2.size())
            return false;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i))
                return false;
        }
        return true;
    }
}


/*
 * Sexier Solution -> 
 * https://leetcode.com/problems/leaf-similar-trees/solutions/152329/c-java-python-o-h-space/
 */
class Solution2 {

    public int dfs(Stack<TreeNode> stack) {
        while (true) {
            TreeNode root = stack.pop();
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
            if (root.left == null && root.right == null)
                return root.val;
        }
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        Stack<TreeNode> stack1 = new Stack<>(), stack2 = new Stack<>();
        stack1.push(root1);
        stack2.push(root2);
        while (!stack1.empty() && !stack2.empty()) {
            if (dfs(stack1) != dfs(stack2))
                return false;
        }
        return (stack1.empty() && stack2.empty());
    }
}
