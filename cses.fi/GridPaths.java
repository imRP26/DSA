/*
 * https://cses.fi/problemset/task/1638
 */
import java.io.*;
import java.util.*;


class GridPaths {

	private static char[][] grid;
	private static int[][] dp;
	private static int mod = (int)1e9 + 7;

	private static int memoization(int x, int y) {
		if (dp[x][y] != -1)
			return dp[x][y];
		int ans = 0;
		if (x >= 1)
			ans = (ans + memoization(x - 1, y)) % mod;
		if (y >= 1)
			ans = (ans + memoization(x, y - 1)) % mod;
		return dp[x][y] = ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		grid = new char[n][n];
		dp = new int[n][n];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		for (int i = 0; i < n; i++) {
			String temp = br.readLine().trim();
			grid[i] = temp.toCharArray();
			for (int j = 0; j < n; j++)
				dp[i][j] = temp.charAt(j) == '*' ? 0 : dp[i][j];	
		}
		dp[0][0] = grid[0][0] == '*' ? 0 : 1;
		System.out.println(memoization(n - 1, n - 1));
	}
}
