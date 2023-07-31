/*
 * https://cses.fi/problemset/task/1683/
 */



// Base Question for SCCs using Kosaraju's algo (refer to Striver's video for explanation)
#include <bits/stdc++.h>
using namespace std;

vector<vector<int> > graph, revGraph;
vector<bool> vis;
vector<int> res;
stack<int> st;

void dfs1(int node) {
	vis[node] = 1;
	for (int neighbor : graph[node]) {
		if (!vis[neighbor])
			dfs1(neighbor);
	}
	st.push(node);
}

void dfs2(int node, int root) {
	vis[node] = 1;
	res[node] = root;
	for (int neighbor : revGraph[node]) {
		if (!vis[neighbor])
			dfs2(neighbor, root);
	}
}

int main() {
	int n, m, u, v;
	cin >> n >> m;
	graph.resize(n + 1);
	revGraph.resize(n + 1);
	vis.resize(n + 1, 0);
	res.resize(n + 1);
	while(m--) {
		cin >> u >> v;
		graph[u].push_back(v);
		revGraph[v].push_back(u);
	}
	for (int i = 1; i <= n; i++) {
		if (vis[i])
			continue;
		dfs1(i);
	}
	for (int i = 1; i <= n; i++)
	    vis[i] = 0;
	m = 0;
	while (!st.empty()) {
		v = st.top();
		st.pop();
		if (vis[v])
			continue;
		m += 1;
		dfs2(v, m);
	}
	cout << m << endl;
	for (int i = 1; i <= n; i++)
		cout << res[i] << ' ';
}
