import javafx.util.Pair;
import java.util.*;

/*
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/
 */



// Definition for a binary tree node
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
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Map<Integer, List<Integer>> nodeLevel = new TreeMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        Pair<TreeNode, Integer> p = new Pair(root, 0);
        queue.offer(p);
        while (!queue.isEmpty()) {
            p = queue.poll();
            int level = p.getValue();
            root = p.getKey();
            //nodeLevel.putIfAbsent(level, new ArrayList<>());
            if (!nodeLevel.containsKey(level))
                nodeLevel.put(level, new ArrayList<>());
            nodeLevel.get(level).add(root.val);
            if (root.left != null)
                queue.offer(new Pair(root.left, level - 1));
            if (root.right != null)
                queue.offer(new Pair(root.right, level + 1));
        }
        int index = -1;
        for (Map.Entry<Integer, List<Integer>> entry : nodeLevel.entrySet()) {
            List<Integer> list = entry.getValue();
            result.add(new ArrayList<>());
            index++;
            for (int i = 0; i < list.size(); i++)
                result.get(index).add(list.get(i));
        }
        return result;
    }
}
