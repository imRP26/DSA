/*
 * https://atcoder.jp/contests/dp/tasks/dp_b
 */
import java.io.*;
import java.util.*;


public class Main {

    /*
     * DP State :-
     * dp(i) = Minimum cost incurred in reaching stone 'i' from stone '0'
     * 
     * DP Transitions :-
     * dp(i) = min over all 1 <= j <= k {|h(i) - h(i - j)| + dp(i - j)}
     * 
     * Final Answer :-
     * dp(n - 1)
     */

    private static int[] dp, heights;

    private static int memoize(int i, int k) {
        if (i == 0)
            return 0;
        if (dp[i] != -1)
            return dp[i];
        int ans = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (i >= j)
                ans = Math.min(ans, memoize(i - j, k) + Math.abs(heights[i] - heights[i - j]));
        }
        return dp[i] = ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int n = Integer.parseInt(temp[0]), k = Integer.parseInt(temp[1]);
        heights = new int[n];
        temp = br.readLine().trim().split(" ");
        for (int i = 0; i < n; i++)
            heights[i] = Integer.parseInt(temp[i]);
        dp = new int[n];
        Arrays.fill(dp, -1);
        System.out.println(memoize(n - 1, k));
    }
}
