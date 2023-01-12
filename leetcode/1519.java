/*
 * https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
 */



/*
 * DFS + Hashing based Approach
 */
class Solution1 {

    int[] result;
    Map<Integer, List<Integer> > graph = new HashMap<>();

    private int[] postorderDFS(String labels, int node, int parent) {
        int[] charCount = new int[26], temp = new int[26];
        int index = labels.charAt(node) - 'a';
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent)
                continue;
            temp = postorderDFS(labels, neighbor, node);
            for (int i = 0; i < 26; i++)
                charCount[i] += temp[i];
            result[node] += temp[index];
        }
        result[node]++;
        charCount[index]++;
        return charCount;
    }

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, val -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, val -> new ArrayList<>()).add(u);
        }
        result = new int[n];
        postorderDFS(labels, 0, -1);
        return result;
    }
}



/*
 * BFS Approach from 
 * https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/solutions/2864718/number-of-nodes-in-the-sub-tree-with-the-same-label/
 */
class Solution2 {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
		Map<Integer, Set<Integer> > graph = new HashMap<>();
		for (int[] edge : edges) {
			int u = edge[0], v = edge[1];
			graph.computeIfAbsent(u, val -> new HashSet<>()).add(v);
			graph.computeIfAbsent(v, val -> new HashSet<>()).add(u);
		}
		int[][] charCounts = new int[n][26];
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			charCounts[i][labels.charAt(i) - 'a']++;
			if (i != 0 && graph.get(i).size() == 1)
				queue.offer(i);
		}
		while (!queue.isEmpty()) {
			int node = queue.poll(), parent = graph.get(node).stream().findFirst().get();
			graph.get(parent).remove(node);
			for (int i = 0; i < 26; i++)
				charCounts[parent][i] += charCounts[node][i];
			if (parent != 0 && graph.get(parent).size() == 1)
				queue.offer(parent);
		}
		int[] result = new int[n];
		for (int i = 0; i < n; i++)
			result[i] = charCounts[i][labels.charAt(i) - 'a']++;
		return result;
    }
}
