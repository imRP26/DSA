/*
 * https://cses.fi/problemset/task/1668/
 */
#include <bits/stdc++.h>
using namespace std;

int main() {
	int nodes, edges, u, v;
	vector<vector<int> > graph;
	cin >> nodes >> edges;
	graph.resize(nodes + 1);
	while (edges--) {
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	vector<int> colour(nodes + 1);
	bool isBipartite = true;
	for (int node = 1; node <= nodes; node++) {
		if (colour[node]) {
			continue;
		}
		colour[node] = 1;
		queue<int> q;
		q.push(node);
		while (!q.empty()) {
			int vertex = q.front();
			q.pop();
			for (int neighbor: graph[vertex]) {
				if (colour[neighbor] == colour[vertex]) {
					isBipartite = false;
					break;
				}
				if (!colour[neighbor]) {
					colour[neighbor] = 3 - colour[vertex];
					q.push(neighbor);
				}
			}
			if (!isBipartite) {
				break;
			}
		}
		if (!isBipartite) {
			break;
		}
	}
	if (!isBipartite) {
		cout << "IMPOSSIBLE";
	} else {
		for (int i = 1; i <= nodes; i++) {
			cout << colour[i] << ' ';
		}
	}
}
