import java.io.*;
import java.util.*;


/*
 * Explanations from -> 
 * https://codinghangover.wordpress.com/2015/03/09/codeforces-260div-1-a-boredom/
 * https://bruceoutdoors.wordpress.com/2015/11/08/445a-boredom-codeforces-tutorial/
 */

public class Main {

    private static int maxVal = (int)1e5;
    private static long[] count, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long res = 0;
        count = new long[1 + maxVal];
        String[] temp = br.readLine().trim().split(" ");
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(temp[i]);
            count[num]++;
        }
        dp = new long[1 + maxVal];
        dp[0] = 0;
        dp[1] = count[1];
        for (int i = 2; i <= maxVal; i++) {
            dp[i] = Math.max(dp[i - 2] + count[i] * i, dp[i - 1]);
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
    }
}
