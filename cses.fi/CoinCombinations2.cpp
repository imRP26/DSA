/*
 * https://cses.fi/problemset/task/1636/
 */
#include <bits/stdc++.h>
using namespace std;

/*
 * This question had me fucked up for almost the entire day!
 * The memoization procedure gives TLE!
 * In the bottom-up procedure, if the outer loop is regarding 'target', then it throws 
 * up TLE and gets AC the other way around!
 */

/*
int memoization(int a, int i) {
	if (i >= coins.size() || a < 0)
		return 0;
	if (a == 0)
		return 1;
	if (dp[a][i] != 0)
		return dp[a][i];
	int ans1 = memoization(a, i + 1), ans2 = memoization(a - coins[i], i);
	return dp[a][i] = (ans1 + ans2) % mod;
}
*/

int main() {
	int n, c, x, mod = 1e9 + 7;
	vector<int> coins;
	cin >> n >> x;
	for (int i = 1; i <= n; i++) {
		cin >> c;
		coins.push_back(c);
	}
	//dp.resize(x + 1, vector<int> (n + 1, 0));
	//cout << memoization(x, 0) << endl;
	vector<vector<int> > dp(n + 1, vector<int> (x + 1, 0));
	dp[0][0] = 1;
	/*
	for (int i = 0; i <= x; i++) {
		for (int j = 1; j <= n; j++) {
			dp[i][j] = dp[i][j - 1];
			int k = i - coins[j - 1];
			if (k >= 0)
				(dp[i][j] += dp[k][j]) %= mod;
		}
	}
	*/
	for (int i = 1; i <= n; i++) {
		for (int j = 0; j <= x; j++) {
			dp[i][j] = dp[i - 1][j];
			int k = j - coins[i - 1];
			if (k >= 0)
				(dp[i][j] += dp[i][k]) %= mod;
		}
	}
	//cout << dp[x][n] << endl;
	cout << dp[n][x] << endl;
}
