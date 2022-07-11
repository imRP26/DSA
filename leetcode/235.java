import javax.lang.model.util.ElementScanner14;

/*
 * Question Link -> 
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
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
// Generic, recursive solution for any tree
class Solution1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode leftSubtree = lowestCommonAncestor(root.left, p, q);
        TreeNode rightSubtree = lowestCommonAncestor(root.right, p, q);
        if (leftSubtree != null && rightSubtree != null)
            return root;
        return leftSubtree == null ? rightSubtree : leftSubtree;
    }
}



// Generic, Iterative Solution for any tree
class Solution2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
		Queue<TreeNode> queue = new LinkedList<>();
		map.put(root, null);
		queue.offer(root);
		while (!map.containsKey(p) || !map.containsKey(q)) {
			root = queue.poll();
			if (root == null)
				continue;
			map.put(root.left, root);
			map.put(root.right, root);
			queue.add(root.left);
			queue.add(root.right);
		}
		Set<TreeNode> set = new HashSet<>();
		while (p != null) {
			set.add(p);
			p = map.get(p);
		}
		while (!set.contains(q))
			q = map.get(q);
		return q;
    }
}



// Recursive Solution for computing the LCA of a BST
class Solution3 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}



// Iterative Solution corresponding to the above recursion
class Solution4 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val > p.val && root.val > q.val)
                root = root.left;
            else if (root.val < p.val && root.val < q.val)
                root = root.right;
            else
                break;
        }
        return root;
    }
}
