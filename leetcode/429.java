import java.util.*;

/*
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 */



/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/



// Easy AF
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                root = queue.poll();
                temp.add(root.val);
                for (int j = 0; j < root.children.size(); j++)
                    queue.offer(root.children.get(j));
            }
            result.add(temp);
        }
        return result;
    }
}