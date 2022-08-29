import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/clone-graph/
 */



// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

// Bohot hi saare comments hain, lekin phir bhi bohot zyada complicated hai
class Solution1 {

    public void dfs(Node node, Node copiedNode, Node[] visited) {
        /*
         * storage of the current node at its val's index which tells that the 
         * node is now visited
         */ 
        visited[copiedNode.val] = copiedNode;
        // Traversing for the adjacent nodes of the root node
        for (Node neighbor : node.neighbors) {
            /*
             * Checking whether this neighbor-node has been visited or not, if 
             * its not visited, then there must be NULL.
             */
            if (visited[neighbor.val] == null) {
                // creation of a new node now that the neighbor isn't visited
                Node newNode = new Node(neighbor.val);
                // addition of this node as the neighbor of the previously copied node
                copiedNode.neighbors.add(newNode);
                // making DFS calls for this node to account for its neighbor nodes
                dfs(neighbor, newNode, visited);
            }
            else
                /*
                 * If that neighbor node has already been visited, then its 
                 * retrieved from visited array & then added as the adjacent 
                 * node of the previously copied node.
                 * AND THIS IS THE REASON of using NODE[] instead of BOOLEAN[] 
                 * array.
                 */
                copiedNode.neighbors.add(visited[neighbor.val]);
        }
    }

    public Node cloneGraph(Node node) {
        // if the actual node is empty, then there's nothing to copy
        if (node == null)
            return null;
        // creation of a new node with the same value as that of the given node
        Node copiedNode = new Node(node.val);
        Node[] visited = new Node[101];
        Arrays.fill(visited, null);
        dfs(node, copiedNode, visited);
        return copiedNode;
    }
}
