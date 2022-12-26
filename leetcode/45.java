/*
 * https://leetcode.com/problems/jump-game-ii/
 */



/*
 * Simple DP
 */
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int jumpLength = nums[i];
            for (int j = 1; j <= jumpLength; j++) {
                if (i + j < n)
                    dp[i + j] = Math.min(dp[i + j], 1 + dp[i]);
            }
        }
        return dp[n - 1];
    }
}



/*
 * Greedy, BFS
 */
class Solution2 {
    public int jump(int[] nums) {
        int n = nums.length, minJumps = 0;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        queue.offer(-1);
        visited.add(0);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (index == -1) {
                if (!queue.isEmpty())
                    queue.offer(-1);
                minJumps++;
                continue;
            }
            if (index == n - 1)
                break;
            for (int i = 1; i <= nums[index]; i++) {
                int j = index + i;
                if (j >= n)
                    break;
                if (!visited.contains(j)) {
                    queue.offer(j);
                    visited.add(j);
                }
            }
        }
        return minJumps;
    }
}



/*
 * Another sort of Greedy, BFS inspired from 
 * https://leetcode.com/problems/jump-game-ii/solutions/18028/o-n-bfs-solution/?orderBy=most_votes
 */
class Solution3 {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return 0;
        int currMax = 0, level = 0, i = 0;
        while (i <= currMax) {
            int furthest = currMax;
            while (i <= currMax) {
                furthest = Math.max(furthest, i + nums[i]);
                if (furthest >= n - 1)
                    return 1 + level;
                i++;
            }
            level++;
            currMax = furthest;
        }
        return -1;
    }
}



/*
 * Greedy based Solution, referenced from 
 * https://leetcode.com/problems/jump-game-ii/solutions/18014/concise-o-n-one-loop-java-solution-based-on-greedy/
 */
class Solution4 {
    public int jump(int[] nums) {
        int jumps = 0, currentEnd = 0, currentFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            currentFarthest = Math.max(currentFarthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = currentFarthest;
            }
        }
        return jumps;
    }
}
