/*
 * https://cses.fi/problemset/task/2220/
 */
#include <bits/stdc++.h>
using namespace std;
#define ll long long

ll dp[20][11][2][2];

ll memoize(string& num, int numDigits, int prevDigit, bool leadingZero, bool tight) {
	if (!numDigits)
		return 1;
	if (dp[numDigits][prevDigit][leadingZero][tight] != -1)
		return dp[numDigits][prevDigit][leadingZero][tight];
	int ub = tight ? num[num.length() - numDigits] - '0' : 9;
	ll ans = 0;
	for (int i = 0; i <= ub; i++) {
		if (i == prevDigit && !leadingZero)
			continue;
		ans += memoize(num, numDigits - 1, i, (leadingZero & i == 0), (tight & i == ub));
	}
	return dp[numDigits][prevDigit][leadingZero][tight] = ans;
}

int main() {
	ll a, b;
	cin >> a >> b;
	ll a1 = a ? a - 1 : a;
	string s1 = to_string(a1), s2 = to_string(b);
	memset(dp, -1, sizeof(dp));
	ll val1 = memoize(s1, s1.length(), 10, 1, 1);
	memset(dp, -1, sizeof(dp));
	ll val2 = memoize(s2, s2.length(), 10, 1, 1);
	if (!a)
		cout << val2 << endl;
	else
		cout << val2 - val1 << endl;
}
