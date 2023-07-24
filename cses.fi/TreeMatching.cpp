/*
 * https://cses.fi/problemset/task/1130
 */
#include <bits/stdc++.h>
using namespace std;

/*
 * THE BIG EXPLANATION :- (wrt the example tree given below)
 *                                                      (1)
 * 													 /	 \    \
 * 													/	 \     \
 *                                                (2)   (3)   (4)
 * 												      /  \  \
 * 													 /	 \   \
 *  												(5) (6) (7)
 * 
 * TFO :- The maximum number of edges where each node is an endpoint of atmaax 1 edge.
 * 
 * DP States :-
 * dp(i, 0) = answer for the subtree rooted at node 'i' such that there's no edge between the node 'i' and 
 * 			  any of its children!
 * dp(i, 1) = answer for the subtree rooted at node 'i' such that there's an edge between the node 'i' and 
 * 			  only 1 of its children!
 * The Final answer = max(dp[1][0], dp[1][1]) - assuming that the given tree is being rooted at node '1'.
 * 
 * Base Case :-
 * dp[leaf][0] = dp[leaf][1] = 0.
 * 
 * DP Transitions when we're currently at node '1' :-
 * dp[1][0] = max(dp[2][0], dp[2][1]) + max(dp[3][0], dp[3][1]) + max(dp[4][0], dp[4][1])
 * => max(dp[3][0]) + max(dp[4][0]) = dp[1][0] - max(dp[2][0], dp[2][1])
 * => dp[node][0] = summation over all children {max(dp[child][0], dp[child][1])}
 * If we consider the edge (1)----(2) in the final answer, then 
 * dp[1][1] = 1 + dp[2][0] + max(dp[3][0], dp[3][1]) + max(dp[4][0], dp[4][1])
 * => dp[1][1] = 1 + dp[1][0] + dp[2][0] - max(dp[2][0], dp[2][1])
 * => dp[node][1] = 1 + dp[node][0] + dp[child][0] - max(dp[child][0], dp[child][1]) 
 */

vector<vector<int> > graph, dp;

int memoization(int node, int parent, int flag) {
	if (dp[node][flag] != -1)
		return dp[node][flag];
	int ans = 0;
	for (int child : graph[node]) {
		if (child == parent)
			continue;
		if (flag == 0)
			ans += max(memoization(child, node, 0), memoization(child, node, 1));
		else
			ans = max(ans, 1 + memoization(node, parent, 0) + memoization(child, node, 0) - 
						   max(memoization(child, node, 0), memoization(child, node, 1)));
	}
	return dp[node][flag] = ans;
}

int main() {
	int n, u, v;
	cin >> n;
	graph.resize(n + 1);
	dp.resize(n + 1, vector<int> (2, -1));
	for (int i = 1; i < n; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	cout << max(memoization(1, -1, 0), memoization(1, -1, 1)) << endl;
}
