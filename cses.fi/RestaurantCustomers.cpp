/*
 * https://cses.fi/problemset/task/1619
 */
#include <bits/stdc++.h>
using namespace std;

int main() {
	int n, a, b, num = 0, res = 0;
	map<int, int> mp;
	cin >> n;
	while (n--) {
		cin >> a >> b;
		mp[a]++;
		mp[b + 1]--;
	}
	for (auto it : mp) {
		num += it.second;
		res = max(res, num);
	}
	cout << res << endl;
}
