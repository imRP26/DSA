/*
 * https://atcoder.jp/contests/dp/tasks/dp_a
 */
import java.io.*;
import java.util.*;


public class Main {

    /*
     * DP State :-
     * dp(i) = Minimum cost incurred in reaching stone 'i' from stone '0'
     * 
     * DP Transitions :-
     * dp(i) = min{|h(i) - h(i - 1)| + dp(i - 1), |h(i) - h(i - 2)| + dp(i - 2)}
     * 
     * Base Case :-
     * dp(0) = 0, destination reached!!
     * 
     * Final Answer :-
     * dp(n - 1)
     */

    private static int[] dp, heights;

    private static int memoize(int i) {
        if (i == 0)
            return 0;
        if (dp[i] != -1)
            return dp[i];
        int ans = Integer.MAX_VALUE;
        if (i >= 1)
            ans = Math.min(ans, Math.abs(heights[i] - heights[i - 1]) + memoize(i - 1));
        if (i >= 2)
            ans = Math.min(ans, Math.abs(heights[i] - heights[i - 2]) + memoize(i - 2));
        return dp[i] = ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        heights = new int[n];
        String[] temp = br.readLine().trim().split(" ");
        for (int i = 0; i < n; i++)
            heights[i] = Integer.parseInt(temp[i]);
        dp = new int[n];
        Arrays.fill(dp, -1);
        System.out.println(memoize(n - 1));
    }
}
