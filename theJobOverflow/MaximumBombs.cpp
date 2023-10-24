/*
 * https://thejoboverflow.com/problem/306/
 */
#include <bits/stdc++.h>
using namespace std;
#define ll long long

/*
 * Idea is to model this problem as a directed graph problem.
 * 2 bombs can be said to be connected to each other if the distance 
 * between their centres is <= the sum of their individual radii.
 * (x1 - x2)^2 + (y1 - y2)^2 <= (r1 + r2)^2
 * Then for each node, a BFS can be performed to compute the maximum 
 * number of nodes covered in the traversal. 
 */

vector<vector<int> > graph, bombs;

int bfs(int node, int n) {
    queue<int> q;
    int ans = 0;
    q.push(node);
    vector<bool> vis(n, 0);
    while (!q.empty()) {
        node = q.front();
        q.pop();
        if (vis[node])
            continue;
        vis[node] = 1;
        ans++;
        for (int neighbor : graph[node]) {
            if (!vis[neighbor])
                q.push(neighbor);
        }
    }
    return ans;
}

int main() {
    int n, res = 0;
    cin >> n;
    graph.resize(n);
    bombs.resize(n, vector<int> (3));
    for (int i = 0; i < n; i++)
        cin >> bombs[i][0] >> bombs[i][1] >> bombs[i][2];
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            ll dx = bombs[i][0] - bombs[j][0], dy = bombs[i][1] - bombs[j][1], r1 = bombs[i][2], r2 = bombs[j][2];
            if (dx * dx + dy * dy <= r1 * r1)
                graph[i].push_back(j);
            if (dx * dx + dy * dy <= r2 * r2)
                graph[j].push_back(i);
        }
    }
    for (int i = 0; i < n; i++)
        res = max(res, bfs(i, n));    
    cout << res;
}
