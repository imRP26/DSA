/*
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */



/*
 * Messy, BFS Approach -> Don't forget the pain from map.computeIfAbsent :(
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
class Solution {

    Map<Integer, List<Integer> > graph = new HashMap<>();

    void bfs(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        Queue<TreeNode[]> q = new LinkedList<>();
        q.offer(new TreeNode[] {root, dummy});
        while (!q.isEmpty()) {
            TreeNode[] temp = q.poll();
            TreeNode node = temp[0], parent = temp[1];
            if (node != root)
                graph.computeIfAbsent(node.val, k -> new ArrayList<>()).add(parent.val);
            if (node.left != null) {
                graph.computeIfAbsent(node.val, k -> new ArrayList<>()).add(node.left.val);
                q.offer(new TreeNode[] {node.left, node});
            }
            if (node.right != null) {
                graph.computeIfAbsent(node.val, k -> new ArrayList<>()).add(node.right.val);
                q.offer(new TreeNode[] {node.right, node});
            }
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        bfs(root);
        List<Integer> res = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {target.val, 0});
        Set<Integer> vis = new HashSet<>();
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int node = temp[0], level = temp[1];
            if (vis.contains(node))
                continue;
            vis.add(node);
            if (level == k)
                res.add(node);
            for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                if (!vis.contains(neighbor))
                    q.offer(new int[] {neighbor, level + 1});
            }
        }
        return res;
    }
}



/*
 * Will try out the DFS solutions later, but they seem to be tough and quite unintuitive!
 * By DFS, its meant the hashmap and recursion based solutions and not the ones involving 
 * the building of a separate graph!
 */
