/*
 * https://cses.fi/problemset/task/1130
 */
#include <bits/stdc++.h>
using namespace std;

vector<vector<int> > graph, dp;

int memoization(int node, int parent, int flag) {
	if (dp[node][flag] != -1)
		return dp[node][flag];
	int ans = 0;
	for (int child : graph[node]) {
		if (child == parent)
			continue;
		if (flag == 0)
			ans += max(memoization(child, node, 0), memoization(child, node, 1));
		else
			ans = max(ans, 1 + memoization(node, parent, 0) + memoization(child, node, 0) - 
						   max(memoization(child, node, 0), memoization(child, node, 1)));
	}
	return dp[node][flag] = ans;
}

int main() {
	int n, u, v;
	cin >> n;
	graph.resize(n + 1);
	dp.resize(n + 1, vector<int> (2, -1));
	for (int i = 1; i < n; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	cout << max(memoization(1, -1, 0), memoization(1, -1, 1)) << endl;
}
