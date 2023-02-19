/*
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */



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



/*
 * My BFS Solution - seems clean to me!!
 */
class Solution1 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer> > result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(new TreeNode(-200));
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.val == -200 || root.val == -300) {
                if (root.val == -300) {
                    if (!queue.isEmpty())
                        queue.offer(new TreeNode(-200));
                    Collections.reverse(list);
                }
                else {
                    if (!queue.isEmpty())
                        queue.offer(new TreeNode(-300));
                }
                result.add(new ArrayList<>(list));
                list.clear();
                continue;
            }
            list.add(root.val);
            if (root.left != null)
                queue.offer(root.left);
            if (root.right != null)
                queue.offer(root.right);
        }
        return result;
    }
}



/*
 * DFS Approach from -> 
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/solutions/461703/binary-tree-zigzag-level-order-traversal/
 */
class Solution2 {

	private void dfs(TreeNode node, int level, List<List<Integer> > result) {
		if (level >= result.size()) {
			List<Integer> newLevel = new ArrayList<>();
			newLevel.add(node.val);
			result.add(newLevel);
		}
		else {
			if (level % 2 == 0)
				result.get(level).add(node.val);
			else 
				result.get(level).add(0, node.val);
		}
		if (node.left != null)
			dfs(node.left, level + 1, result);
		if (node.right != null)
			dfs(node.right, level + 1, result);
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer> > result = new ArrayList<>();
        if (root == null)
            return result;
		dfs(root, 0, result);
		return result;
	} 
}
