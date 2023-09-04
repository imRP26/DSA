/*
 * https://leetcode.com/problems/minimum-time-to-build-blocks/
 */



/*
 * Approach of Top-Down DP and Bottom-Up from LC Official Editorial!
 */
class Solution {

    private int n;
    private int[][] dp;

    private int memoization(int[] blocks, int split, int i, int j) {
        if (i == n) // all blocks have been built
            return 0;
        if (j == 0) // no worker left to build the remaining blocks
            return Integer.MAX_VALUE;
        if (j >= n - i) // current number of workers is sufficient to build the remaining blocks
            return blocks[i];
        if (dp[i][j] != -1)
            return dp[i][j];
        int build1 = Math.max(blocks[i], memoization(blocks, split, i + 1, j - 1)); // both things can be done in parallel, hence the use of max()
        int split1 = split + memoization(blocks, split, i, Math.min(2 * j, n - i));
        return dp[i][j] = Math.min(build1, split1);
    }

    public int minBuildTime(int[] blocks, int split) {
        n = blocks.length;
        dp = new int[n][n + 1];
        /*
         * DP State :-
         * dp[i][j] = minimum time taken to build blocks from i to (n - 1) using j workers.
         * 
         * DP Transitions :-
         * dp[i][j] = min(buildHere, splitHere)
         * buildHere = max(blocks[b], dp[i + 1][j - 1])
         * splitHere = split + dp[i][min(2 * j, n - i)]
         *   
         * Final Answer :-
         * dp[0][1]
         */
        Arrays.sort(blocks);
        for (int i = 0; i < n / 2; i++) {
            int temp = blocks[i];
            blocks[i] = blocks[n - i - 1];
            blocks[n - i - 1] = temp;
        }
        for (int[] row : dp)
            Arrays.fill(row, -1);
        //return memoization(blocks, split, 0, 1);
        int[][] dp2 = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) // base case of 0 workers
            dp2[i][0] = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) // base case of all blocks having been built
            dp2[n][i] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n; j >= 1; j--) {
                if (j >= n - i) {
                    dp2[i][j] = blocks[i];
                    continue;
                }
                int buildHere = Math.max(blocks[i], dp2[i + 1][j - 1]);
                int splitHere = split + dp2[i][Math.min(2 * j, n - i)];
                dp2[i][j] = Math.min(buildHere, splitHere);
            }
        }
        return dp2[0][1];
    }
}



/*
 * Approach of Space-Optimized DP from LC Official Editorial! 
 */
class Solution {
    public int minBuildTime(int[] blocks, int split) {
        int n = blocks.length;
        Arrays.sort(blocks);
        for (int i = 0; i < n / 2; i++) {
            int temp = blocks[i];
            blocks[i] = blocks[n - i - 1];
            blocks[n - i - 1] = temp;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 0);
        dp[0] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n; j >= 1; j--) {
                if (j >= n - i) {
                    dp[j] = blocks[i];
                    continue;
                }
                int buildHere = Math.max(blocks[i], dp[j - 1]);
                int splitHere = split + dp[Math.min(2 * j, n - i)];
                dp[j] = Math.min(buildHere, splitHere);
            }
        }
        return dp[1]; // initially, we've only 1 worker for building all the blocks
    }
}



/*
 * Approach similar to merging using Huffman Encoding from LC official editorial! 
 */
class Solution {
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        for (int b : blocks)
            minPQ.offer(b);
        int res = 0;
        while (minPQ.size() > 1) {
             int x1 = minPQ.poll(), x2 = minPQ.poll();
             minPQ.offer(split + Math.max(x1, x2));
        }
        return minPQ.poll();
    }
}



/*
 * Approach of Binary Search from LC Official Editorial!
 */
class Solution {

    private boolean isPossible(int[] blocks, int split, int limit) {
        int w = 1, n = blocks.length;
        for (int i = 0; i < n; i++) {
            int time = blocks[i];
            if (w <= 0 || time > limit)
                return false;
            while (time + split <= limit) {
                limit -= split;
                w *= 2;
                if (w >= n - i)
                    return true;
            }
            w--;
        }
        return true;
    }

    public int minBuildTime(int[] blocks, int split) {
        int n = blocks.length, res = 0;
        Arrays.sort(blocks);
        for (int i = 0; i < n / 2; i++) {
            int temp = blocks[i];
            blocks[i] = blocks[n - i - 1];
            blocks[n - i - 1] = temp;
        }
        int low = blocks[0], high = low + split * (int)Math.ceil(Math.log(n) / Math.log(2));
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(blocks, split, mid)) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res;
    }
}
