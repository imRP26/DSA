#include <bits/stdc++.h>
using namespace std;

/*
 * https://www.hackerrank.com/contests/practice-test-5-3/challenges/travel-in-minimum-time
 */



// Backtracking DFS
int result = INT_MAX;
int graph[50][50];
bool visited[50][50];

void dfs(int n, int x, int y, int time) {
    if (visited[x][y])
        return;
    if (x == n - 1 && y == n - 1) {
        result = min(result, time);
        return;
    }
    visited[x][y] = 1;
    if (x >= 1)
        dfs(n, x - 1, y, max(time, graph[x - 1][y]));
    if (x + 1 < n)
        dfs(n, x + 1, y, max(time, graph[x + 1][y]));
    if (y >= 1)
        dfs(n, x, y - 1, max(time, graph[x][y - 1]));
    if (y + 1 < n)
        dfs(n, x, y + 1, max(time, graph[x][y + 1]));
    visited[x][y] = 0;
}

int main() {
    int n, i, j;
    cin >> n;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++)
            cin >> graph[i][j];
    }
    memset(visited, 0, sizeof(visited));
    dfs(n, 0, 0, graph[0][0]);
    cout << result;
}
