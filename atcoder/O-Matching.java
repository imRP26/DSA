/*
 * https://atcoder.jp/contests/dp/tasks/dp_o
 */
import java.io.*;
import java.util.*;


public class Main {
	
	/*
	 * DP State :-
	 * dp(mask) = number of matchings for females in the set S to the 1st |S| males.
	 * 
	 * DP Transition :-
	 * dp(mask) = sigma {dp(mask \ x) : isCompatible[|mask|][x]} => The number of matchings in a 
	 *			  subset S to include a certain female x is equivalent to the sum of all the 
	 * 			  matchings without female x, where female x is compatible with the |S|-th male.
	 * 
	 * Base Case :-
	 * dp(0) = 1, i.e., a single matching involving 0 pairs.
	 * 
	 * Final Answer :-
	 * dp(completeMask)
     */
	
	private static int mod = (int)1e9 + 7;
	private static int[] dp;
	private static boolean[][] isCompatible;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		isCompatible = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String[] temp = br.readLine().trim().split(" ");
			for (int j = 0; j < n; j++)
				isCompatible[i][j] = Integer.parseInt(temp[j]) == 1;
		}
		dp = new int[1 << n];
		dp[0] = 1; // there exists only 1 way to match no-one!
		for (int mask = 0; mask < (1 << n); mask++) {
			int man = Integer.bitCount(mask);
			for (int woman = 0; woman < n; woman++) {
				// the woman mustn't have been paired already and also compatible with the (man)-th man
				if ((mask & (1 << woman)) != 0 || !isCompatible[man][woman])
					continue;
				int newMask = mask | (1 << woman);
				dp[newMask] = (dp[newMask] + dp[mask]) % mod;
			}
		}
		System.out.println(dp[(1 << n) - 1]);
	}
}
