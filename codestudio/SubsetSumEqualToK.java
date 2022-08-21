import java.util.*;

/*
 * https://www.codingninjas.com/codestudio/problems/subset-sum-equal-to-k_1550954?leftPanelTab=0
 */



// Refer to Striver's DP-15 video
// Memoization Approach
class Solution1 {
    
    static int memoization(int[] arr, int[][] dp, int index, int sum) {
        if (sum == 0)
            return dp[index][sum] = 1;
        if (index == 0) {
            if (arr[0] == sum)
                return dp[index][sum] = 1;
            return dp[index][sum] = 0;
        }
        if (dp[index][sum] != -1)
            return dp[index][sum];
        int choice1 = memoization(arr, dp, index - 1, sum);
        int choice2 = 0;
        if (sum >= arr[index])
            choice2 = memoization(arr, dp, index - 1, sum - arr[index]);
        if (choice1 == 1 || choice2 == 1)
            return dp[index][sum] = 1;
        return dp[index][sum] = 0;
    }
    
    public static boolean subsetSumToK(int n, int k, int arr[]){
        int[][] dp = new int[n][k + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return memoization(arr, dp, n - 1, k) == 1;
    }
}



// Tabulation Approach
class Solution2 {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        boolean[][] dp = new boolean[n][k + 1];
        for (int i = 0; i < n; i++)
            dp[i][0] = true;
        if (arr[0] <= k)
            dp[0][arr[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                boolean choice1 = dp[i - 1][j], choice2 = false;
                if (j >= arr[i])
                    choice2 = dp[i - 1][j - arr[i]];
                dp[i][j] = choice1 | choice2;
            }
        }
        return dp[n - 1][k];
    }
}
