/*
 * https://cses.fi/problemset/task/1140
 */
import java.io.*;
import java.util.*;


public class Projects {
    
    private static int[] endDays;
    private static long[] dp;
    private static int[][] projects;

    private static int lowerBound(int startTime) {
        int low = 0, high = endDays.length - 1, res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (projects[mid][1] < startTime) {
                res = mid;
                low = mid + 1;
            }
            else 
                high = mid - 1;
        }
        return res;
    }

    private static long memoization(int i) {
        if (i < 0)
            return 0;
        if (dp[i] != -1)
            return dp[i];
        long ans1 = memoization(i - 1), ans2 = projects[i][2];
        int lb = lowerBound(projects[i][0]);
        lb -= (lb >= 0 && projects[lb][1] == projects[i][0]) ? 1 : 0;
        if (lb >= 0)
            ans2 += memoization(lb);
        return dp[i] = Math.max(ans1, ans2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        projects = new int[n][3];
        for (int i = 1; i <= n; i++) {
            String[] temp = br.readLine().trim().split(" ");
            projects[i - 1] = new int[] {Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])};
        }
        Arrays.sort(projects, (a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return a[1]- b[1];
        });
        endDays = new int[n];
        for (int i = 0; i < n; i++)
            endDays[i] = projects[i][1];
        dp = new long[n];
        Arrays.fill(dp, -1);
        dp[0] = projects[0][2];
        System.out.println(memoization(n - 1));
    }  
}
