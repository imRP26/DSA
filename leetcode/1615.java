/*
 * https://leetcode.com/problems/maximal-network-rank/
 */



/*
 * Simple Ad-Hoc on Graphs!
 */
class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] degree = new int[n];
        boolean[][] isConnected = new boolean[n][n];
        for (int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
            isConnected[road[0]][road[1]] = isConnected[road[1]][road[0]] = true;
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j])
                    res = Math.max(res, degree[i] + degree[j] - 1);
                else
                    res = Math.max(res, degree[i] + degree[j]);
            }
        }
        return res;
    }
}
