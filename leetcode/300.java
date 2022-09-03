import java.util.*;

/*
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */



/*
 * Recursion as well as memoization :-
 * 2 cases are possible for a particular index :- either take the element present 
 * at the current index and extend the LIS, or don't take the element at the 
 * current index.
 */
class Solution1 {
    
    int recurse(int currIndex, int prevIndex, int[] nums) {
        if (currIndex == nums.length)
            return 0;
        // not picking up the current element
        int len = recurse(currIndex + 1, prevIndex, nums);
        // picking up the current element
        if (prevIndex == -1 || nums[currIndex] > nums[prevIndex])
            len = Math.max(len, 1 + recurse(currIndex + 1, currIndex, nums));
        return len;
    }
    
    int memoize(int currIndex, int prevIndex, int[] nums, int[][] dp) {
        if (currIndex == nums.length)
            return 0;
        if (dp[currIndex][prevIndex + 1] != -1) // co-ordinate shifting
            return dp[currIndex][prevIndex + 1];
        int len = memoize(currIndex + 1, prevIndex, nums, dp);
        if (prevIndex == -1 || nums[currIndex] > nums[prevIndex])
            len = Math.max(len, 1 + memoize(currIndex + 1, currIndex, nums, dp));
        return dp[currIndex][prevIndex + 1] = len;
    }
    
    public int lengthOfLIS(int[] nums) {
        //return recurse(0, -1, nums);
        int n = nums.length;
        // for co-ordinate shifing, refer to Striver's video
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return memoize(0, -1, nums, dp);
    }
} 



/*
 * Method of Tabulation :-
 * Let dp[i] = longest increasing subsequence of nums[0 .. i] that has nums[i] 
 * as the end element of the subsequence.
 */
class Solution2 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length, result = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // each element is an LIS of length 1 by default
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}



// Printing the LIS
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length, result = 0, maxIndex = -1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // each element is an LIS of length 1 by default
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
            if (dp[i] > result) {
                result = dp[i];
                maxIndex = i;
            }
        }
        do {
            System.out.println(maxIndex + " -> " + nums[maxIndex]);
            maxIndex = parent[maxIndex];
        } while (parent[maxIndex] != maxIndex);
        System.out.println(maxIndex + " " + nums[maxIndex]);
        return result;
    }
}



/*
 * Greedy + Binary Search :- 
 * Refer to Striver's video. - already done in 354 - Russian Doll Envelopes
 */
class Solution3 {
    public int lengthOfLIS(int[] nums) {
        
    }
}
