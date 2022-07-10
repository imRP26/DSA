/*
 * Question Link -> 
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Easy, Recursion
class Solution1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left == null ? right : left;
    }
}



// Some thinking, Iterative Solution
class Solution2 { // SC = O(N), TC = O(N)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
		Queue<TreeNode> queue = new LinkedList<>();
		parent.put(root, null);
		queue.offer(root);
		while (!parent.containsKey(p) || !parent.containsKey(q)) {
			TreeNode node = queue.poll();
			if (node == null)
				continue;
			parent.put(node.left, node);
			parent.put(node.right, node);
			queue.add(node.left);
			queue.add(node.right);
		}
		Set<TreeNode> set = new HashSet<>();
		while (p != null) {
			set.add(p);
			p = parent.get(p);
		}
		while (!set.contains(q))
			q = parent.get(q);
		return q;
    }
}
