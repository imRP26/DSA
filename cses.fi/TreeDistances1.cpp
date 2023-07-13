/*
 * https://cses.fi/problemset/task/1132
 */
#include <bits/stdc++.h>
using namespace std;

int n;
vector<vector<int> > graph;

int bfs1(int node) {
	queue<int> q;
	int ans = 0;
	q.push(node);
	unordered_set<int> ust;
	while (!q.empty()) {
		node = q.front();
		q.pop();
		if (ust.find(node) != ust.end())
			continue;
		ust.insert(node);
		ans = node;
		for (int neighbor : graph[node]) {
			if (ust.find(neighbor) == ust.end())
				q.push(neighbor);
		}
	}
	return ans;
}

vector<int> bfs2(int node) {
	vector<int> v(n + 1, 0);
	queue<pair<int, int> > q;
	unordered_set<int> ust;
	q.push({node, 0});
	while (!q.empty()) {
		pair<int, int> p = q.front();
		q.pop();
		node = p.first;
		if (ust.find(node) != ust.end())
			continue;
		ust.insert(node);
		int d = p.second;
		v[node] = d;
		for (int neighbor : graph[node]) {
			if (ust.find(neighbor) == ust.end())
				q.push({neighbor, d + 1});
		}
	}
	return v;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int u, v;
	cin >> n;
	graph.resize(n + 1);
	for (int i = 1; i < n; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	int dep1 = bfs1(1), dep2 = bfs1(dep1);
	vector<int> v1 = bfs2(dep1), v2 = bfs2(dep2);
	for (int i = 1; i <= n; i++)
		cout << max(v1[i], v2[i]) << ' ';
}
