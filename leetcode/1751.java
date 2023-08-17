/*
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/
 */



/*
 * Approach 1 (Top-Down DP + Binary-Search) from LC Official Editorial!
 */
class Solution {

    private int n;
    private int[][] dp;

    private int upperBound(int val, int[][] events) {
        int low = 0, high = n - 1, res = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > val) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res;
    }

    /*
	 * Case 1 :- dfs(currIndex) = the maximum value obtained by attending events optimally in 
     * the range events[currIndex ~ (n - 1)].
	 * Case 2 :- dfs(currIndex) = max(dfs(currIndex + 1), dfs(nextIndex) + events[currIndex][2])
	 * dfs(currIndex, count) -> this will be the state function in use for this problem.
     * dfs(currIndex, count) = max value obtained by attending a maximum of 'count' events in the 
     * range events[currIndex ~ (n - 1)].
	 */
    private int memoization(int count, int i, int[][] events) {
        if (count == 0 || i == n)
            return 0;
        if (dp[count][i] != -1)
            return dp[count][i];
        int ans1 = memoization(count, i + 1, events);
        int j = upperBound(events[i][1], events);
        int ans2 = events[i][2] + memoization(count - 1, j, events);
        return dp[count][i] = Math.max(ans1, ans2);
    }

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        n = events.length;
        dp = new int[k + 1][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return memoization(k, 0, events);
    }
}



/*
 * Approach 2 (Bottom-Up DP + Binary Search) from LC Official Editorial!
 */
class Solution {

    private int n;

    private int upperBound(int target, int[][] events) {
        int low = 0, high = n - 1, res = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > target) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res;
    }

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        n = events.length;
        int[][] dp = new int[k + 1][n + 1];
        for (int k1 = 1; k1 <= k; k1++) {
            for (int i = n - 1; i >= 0; i--) {
                int j = upperBound(events[i][1], events);
                dp[k1][i] = Math.max(dp[k1][i + 1], events[i][2] + dp[k1 - 1][j]);
            }
        }
        return dp[k][0];
    }
}



/*
 * Approach of Top-Down DP + Cached Binary Search from LC Official Editorial!
 */
class Solution {

    private int n;
    private int[][] dp;
    private Map<Integer, Integer> map = new HashMap<>();

    private int upperBound(int val, int[][] events) {
        int low = 0, high = n - 1, res = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > val) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res;
    }

    private int memoization(int count, int i, int[][] events) {
        if (count == 0 || i == n)
            return 0;
        if (dp[count][i] != -1)
            return dp[count][i];
        int val1 = memoization(count, i + 1, events), val2 = memoization(count - 1, map.get(i), events) + events[i][2];
        return dp[count][i] = Math.max(val1, val2);
    }

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        n = events.length;
        dp = new int[k + 1][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        for (int i = 0; i < n; i++)
            map.put(i, upperBound(events[i][1], events));
        return memoization(k, 0, events);
    }
}



/*
 * Approach of Bottom-Up DP + Cached Binary Search from LC Official Editorial!
 */
class Solution {

    private int n;

    private int upperBound(int target, int[][] events) {
        int low = 0, high = n - 1, res = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > target) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res;
    }

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        n = events.length;
        int[][] dp = new int[k + 1][n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.put(i, upperBound(events[i][1], events));
        for (int k1 = 1; k1 <= k; k1++) {
            for (int i = n - 1; i >= 0; i--) {
                int j = map.get(i);
                dp[k1][i] = Math.max(dp[k1][i + 1], events[i][2] + dp[k1 - 1][j]);
            }
        }
        return dp[k][0];
    }
}



/*
 * Approach of only Top-Down DP (no BinarySearch) from LC Official Editorial
 */

