/*
 * https://thejoboverflow.com/problem/32/
 */
// EASY AF!
#include <bits/stdc++.h>
using namespace std;

int main() {
	int nodes, u, v, delNode;
	cin >> nodes;
	vector<int> degree(nodes + 1, 0);
	nodes--;
	while (nodes--) {
		cin >> u >> v;
		degree[u]++;
		degree[v]++;
	}
	cin >> v;
	cout << degree[v];
}
