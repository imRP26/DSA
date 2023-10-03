/*
 * https://atcoder.jp/contests/dp/tasks/dp_j
 */
import java.io.*;
import java.util.*;


// Solution Referred from -> https://www.youtube.com/watch?v=XRVCU2Ys9Mk
public class Main {

    /*
     * DP State :-
     * dp[x][y][z] = expected number of times of die roll in order to 
     *               complete eating x dishes having 3 pieces of sushi each, 
     *               y dishes having 2 pieces of sushi each, z dishes having 
     *               a single piece of sushi each.
     * x + y + z = N (always) => (N - x - y - z) dishes don't have any sushi. 
     *
     * DP Transitions :-
     * Each transition consists of a die roll that can "uniformly" generate any 
     * number from 1 to N.
     * Now since any number from 1 to N can be generated uniformly, so the sushi dishes can be 
     * shuffled and hence the final answer won't depend upon the ordering of the sushi dishes.
     * Basically we need to be concerned about the overall state of the sushi dishes, since 
     * till there's sushi left, die rolls will continue being made!
     * dp[x][y][z] = 1 + p3 * dp[x - 1][y + 1][z] + p2 * dp[x][y - 1][z + 1] + p1 * dp[x][y][z - 1] + 
                     p0 * dp[x][y][z]
     * => dp[x][y][z] = {1 + p3 * dp[x - 1][y + 1][z] + p2 * dp[x][y - 1][z + 1] + p1 * dp[x][y][z - 1]} / (1 - p0)
     * Here, p3, p2, p1 and p0 are the probabilities of picking up dishes containing 3, 2, 1, or 0 pieces 
     * of sushi respectively.
     * 
     * Base Case :-
     * dp[0][0][0] = 0, since no further die rolls need to be made now.
     * 
     * Final Answer :-
     * dp[num3][num2][num1], where num3, num2 and num1 are the initial counts of dishes having 3, 2 and 1
     * pieces of sushi respectively.
     */

    private static double[][][] dp;

    private static double memoize(int i, int j, int k, int n) {
        if (i < 0 || j < 0 || k < 0 || (i + j + k > n))
            return 0;
        if (i == 0 && j == 0 && k == 0)
            return 0;
        if (dp[i][j][k] != -1)
            return dp[i][j][k];
        double p3 = i * 1.0 / n, p2 = j * 1.0 / n, p1 = k * 1.0 / n, p0 = (n - i - j - k) * 1.0 / n;
        double ans = 1.0;
        ans += p3 * memoize(i - 1, j + 1, k, n);
        ans += p2 * memoize(i, j - 1, k + 1, n);
        ans += p1 * memoize(i, j, k - 1, n);
        ans /= (1.0 - p0);
        return dp[i][j][k] = ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim()), num1 = 0, num2 = 0, num3 = 0;
        String[] temp = br.readLine().trim().split(" ");
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(temp[i]);
            num1 += (num == 1) ? 1 : 0;
            num2 += (num == 2) ? 1 : 0;
            num3 += (num == 3) ? 1 : 0;
        }
        dp = new double[n + 1][n + 1][n + 1];
        for (double[][] row1 : dp) {
            for (double[] row2 : row1)
                Arrays.fill(row2, -1);
        }
        System.out.println(memoize(num3, num2, num1, n));
    }
}
