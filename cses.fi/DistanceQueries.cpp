/*
 * https://cses.fi/problemset/task/1135/
 */
#include <bits/stdc++.h>
using namespace std;


vector<vector<int> > graph, kthParent;
vector<int> nodeLevel;
int nodes;

void dfs(int node, int parent, int level) {
	kthParent[0][node] = parent;
	nodeLevel[node] = level;
	for (int child : graph[node]) {
		if (child != parent)
			dfs(child, node, 1 + level);
	}
}

int kthParentQuery(int node, int k) {
	for (int i = 0; i <= log2(nodes) + 1; i++) {
		if ((k & (1 << i)) != 0)
			node = kthParent[i][node];
		if (node == -1)
			break;
	}
	return node;
}

int lca(int x, int y) {
	if (nodeLevel[x] > nodeLevel[y])
		x = kthParentQuery(x, nodeLevel[x] - nodeLevel[y]);
	else if (nodeLevel[x] < nodeLevel[y])
		y = kthParentQuery(y, nodeLevel[y] - nodeLevel[x]);
	if (x == y)
		return x;
	for (int k = log2(nodes) + 1; k >= 0; k--) {
		int kthxPar = kthParent[k][x], kthyPar = kthParent[k][y];
		if (kthxPar != kthyPar) {
			x = kthxPar;
			y = kthyPar;
		}
	}
	return kthParent[0][x];
}

int main() {
	int q, u, v;
	cin >> nodes >> q;
	graph.resize(nodes + 1);
	nodeLevel.resize(nodes + 1);
	kthParent.resize(log2(nodes) + 2, vector<int> (nodes + 1, -1));
	for (int i = 1; i < nodes; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	dfs(1, -1, 0);
	for (int k = 1; k <= log2(nodes) + 1; k++) {
		for (int i = 1; i <= nodes; i++) {
			int intermediate = kthParent[k - 1][i];
			if (intermediate != -1)
				kthParent[k][i] = kthParent[k - 1][intermediate];
			else
				kthParent[k][i] = -1;
		}
	}
	while (q--) {
		cin >> u >> v;
		int uvlca = lca(u, v), res = nodeLevel[u] + nodeLevel[v] - 2 * nodeLevel[uvlca];
		cout << res << endl;
	} 
}
