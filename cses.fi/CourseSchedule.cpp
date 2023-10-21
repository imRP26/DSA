/*
 * https://cses.fi/problemset/task/1679/
 */
#include <bits/stdc++.h>
using namespace std;

int main() {
	int nodes, edges, u, v;
	vector<vector<int> > graph;
	cin >> nodes >> edges;
	graph.resize(nodes + 1);
	vector<int> indegree(nodes + 1, 0);
	while (edges--) {
		cin >> u >> v;
		graph[u].push_back(v);
		indegree[v]++;
	}
	queue<int> q;
	for (int i = 1; i <= nodes; i++) {
		if (!indegree[i])
			q.push(i);
	}
	vector<int> res;
	vector<bool> vis(nodes + 1, 0);
	while (!q.empty()) {
		int node = q.front();
		q.pop();
		if (vis[node])
			continue;
		vis[node] = 1;
		res.push_back(node);
		for (int neighbor : graph[node]) {
			if (!(--indegree[neighbor]))
				q.push(neighbor);
		}
	}
	if (res.size() == nodes) {
		for (int node : res)
			cout << node << ' ';
	}
	else
		cout << "IMPOSSIBLE";
}
 