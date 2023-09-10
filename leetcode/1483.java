/*
 * https://leetcode.com/problems/kth-ancestor-of-a-tree-node/
 */



/*
 * Approach of Binary Lifting from Priyansh's TLE-Eliminators Graphs & Trees BootCamp!
 * Can also refer to videos on Priyansh's and Kartik Arora's channel!
 */
class TreeAncestor {

	private int numIter;
	private int[][] kthParent;
    
    public TreeAncestor(int n, int[] parent) {
        numIter = (int)(Math.log(n) / Math.log(2));
        kthParent = new int[numIter + 1][n];
        for (int[] row : kthParent)
        	Arrays.fill(row, -1);
        for (int i = 1; i < n; i++)
            kthParent[0][i] = parent[i];
        for (int j = 1; j <= numIter; j++) {
        	for (int i = 0; i < n; i++) {
        		int intermediate = kthParent[j - 1][i];
        		kthParent[j][i] = (intermediate == -1) ? -1 : kthParent[j - 1][intermediate];
        	}
        }
    }
    
    public int getKthAncestor(int node, int k) {
        for (int i = 0; i <= numIter; i++) {
        	if ((k & (1 << i)) != 0)
        		node = kthParent[i][node];
        	if (node == -1)
        		break;
        }
        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */



