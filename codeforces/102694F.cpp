/*
 * https://codeforces.com/gym/102694/problem/B 
 */
#include <bits/stdc++.h>
using namespace std;

vector<vector<int> > graph;

vector<int> bfs1(int src, int nodes) {
	queue<int> q;
	q.push(src);
	q.push(0);
	int level = 0, farthestNode = 0;
	vector<bool> vis(1 + nodes, 0);
	vector<int> ans;
	while (!q.empty()) {
		int node = q.front();
		q.pop();
		if (node == 0) {
			if (q.empty())
				break;
			level++;
			q.push(0);
			continue;
		}
		if (vis[node])
			continue;
		vis[node] = 1;
		farthestNode = node;
		for (int neighbor : graph[node]) {
			if (!vis[neighbor])
				q.push(neighbor);
		}
	}
	ans.push_back(farthestNode);
	ans.push_back(level);
	return ans;
}

vector<int> bfs2(int src, int nodes) {
	vector<int> res(nodes + 1);
	vector<bool> vis(1 + nodes, 0);
	queue<pair<int, int> > q;
	q.push({src, 0});
	while (!q.empty()) {
		auto p = q.front();
		q.pop();
		int node = p.first, dist = p.second;
		if (vis[node])
			continue;
		vis[node] = 1;
		res[node] = dist;
		for (int neighbor : graph[node]) {
			if (!vis[neighbor])
				q.push({neighbor, 1 + dist});
		}
	}
	return res;
}

int main() {
	int nodes, u, v;
	cin >> nodes;
	graph.resize(nodes + 1);
	for (int i = 1; i < nodes; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	vector<int> v1 = bfs1(1, nodes), v2 = bfs1(v1[0], nodes);
	int diameter = v2[1], dep1 = v1[0], dep2 = v2[0];
	vector<int> dist1 = bfs2(dep1, nodes), dist2 = bfs2(dep2, nodes);
	for (int i = 1; i <= nodes; i++) {
		int d = max(dist1[i], dist2[i]);
		if (d == diameter)
			cout << (1 + d) << '\n';
		else
			cout << diameter << '\n';
	}
}
