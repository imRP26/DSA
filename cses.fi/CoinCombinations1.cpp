/*
 * https://cses.fi/problemset/task/1635
 */
#include <bits/stdc++.h>
using namespace std;
#define ll long long

vector<ll> coins, dp;
const ll mod = 1e9 + 7;

ll memoization(int a) {
	if (dp[a] != -1)
		return dp[a];
	ll ans = 0;
	for (int c : coins) {
		if (c > a)
			break;
		ans += memoization(a - c);
		ans %= mod;
	}
	return dp[a] = ans;
}

int main() {
	ll n, x, c;
	cin >> n >> x;
	while (n--) {
		cin >> c;
		coins.push_back(c);
	}
	dp.resize(x + 1, -1);
	dp[0] = 1;
	sort(coins.begin(), coins.end());
	cout << memoization(x) << endl;
}
