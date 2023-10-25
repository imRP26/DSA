/*
 * https://thejoboverflow.com/problem/112/
 */
// Simple Application of Binary Search - edge case being res = 0 when all elements = 0
#include <bits/stdc++.h>
using namespace std;
#define ll long long

bool isPossible(vector<ll>& v, ll x, ll m) {
	ll ans = 0;
	for (int i = 0; i < v.size(); i++) {
		ans += v[i] / x;
		ans += (v[i] % x) ? 1 : 0;
		if (ans > m)
			return 0;
	}
	return ans <= m;
}

int main() {
	int t, n;
	ll m;
	cin >> t;
	while (t--) {
		cin >> n >> m;
		vector<ll> v(n);
		ll sum = 0;
		for (int i = 0; i < n; i++) {
			cin >> v[i];
			sum += v[i];
		}
		if (!sum) {
			cout << 0 << endl;
			continue;
		}
		ll low = 1, high = 1e18, res = -1;
		while (low <= high) {
			ll mid = low + (high - low) / 2;
			if (isPossible(v, mid, m)) {
				res = mid;
				high = mid - 1;
			}
			else
				low = mid + 1;
		}
		cout << res << endl;
	}
}
