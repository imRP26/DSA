import java.util.*;

/* 
 * https://leetcode.com/problems/largest-divisible-subset/
*/



/*
 * My solution - I'm proud of this 1...
 * Just used the concept of LIS here.
 */
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, maxLen = 0, maxIndex = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] prevIndex = new int[n];
        Arrays.fill(prevIndex, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[i] < 1 + dp[j]) {
                        dp[i] = 1 + dp[j];
                        prevIndex[i] = j;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (maxLen < dp[i]) {
                maxLen = dp[i];
                maxIndex = i;
            }    
        }
        List<Integer> result = new ArrayList<>();
        while (maxIndex != -1) {
            result.add(nums[maxIndex]);
            maxIndex = prevIndex[maxIndex];
        }
        Collections.reverse(result);
        return result;
    }
}
