/*
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
 */



/*
 * Approach 1 from -> 
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/solutions/950242/multiple-solution-approaches-in-java-with-comments-and-explanation/
 */
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = p.parent;
        }
        while (q != null) {
            if (set.contains(q))
                return q;
            q = q.parent;
        }
        return null;
    }
}



/*
 * Approach 2 from -> 
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/solutions/950242/multiple-solution-approaches-in-java-with-comments-and-explanation/
 */
class Solution {

    private int getNodeHeight(TreeNode node) {
        int h = 0;
        while (node != null) {
            node = node.parent;
            h++;
        }
        return h;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        int h1 = getNodeHeight(p), h2 = getNodeHeight(q);
        if (h1 < h2) {
            Node temp = q;
            q = p;
            p = temp;
        }
        int hdiff = Math.abs(h1 - h2);
        while (hdiff > 0) {
            p = p.parent;
            hdiff--;
        }
        while (p != q) {
            p = p.parent;
            q = q.parent;
        }
        return p;
    }
}



/*
 * Approach 3 from -> 
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/solutions/950242/multiple-solution-approaches-in-java-with-comments-and-explanation/
 * Approach 3 of LC Editorial of LC160
 */
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node node1 = p, node2 = q;
        while (node1 != node2) {
            node1 = (node1 == null) ? q : node1.parent;
            node2 = (node2 == null) ? p : node2.parent;
        }
        return node1;
    }
}
