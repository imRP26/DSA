/*
 * https://cses.fi/problemset/task/1690/
 */
#include <bits/stdc++.h>
using namespace std;

vector<vector<int> > graph, dp;
const int mod = 1e9 + 7;

int memoize(int mask, int node) {
	if (!node)
		return mask == 1 ? 1 : 0;
	if (!(mask & (1 << node)))
		return 0;
	if (dp[mask][node] != -1)
		return dp[mask][node];
	int ans = 0, newMask = mask ^ (1 << node);
	for (int neighbor : graph[node])
		ans = (ans + memoize(newMask, neighbor)) % mod;
	return dp[mask][node] = ans;
}

int main() {
	int nodes, edges, u, v;
	cin >> nodes >> edges;
	graph.resize(nodes);
	while (edges--) {
		cin >> u >> v;
		graph[v - 1].push_back(u - 1);
	}
	dp.resize(1 << nodes, vector<int> (nodes, -1));
	cout << memoize((1 << nodes) - 1, nodes - 1);
}
