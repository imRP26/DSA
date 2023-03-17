/*
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
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
 * Approach 1 from -> 
 * https://leetcode.com/problems/clone-binary-tree-with-random-pointer/editorial/
 */
class Solution1 {
	
	private Map<Node, NodeCopy> oldToNewMappings = new HashMap<>();
	
	private NodeCopy  deepCopy(Node root) {
		if (root == null)
			return null;
		NodeCopy newRoot = new NodeCopy(root.val);
		// deep-copying left subtree of current node and attaching it on current node's left
		newRoot.left = deepCopy(root.left);
		// deep-copying right subtree of current node and attaching it on current node's right
		newRoot.right = deepCopy(root.right);
		// storing the pair of old and new nodes in the hashmap
		oldToNewMappings.put(root, newRoot);
		return newRoot;
	}
	
	private void mapRandomPointers(Node oldNode) {
		if (root == null)
			return null;
		NodeCopy newNode = oldToNewMappings.get(oldNode);
		Node oldRandomNode = oldNode.random;
		NodeCopy newRandomNode = oldToNewMappings.get(oldRandomNode);
		newNode.random = newRandomNode;
		mapRandomPointers(oldNode.left);
		mapRandomPointres(oldNode.right);
	}
	
    public NodeCopy copyRandomBinaryTree(Node root) {
		// creation of a new tree for each node of old tree
        NodeCopy newRoot = deepCopy(root);
		// assignment of random pointers of new tree to respective correct nodes
		mapRandomPointers(root);
		return newRoot;
    }
}



/* 
 * Approach 2 (1 Pass DFS) from the above editorial, treating the given tree as a connected Graph
 */
class Solution2 {
	
	private Map<Node, NodeCopy> seen = new HashMap<>();
	
	private NodeCopy dfs(Node root) {
		if (root == null)
			return null;
		if (seen.containsKey(root))
			return seen.get(root);
		NodeCopy newRoot = new NodeCopy(root.val);
		seen.put(root, newRoot);
		newRoot.left = dfs(root.left);
		newRoot.right = dfs(root.right);
		newRoot.random = dfs(root.random);
		return newRoot;
	}
	
    public NodeCopy copyRandomBinaryTree(Node root) {
    	NodeCopy newRoot = dfs(root);
		return newRoot;
    }
}



/*
 * Approach 3 (BFS) from the above editorial
 */
class Solution {
	
    private Map<Node, NodeCopy> visited = new HashMap<>();

	private NodeCopy bfs(Node root) {
		if (root == null)
			return null;
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		visited.put(root, new NodeCopy(root.val));
		while (!queue.isEmpty()) {
			Node oldNode = queue.poll();
			NodeCopy newNode = visited.get(oldNode);
			if (oldNode.left != null) {
				if (!visited.containsKey(oldNode.left)) {
					queue.offer(oldNode.left);
					visited.put(oldNode.left, new NodeCopy(oldNode.left.val));
				}
				newNode.left = visited.get(oldNode.left);
			}
			if (oldNode.right != null) {
				if (!visited.containsKey(oldNode.right)) {
					queue.offer(oldNode.right);
					visited.put(oldNode.right, new NodeCopy(oldNode.right.val));
				}
				newNode.right = visited.get(oldNode.right);
			}
			if (oldNode.random != null) {
				if (!visited.containsKey(oldNode.random)) {
					queue.offer(oldNode.random);
					visited.put(oldNode.random, new NodeCopy(oldNode.random.val));
				}
				newNode.random = visited.get(oldNode.random);
			}
		}
		return visited.get(root);
	}
	
    public NodeCopy copyRandomBinaryTree(Node root) {
        NodeCopy newRoot = bfs(root);
		return newRoot;
    }
}
