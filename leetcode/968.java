/*
 * https://leetcode.com/problems/binary-tree-cameras/
 */



/**
 * Definition for a binary tree node.
 * 
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}



/*
 * Referred to the approach from Kartik Arora's video!!
 */
class Solution {

    /*
     * DP State :-
     * dp(node, placeCam, parentCam) = minimum number of nodes required to cover the 
	 * entire subtree at node, given the values of 'placeCam' and 'parentCam'.
	 * 
	 * Assumption :-
	 * The root has a dummy node, wherein no camera needs to be kept!
	 * 
	 * DP Transitions :-
	 * dp(node, 1, 0) = 1 + min{dp(C1, 0, 1), dp(C1, 1, 1)} + min{dp(C2, 0, 1), dp(C2, 1, 1)}
	 * dp(node, 1, 1) = 1 + min{dp(C1, 0, 1), dp(C1, 1, 1)} + min{dp(C2, 0, 1), dp(C2, 1, 1)}
	 * dp(node, 0, 1) = min{dp(C1, 0, 0), dp(C1, 1, 0)} + min{dp(C2, 0, 0), dp(C2, 1, 0)}
	 * dp(node, 0, 0) = min[dp(C1, 1, 0) + min{dp(C2, 0, 0), dp(C2, 1, 0)}, 
	 * 						dp(C2, 1, 0) + min{dp(C1, 0, 0), dp(C1, 1, 0)}]
	 *
	 * Base Cases :-
	 * dp(leaf, 1, 0) = 1
	 * dp(leaf, 1, 1) = 1
	 * dp(leaf, 0, 1) = 0
	 * dp(leaf, 0, 0) = INT_MAX => We're unable to cover the entire tree!
     * dp(NULL, 1, 0) = INT_MAX
     * dp(NULL, 1, 1) = INT_MAX
     * dp(NULL, 0, 1) = 0
     * dp(NULL, 0, 0) = 0
	 * 
	 * Final Answer :- 
	 * max{dp(root, 1, 0), dp(root, 0, 0)}
     */

    private Map<Pair<TreeNode, Pair<Boolean, Boolean> >, Integer> dp = new HashMap<>();
    private int inf = (int)1e6;
    
    private int memoization(TreeNode node, boolean nodeCam, boolean parentCam) {
        if (node == null) // case of empty tree
            return nodeCam ? inf : 0;
        if (node.left == null && node.right == null) // leaf node edge case
            return nodeCam ? 1 : parentCam ? 0 : inf;
        Pair<TreeNode, Pair<Boolean, Boolean> > p = new Pair<>(node, new Pair<>(nodeCam, parentCam));
        if (dp.containsKey(p))
            return dp.get(p);
        if (nodeCam) {
            int val1 = Math.min(memoization(node.left, true, true), memoization(node.left, false, true));
            int val2 = Math.min(memoization(node.right, true, true), memoization(node.right, false, true));
            dp.put(p, 1 + val1 + val2);
        }
        else if (parentCam) { // nodeCam == 0 && parentCam == 1
            int val1 = Math.min(memoization(node.left, false, false), memoization(node.left, true, false));
            int val2 = Math.min(memoization(node.right, false, false), memoization(node.right, true, false));
            dp.put(p, val1 + val2);
        }
        else {
            int val1 = memoization(node.left, true, false) + Math.min(memoization(node.right, false, false), memoization(node.right, true, false));
            int val2 = memoization(node.right, true, false) + Math.min(memoization(node.left, false, false), memoization(node.left, true, false));
            dp.put(p, Math.min(val1, val2));
        }
        return dp.get(p);
    }

    public int minCameraCover(TreeNode root) {
        return Math.min(memoization(root, false, false), memoization(root, true, false));
    }
}
