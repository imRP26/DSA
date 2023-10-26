/*
 * https://codeforces.com/problemset/problem/1249/E
 */
import java.io.*;
import java.util.*;


public class Main {

    /*
     * TRANSITIONS WERE ACTUALLY EASY TO THINK OF, NO NEED TO FUCK UP BY OVERCOMPLICATING AND OVERTHINKING!
     *
	 * DP State :-
	 * dp(i, 0) = Minimum time taken to reach floor 'i' if we aren't in the elevator currently.
	 * dp(i, 1) = Minimum time taken to reach floor 'i' if we're currently in the elevator.
	 * 
	 * DP Transition :-
	 * dp(i + 1, 0) = min{dp(i + 1, 0), dp(i, 0) + a[i], dp(i, 1) + a[i]}
	 * 				=> 2 cases arise -> (1) we weren't in the elevator and going to the next floor using stairs.
	 * 									(2) we were in the elevator and going to the next floor using stairs.
	 * 
	 * dp(i + 1, 1) = min{dp(i + 1, 1), dp(i, 1) + b[i], dp(i, 0) + b[i] + c}
	 *				=> 2 cases arise -> (1) we were in the elevator and going to the next floor using elevator.
	 *									(2) we weren't in the elevator and going to the next floor using elevator.
	 *
	 * Base Case :-
	 * dp(0, 0) = 0 and dp(0, 1) = c. All other states' values are assigned as INF.
	 * 
	 * Final Answer :-
	 * min{dp(i, 0), dp(i, 1)} for all floors i.
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]), c = Integer.parseInt(temp[1]);
		int[] stairs = new int[n - 1], elevator = new int[n - 1];
		int[][] dp = new int[n][2];
		temp = br.readLine().trim().split(" ");
		for (int i = 0; i < n - 1; i++)
		    stairs[i] = Integer.parseInt(temp[i]);
		temp = br.readLine().trim().split(" ");
		for (int i = 0; i < n - 1; i++)
			elevator[i] = Integer.parseInt(temp[i]);
		for (int[] row : dp)
			Arrays.fill(row, Integer.MAX_VALUE);
		dp[0][0] = 0;
		dp[0][1] = c;
		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.min(dp[i][0], stairs[i - 1] + Math.min(dp[i - 1][0], dp[i - 1][1]));
			dp[i][1] = Math.min(dp[i][1], elevator[i - 1] + Math.min(dp[i - 1][0] + c, dp[i - 1][1]));
		}
		for (int i = 0; i < n; i++)
			System.out.print(Math.min(dp[i][0], dp[i][1]) + " ");
	}
}
