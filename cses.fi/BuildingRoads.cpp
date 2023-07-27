/*
 * https://cses.fi/problemset/task/1666/
 */
#include <bits/stdc++.h>
using namespace std;

vector<vector<int> > graph;
vector<bool> vis;

void dfs(int node) {
    if (vis[node])
        return;
    vis[node] = 1;
    for (int neighbor : graph[node]) {
        if (!vis[neighbor])
            dfs(neighbor);
    }
}

int main() {
    int n, m, u, v;
    vector<int> vec;
    cin >> n >> m;
    graph.resize(n + 1);
    vis.resize(n + 1, 0);
    while (m--) {
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    for (int i = 1; i <= n; i++) {
        if (vis[i])
            continue;
        vec.push_back(i);
        dfs(i);
    }
    cout << (vec.size() - 1) << endl;
    for (int i = 1; i < vec.size(); i++)
        cout << vec[i - 1] << ' ' << vec[i] << endl;
}
