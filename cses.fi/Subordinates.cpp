/*
 * https://cses.fi/problemset/task/1674
 */
#include <bits/stdc++.h>
using namespace std;

vector<vector<int> > graph;
vector<int> dp;

int dfs(int node) {
	int count = 0;
	for (int i = 0; i < graph[node].size(); i++)
		count += 1 + dfs(graph[node][i]);
	return dp[node] = count;
}

int main() {
	int nodes, par, node = 2;
	cin >> nodes;
	graph.resize(nodes + 1);
	for (int i = 0; i < nodes - 1; i++) {
		cin >> par;
		graph[par].push_back(node++);
	}
	for (int i = 0; i <= nodes + 1; i++)
		dp.push_back(-1);
	dfs(1);
	for (int i = 1; i <= nodes; i++)
		cout << dp[i] << ' ';
}



/*
 * Aliter method :-
#include <bits/stdc++.h>
using namespace std;

vector<int> dp;
vector<vector<int> > graph;

int dfs(int node) {
    int ans = 0;
    for (int child : graph[node])
        ans += dfs(child);
    ans += graph[node].size();
    return dp[node] = ans;
}

int main() {
    int n, u;
    cin >> n;
    graph.resize(n + 1);
    for (int i = 2; i <= n; i++) {
        cin >> u;
        graph[u].push_back(i);
    }
    dp.resize(n + 1, 0);
    dfs(1);
    for (int i = 1; i <= n; i++)
        cout << dp[i] << ' ';
    cout << endl;
}
 */
