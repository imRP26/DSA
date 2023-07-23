/* 
 * https://cses.fi/problemset/task/1136
 */
#include <bits/stdc++.h>
using namespace std;

int n;
vector<int> level, res;
vector<vector<int> > graph, kthParent;

void dfs(int node, int par, int l) {
	level[node] = l;
	kthParent[0][node] = par;
	for (int child : graph[node]) {
		if (child != par)
			dfs(child, node, l + 1);
	}
}

int kthParentQuery(int node, int k) {
	for (int i = 0; i <= log2(n); i++) {
		if (k & (1 << i))
			node = kthParent[i][node];
		if (node == 0)
			break;
	}
	return node;
}

int lca(int x, int y) {
	if (level[x] > level[y])
		x = kthParentQuery(x, level[x] - level[y]);
	else if (level[y] > level[x])
		y = kthParentQuery(y, level[y] - level[x]);
	if (x == y)
		return x;
	for (int k = log2(n); k >= 0; k--) {
		int kthxPar = kthParent[k][x], kthyPar = kthParent[k][y];
		if (kthxPar != kthyPar) {
			x = kthxPar;
			y = kthyPar;
		}
	}
	return kthParent[0][x];
}

int postorder(int node) {
	int ans = res[node], par = kthParent[0][node];
	for (int child : graph[node]) {
		if (child != par)
			ans += postorder(child);
	}
	return res[node] = ans;
}

int main() {
	int m, u, v, x;
	cin >> n >> m;
	graph.resize(n + 1);
	res.resize(n + 1, 0);
	level.resize(n + 1);
	kthParent.resize(log2(n) + 1, vector<int> (n + 1, 0));
	graph[0].push_back(1);
	graph[1].push_back(0);
	for (int i = 1; i < n; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	dfs(0, 0, 0);
	for (int j = 1; j <= log2(n); j++) {
		for (int i = 1; i <= n; i++) {
			int intermediate = kthParent[j - 1][i];
			kthParent[j][i] = (intermediate == 0) ? 0 : kthParent[j - 1][intermediate];
		}
	}
	while (m--) {
		cin >> u >> v;
		if (level[u] > level[v])
			swap(u, v);
		x = lca(u, v);
		if (x == u) {
			res[v]++;
			res[kthParent[0][u]]--;
		}
		else {
			res[u]++;
			res[kthParent[0][x]]--;
			res[v]++;
			res[x]--;
		}
	}
	postorder(1);
	for (int i = 1; i <= n; i++)
		cout << res[i] << ' ';
	cout << endl;
}
