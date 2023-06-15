#include <bits/stdc++.h>
using namespace std;

vector<vector<int> > graph;
vector<int> dp;

int dfs(int node) {
	int count = 0;
	for (int i = 0; i < graph[node].size(); i++)
		count += 1 + dfs(graph[node][i]);
	return dp[node] = count;
}

int main() {
	int nodes, par, node = 2;
	cin >> nodes;
	graph.resize(nodes + 1);
	for (int i = 0; i < nodes - 1; i++) {
		cin >> par;
		graph[par].push_back(node++);
	}
	for (int i = 0; i <= nodes + 1; i++)
		dp.push_back(-1);
	dfs(1);
	for (int i = 1; i <= nodes; i++)
		cout << dp[i] << ' ';
}