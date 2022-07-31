/*
 * https://leetcode.com/problems/climbing-stairs/
 */



// Simple DP
class Solution1 {
    public int climbStairs(int n) {
        int[] numberOfWays = new int[n + 1];
        numberOfWays[0] = 1;
        numberOfWays[1] = 1;
        for (int i = 2; i <= n; i ++)
            numberOfWays[i] = numberOfWays[i - 1] + numberOfWays[i - 2];
        return numberOfWays[n];
    }
}



// O(1) SC
class Solution2 {
    public int climbStairs(int n) {
        int a = 1, b = 1, c = 1, i;
        for (i = 2; i <= n; i ++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
