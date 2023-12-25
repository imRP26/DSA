/*
 * https://cses.fi/problemset/task/1639
 */
#include <bits/stdc++.h>
using namespace std;

string str1, str2;
vector<vector<int> > dp;

int memoize(int idx1, int idx2) {
	if (idx1 < 0)
		return 1 + idx2;
	if (idx2 < 0)
		return 1 + idx1;
	if (dp[idx1][idx2] != -1)
		return dp[idx1][idx2];
	if (str1[idx1] == str2[idx2])
		return dp[idx1][idx2] = memoize(idx1 - 1, idx2 - 1);
	int insertionCost = memoize(idx1, idx2 - 1), deletionCost = memoize(idx1 - 1, idx2), replacementCost = memoize(idx1 - 1, idx2 - 1);
	return dp[idx1][idx2] = 1 + min(replacementCost, min(insertionCost, deletionCost));
}

int main() {
	cin >> str1;
	cin >> str2;
	int len1 = str1.length(), len2 = str2.length();
	dp.resize(len1, vector<int> (len2, -1));
	memoize(len1 - 1, len2 - 1);
	cout << dp[len1 - 1][len2 - 1];
}
