/*
 * https://leetcode.com/problems/find-the-town-judge/
 */



/*
 * Modelling it as a graph problem, wrt indegree and outdegree of vertices
 */
class Solution1 {
    public int findJudge(int n, int[][] trust) {
        int[] indegree = new int[n + 1], outdegree = new int[n + 1];
        for (int[] t : trust) {
            int a = t[0], b = t[1];
            indegree[b]++;
            outdegree[a]++;
        }
        int result = -1;
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == n - 1 && outdegree[i] == 0) {
                if (result == -1)
                    result = i;
                else
                    return -1;
            }
        }
        return result;
    }
}



/*
 * Slight modification to the above approach, the sum of indegree and outdegree 
 * for any vertex qualifying to be regarded as Town Judge equals (n - 1). 
 */
class Solution2 {
    public int findJudge(int n, int[][] trust) {
        int[] degree = new int[n + 1];
        for (int[] t : trust) {
            int a = t[0], b = t[1];
            degree[b]++;
            degree[a]--;
        }
        int result = -1;
        for (int i = 1; i <= n; i++) {
            if (degree[i] == n - 1) {
                if (result != -1)
                    return -1;
                result = i;
            } 
        }
        return result;
    }
}
