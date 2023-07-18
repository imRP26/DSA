/*
 * https://cses.fi/problemset/task/1688/
 */
#include <bits/stdc++.h>
using namespace std;

int n;
vector<int> nodeLevel;
vector<vector<int> > graph, kthParent;

void dfs(int node, int parent, int level) {
    nodeLevel[node] = level;
    kthParent[0][node] = parent;
	for (int child : graph[node])
		dfs(child, node, level + 1);
}

int kthParentQuery(int node, int k) {
	for (int i = 0; i <= log2(n); i++) {
		if (k & (1 << i))
			node = kthParent[i][node];
		if (node == -1)
			break;
	}
	return node;
}

int lca(int x, int y) {
	if (nodeLevel[x] > nodeLevel[y])
		x = kthParentQuery(x, nodeLevel[x] - nodeLevel[y]);
	else if (nodeLevel[y] > nodeLevel[x])
		y = kthParentQuery(y, nodeLevel[y] - nodeLevel[x]);
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

int main() {
	int q, u, v;
	cin >> n >> q;
	graph.resize(n + 1);
	for (int i = 2; i <= n; i++) {
		cin >> u;
		graph[u].push_back(i);
	}
	nodeLevel.resize(n + 1, 0);
	kthParent.resize(log2(n) + 1);
	for (int i = 0; i <= log2(n); i++)
		kthParent[i].resize(n + 1, -1);
	dfs(1, -1, 0);
	for (int j = 1; j <= log2(n); j++) {
		for (int i = 1; i <= n; i++) {
			int intermediate = kthParent[j - 1][i];
			kthParent[j][i] = (intermediate == -1) ? -1 : kthParent[j - 1][intermediate];
		}
	}
	while (q--) {
		cin >> u >> v;
		cout << lca(u, v) << endl;
	}
}




/*
 * ALTERNATE METHOD :- using the in-out time trick
 */
/*
#include <bits/stdc++.h>
using namespace std;

int n, t = 0;
vector<int> inTime, outTime;
vector<vector<int> > graph, kthParent;

void dfs(int node, int parent) {
    kthParent[0][node] = parent;
	inTime[node] = t++;
	for (int child : graph[node])
		dfs(child, node);
	outTime[node] = t++;
}

bool isAncestor(int x, int y) {
	if (x == -1 || y == -1)
		return 1;
	return inTime[x] <= inTime[y] && outTime[x] >= outTime[y];
}

int lca(int x, int y) {
	if (isAncestor(x, y))
		return x;
	if (isAncestor(y, x))
		return y;
	for (int k = log2(n); k >= 0; k--) {
		int xPar = kthParent[k][x];
		if (!isAncestor(xPar, y))
			x = xPar;
	}
	return kthParent[0][x];
}

int main() {
	int q, u, v;
	cin >> n >> q;
	graph.resize(n + 1);
	for (int i = 2; i <= n; i++) {
		cin >> u;
		graph[u].push_back(i);
	}
	inTime.resize(n + 1, 0);
	outTime.resize(n + 1, 0);
	kthParent.resize(log2(n) + 1);
	for (int i = 0; i <= log2(n); i++)
		kthParent[i].resize(n + 1, -1);
	dfs(1, -1);
	for (int j = 1; j <= log2(n); j++) {
		for (int i = 1; i <= n; i++) {
			int intermediate = kthParent[j - 1][i];
			kthParent[j][i] = (intermediate == -1) ? -1 : kthParent[j - 1][intermediate];
		}
	}
	while (q--) {
		cin >> u >> v;
		cout << lca(u, v) << endl;
	}
}
 */
