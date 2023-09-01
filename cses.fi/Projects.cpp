/*
 * https://cses.fi/problemset/task/1140
 */
#include <bits/stdc++.h>
using namespace std;
#define ll long long

vector<int> endTimes;
vector<vector<int> > projects;
vector<ll> dp;

bool comp(vector<int>& v1, vector<int>& v2) {
	if (v1[1] == v2[1])
		return v1[0] < v2[0];
	return v1[1] < v2[1];
}

int lowerBound(int startTime) {
	int low = 0, high = endTimes.size() - 1, res = -1;
	while (low <= high) {
		int mid = low + (high - low) / 2;
		if (projects[mid][1] < startTime) {
			res = mid;
			low = mid + 1;
		}
		else
			high = mid - 1;
	}
	return res;
}

ll memoization(int i) {
	if (i < 0)
		return 0;
	if (dp[i] != -1)
		return dp[i];
	ll ans1 = memoization(i - 1), ans2 = projects[i][2];
	int lb = lowerBound(projects[i][0]);
	lb -= (lb >= 0 && projects[lb][1] == projects[i][0]) ? 1 : 0;
	ans2 += (lb >= 0) ? memoization(lb) : 0;
	return dp[i] = max(ans1, ans2);
}

int main() {
    int n, start, end, reward;
    cin >> n;
    vector<int> v;
    for (int i = 0; i < n; i++) {
        cin >> start >> end >> reward;
        v.clear();
        v.push_back(start);
        v.push_back(end);
        v.push_back(reward);
        projects.push_back(v);
    }
    sort(projects.begin(), projects.end(), comp);
    for (int i = 0; i < n; i++)
    	endTimes.push_back(projects[i][1]);
    dp.resize(n, -1);
    dp[0] = projects[0][2];
    cout << memoization(n - 1) << endl;
}
