/*
 * https://leetcode.com/problems/shortest-path-visiting-all-nodes/
 */



/*
 * Approach of BFS and Bitmasking from LC Official Editorial!
 */
class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length, endingMask = (1 << n) - 1, res = 0;
        if (n > 1) {
            boolean[][] vis = new boolean[n][endingMask];
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < n; i++) { // adding all possible start nodes
                q.offer(new int[] {i, 1 << i});
                vis[i][1 << i] = true;
            }
            q.offer(new int[] {-1, -1});
            while (!q.isEmpty()) {
                int[] temp = q.poll();
                int node = temp[0], currMask = temp[1];
                if (node == -1) {
                    if (q.isEmpty())
                        break;
                    res++;
                    q.offer(new int[] {-1, -1});
                    continue;
                }
                for (int neighbor : graph[node]) {
                    int nextMask = currMask | (1 << neighbor);
                    if (nextMask == endingMask)
                        return 1 + res;
                    if (!vis[neighbor][nextMask]) {
                        vis[neighbor][nextMask] = true;
                        q.offer(new int[] {neighbor, nextMask});
                    }
                }
            }
        }
        return 0;
    }
}
