/*
 * https://cses.fi/problemset/task/1131
 */
// Unfortunately, the JAVA code gives TLE!
#include <bits/stdc++.h>
using namespace std;

vector<vector<int> > graph;

vector<int> bfs(int node) {
	queue<int> q;
	unordered_set<int> ust;
	int lastNode = 0, level = 0;
	q.push(node);
	q.push(-1);
	while (!q.empty()) {
		node = q.front();
		q.pop();
		if (node == -1) {
			if (!q.empty())
				q.push(-1);
			level++;
			continue;
		}
		if (ust.find(node) != ust.end())
			continue;
		ust.insert(node);
		lastNode = node;
		for (int neighbor : graph[node]) {
			if (ust.find(neighbor) == ust.end())
				q.push(neighbor);
		}
	}
	vector<int> v;
	v.push_back(lastNode);
	v.push_back(level - 1);
	return v;
}


int main() {
	int n, u, v;
	cin >> n;
	graph.resize(n + 1);
	for (int i = 1; i < n; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	vector<int> vec = bfs(1);
	vec = bfs(vec[0]);
	cout << vec[1] << endl;
}
