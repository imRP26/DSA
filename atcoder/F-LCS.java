/*
 * https://atcoder.jp/contests/dp/tasks/dp_f
 */



/*
 * Commented wala portion is for memoization!
 */
import java.io.*;
import java.util.*;


public class Main {
    
    private static int[][] dp;
    private static String s1, s2;
    
    private static int memoize(int idx1, int idx2) {
        if (idx1 < 0 || idx2 < 0)
            return 0;
        if (dp[idx1][idx2] != -1)
            return dp[idx1][idx2];
        if (s1.charAt(idx1) == s2.charAt(idx2))
            return dp[idx1][idx2] = 1 + memoize(idx1 - 1, idx2 - 1);
        return dp[idx1][idx2] = Math.max(memoize(idx1 - 1, idx2), memoize(idx1, idx2 - 1));
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine().trim();
        s2 = br.readLine().trim();
        int len1 = s1.length(), len2 = s2.length(), idx1 = len1 - 1, idx2 = len2 - 1;
        /*
        dp = new int[len1][len2];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        memoize(idx1, idx2);
        */
        dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        StringBuilder sb = new StringBuilder();
        /*
        while (idx1 >= 0 && idx2 >= 0) {
            if (s1.charAt(idx1) == s2.charAt(idx2)) {
                sb.insert(0, s1.charAt(idx1));
                idx1--;
                idx2--;
            }
            else if (idx1 >= 1 && idx2 >= 1 && dp[idx1 - 1][idx2] >= dp[idx1][idx2 - 1])
                idx1--;
            else
                idx2--;
        }
        */
        idx1 = len1;
        idx2 = len2;
        while (idx1 > 0 && idx2 > 0) {
            if (s1.charAt(idx1 - 1) == s2.charAt(idx2 - 1)) {
                sb.insert(0, s1.charAt(idx1 - 1));
                idx1--;
                idx2--;
            }
            else if (idx1 >= 1 && idx2 >= 1 && dp[idx1 - 1][idx2] >= dp[idx1][idx2 - 1])
                idx1--;
            else
                idx2--;
        }
        System.out.println(sb.toString());
    }
}
