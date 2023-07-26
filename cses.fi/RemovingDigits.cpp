/*
 * https://cses.fi/problemset/task/1637
 */
#include <bits/stdc++.h>
using namespace std;

vector<int> dp;

int memoization(int n) {
	if (n == 0)
		return 0;
	if (dp[n] != -1)
		return dp[n];
	int ans = n + 1, num = n;
	while (num > 0) {
		int d = num % 10;
		if (d != 0)
			ans = min(ans, 1 + memoization(n - d));
		num /= 10;
	}
	return dp[n] = ans;
}

int main() {
	int n;
	cin >> n;
	dp.resize(n + 1, -1);
	cout << memoization(n) << endl;
}
