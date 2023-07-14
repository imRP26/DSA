/*
 * https://cses.fi/problemset/task/1687/
 */
#include <bits/stdc++.h>
using namespace std;

int nodes;
vector<vector<int> > graph;

int query(int x, int k, vector<vector<int> > &kthParent) {
	for (int i = 0; i <= log2(nodes); i++) {
		if (k & (1 << i)) {
			x = kthParent[i][x]; // 2^(i'th) parent of x
			if (x == -1)
				break;
		}
	}
	return x;
}

void dfs(int node, int parent, vector<vector<int> > &kthParent) {
	kthParent[0][node] = parent;
	for (int child : graph[node])
		dfs(child, node, kthParent);
}

int main() {
	int q, par, x, k;
	cin >> nodes >> q;
	vector<vector<int> > kthParent(log2(nodes) + 1, vector<int> (nodes + 1, -1));
	graph.resize(nodes + 1);
	for (int i = 2; i <= nodes; i++) {
		cin >> par;
		graph[par].push_back(i);
	}
	dfs(1, -1, kthParent);
	for (k = 1; k <= log2(nodes); k++) {
		for (int i = 1; i <= nodes; i++) {
			int intermediate = kthParent[k - 1][i];
			if (intermediate != -1)
				kthParent[k][i] = kthParent[k - 1][intermediate];
		}
	}
    while (q--) {
		cin >> x >> k;
		cout << query(x, k, kthParent) << endl;
	}
}
