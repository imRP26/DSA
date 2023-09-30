/*
 * https://codeforces.com/contest/161/problem/D
 */



// Refer the approach from Kartik Arora's YouTube solution video!
#include <bits/stdc++.h>
using namespace std;
#define ull unsigned long long

vector<vector<int> > graph;
vector<vector<int> > dp1, dp2;

void dfs1(int node, int parent, int k) {
	for (int child : graph[node]) {
		if (child != parent)
			dfs1(child, node, k);
	}
	dp1[node][0] = 1;
	for (int i = 1; i <= k; i++) {
		for (int child : graph[node]) {
			if (child != parent)
				dp1[node][i] += dp1[child][i - 1];
		}
	}
}

void dfs2(int node, int parent, int k) {
	for (int i = 0; i <= k; i++)
		dp2[node][i] = dp1[node][i];
	if (parent != 0) {
		dp2[node][1] += dp2[parent][0];
		for (int i = 2; i <= k; i++)
			dp2[node][i] += dp2[parent][i - 1] - dp1[node][i - 2];
	}
	for (int child : graph[node]) {
		if (child != parent)
			dfs2(child, node, k);
	}
}

int main() {
	int n, k, u, v;
	cin >> n >> k;
	graph.resize(n + 1);
	for (int i = 1; i < n; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	dp1.resize(n + 1, vector<int> (k + 1, 0));
	dp2.resize(n + 1, vector<int> (k + 1, 0));
	dfs1(1, 0, k);
	dfs2(1, 0, k);
	ull res = 0;
	for (int i = 1; i <= n; i++)
		res += dp2[i][k];
	cout << (res / 2) << endl;
}
