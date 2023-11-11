/*
 * https://cses.fi/problemset/task/1680
 */
#include <bits/stdc++.h>
using namespace std;

vector<bool> vis;
vector<int> dp, parent;
vector<vector<int> > graph;

void dfs(int node) {
	vis[node] = 1;
	for (int neighbor : graph[node]) {
		if (!vis[neighbor])
			dfs(neighbor);
        // The 2nd condition in the below if condition helps in pruning out unnecessary paths, which
        // don't lead to the final node
		if (dp[node] < 1 + dp[neighbor] && dp[neighbor] != INT_MIN) {
			dp[node] = 1 + dp[neighbor];
			parent[node] = neighbor;
		}
	}
}

int main() {
	int nodes, edges, u, v;
	cin >> nodes >> edges;
	graph.resize(nodes + 1);
	while (edges--) {
		cin >> u >> v;
		graph[u].push_back(v);
	}
	vis.resize(nodes + 1, 0);
	dp.resize(nodes + 1, INT_MIN);
	dp[nodes] = 1;
	parent.resize(nodes + 1);
	dfs(1);
	if (dp[1] == INT_MIN)
		cout << "IMPOSSIBLE";
	else {
		int node = 1;
		cout << dp[1] << endl;
		while (node) {
			cout << node << ' ';
			node = parent[node];
		}
	}
} 
