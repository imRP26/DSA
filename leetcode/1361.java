/*
 * https://leetcode.com/problems/validate-binary-tree-nodes/
 */



/*
 * FUCK MY LIFE!!!
 * Conditions to be checked :-
 * (1) There must be 1 root - the root is a node that's got no parent.
 * (2) Every node other than the root must have exactly 1 parent.
 * (3) The tree must be connected.
 * (4) There can't exist a cycle.
 */
class Solution {

    private Set<Integer> leftChildrenSet = new HashSet<>(), rightChildrenSet = new HashSet<>();
    private Map<Integer, List<Integer> > graph = new HashMap<>();

    private int findRoot(int n) {
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (!leftChildrenSet.contains(i) && !rightChildrenSet.contains(i)) {
                if (root != -1)
                    return -1;
                root = i;
            }
        }
        return root;
    }

    private int bfs(int root) { // condition 3
        int nodes = 0;
        Set<Integer> vis = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int node = q.poll();
            if (!vis.add(node))
                continue;
            nodes++;
            for (int child : graph.getOrDefault(node, Collections.emptyList())) {
                if (!vis.contains(child))
                    q.offer(child);
            }
        }
        return nodes;
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] numParents = new int[n];
        int edges = 0;
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                if (++numParents[leftChild[i]] > 1) // condition 2
                    return false;
                edges++;
                graph.computeIfAbsent(i, k -> new ArrayList<>()).add(leftChild[i]);
            }
            if (rightChild[i] != -1) {
                if (++numParents[rightChild[i]] > 1) // condition 2
                    return false;
                edges++;
                graph.computeIfAbsent(i, k -> new ArrayList<>()).add(rightChild[i]);
            }
            leftChildrenSet.add(leftChild[i]);
            rightChildrenSet.add(rightChild[i]);
        }
        int root = findRoot(n);
        if (root == -1) // condition 1
            return false;
        if (edges != n - 1) // condition 4
            return false;
        return bfs(root) == n; // condition 3
    }
}
