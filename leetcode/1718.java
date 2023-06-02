/*
 * https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
 */



/*
 * Approach of Recursion from -> 
 * https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/solutions/1008864/java-c-detailed-explanation-backtracking-100-faster/
 */
class Solution {

    private int[] result;
    private boolean[] visited;

    private boolean backtrack(int idx, int n) {
        if (idx == result.length)
            return true;
        if (result[idx] != 0)
            return backtrack(idx + 1, n);
        for (int i = n; i >= 1; i--) {
            if (visited[i])
                continue;
            result[idx] = i;
            visited[i] = true;
            if (i == 1) {
                if (backtrack(idx + 1, n))
                    return true;
            }
            else if (idx + i < result.length && result[idx + i] == 0) {
                result[idx + i] = i;
                if (backtrack(idx + 1, n))
                    return true;
                result[idx + i] = 0;
            }
            result[idx] = 0;
            visited[i] = false;
        }
        return false;
    }

    public int[] constructDistancedSequence(int n) {
        result = new int[2 * n - 1];
        visited = new boolean[n];
        backtrack(0, n);
        return result;
    }
}
