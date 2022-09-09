import java.util.*;

/*
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 */



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



// My Solution - need to be a bit better at reading the question properly FFS
class Solution {

	class Pair {
		TreeNode node;
		int x, y;
		Pair(TreeNode node, int x, int y) {
			this.node = node;
			this.x = x;
			this.y = y;
		}
	}

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, List<Pair>> map = new HashMap<>();
		Queue<Pair> queue = new LinkedList<>();
		queue.offer(new Pair(root, 0, 0));
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			min = Math.min(min, p.x);
			max = Math.max(max, p.y);
			if (!map.containsKey(p.x))
				map.put(p.x, new ArrayList<>());
			map.get(p.x).add(p);
			if (p.node.left != null)
				queue.offer(new Pair(p.node.left, p.x - 1, p.y + 1));
			if (p.node.right != null)
				queue.offer(new Pair(p.node.right, p.x + 1, p.y + 1));
		}
		for (int i = min; i <= max; i++) {
            if (map.get(i) == null)
                continue;
			Collections.sort(map.get(i), new Comparator<Pair>() {
				public int compare(Pair a, Pair b) {
					if (a.y == b.y)
						return a.node.val - b.node.val;
					return 0;
				}
			});
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < map.get(i).size(); j++)
				temp.add(map.get(i).get(j).node.val);
			result.add(temp);
		}
		return result;
	}
}
