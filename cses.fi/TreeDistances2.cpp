/*
 * https://cses.fi/problemset/task/1133
 */
#include <bits/stdc++.h>
using namespace std;
#define ll long long

/*
 * FULL Explanation :-
 * 
 *                                           (1)
 *                                     /    \        \
 *                                    /      \        \
 *                                  (c1) ... (ci) ... (cn)  
 * 
 * P.S. : For a more in-depth explanation, refer to Kartik Arora's video! 
 * 
 * res[i] = sum of distances from node i to all the other nodes in the tree.
 * subtreeAnswer[i] = sum of distances from node i to all other nodes in its subtree, 
 *                    assuming that the subtree is rooted at node no. 'i'.
 *                  = summation over (subtreeAnswer[ci] + numNodesInSubtree[ci]) over all i.
 * numNodesInSubtree[i] = number of nodes in the subtree rooted at node no. 'i'.
 * partialAnswer(1 | ci) = sum of the distances of all the nodes rom node no. 1 other 
 *                         than the nodes that belong to the subtree rooted at ci.
 *                       = res[1] - subtreeAnswer[ci] - numNodesInSubtree[ci]
 * res[ci] = subtreeAnswer[ci] + partialAnswer[1 | ci] + (total no. of nodes in the tree - numNodesInSubtree[ci])
 *         = subtreeAnswer[ci] + res[1] - subtreeAnswer[ci] - numNodesInSubtree[ci] + total no. of nodes - numNodesInSubtree[ci]
 * => res[ci] = res[parent] + nodes - 2 * numNodesInSubtree[ci]
 */

int nodes;
vector<ll> result, subtreeAnswer, numNodesInSubtree;
vector<vector<int> > graph;

// Utility function to compute 'numNodesInSubtree'
ll dfs1(int node, int parent) {
    ll ans = 1;
    for (int child : graph[node]) {
        if (child != parent)
            ans += dfs1(child, node);    
    }
    return numNodesInSubtree[node] = ans;
}

// Utility function to compute 'subtreeAnswer'
ll dfs2(int node, int parent) {
    for (int child : graph[node]) {
        if (child != parent)
            subtreeAnswer[node] += dfs2(child, node);    
    }
    return subtreeAnswer[node] + numNodesInSubtree[node];
}

// Utility function to compute 'partialAnswer' per node and then the final 'result'
void dfs3(int node, int parent) {
    result[node] = result[parent] + nodes - 2 * numNodesInSubtree[node];
    for (int child : graph[node]) {
        if (child != parent)
            dfs3(child, node);    
    }
}

int main() {
    int u, v;
    cin >> nodes;
    graph.resize(nodes + 1);
    for (int i = 1; i < nodes; i++) {
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    numNodesInSubtree.resize(nodes + 1);
    dfs1(1, 0); // to compute numNodesInSubtree
    subtreeAnswer.resize(nodes + 1, 0);
    dfs2(1, 0); // to compute subtreeAnswer
    result.resize(nodes + 1);
    result[1] = subtreeAnswer[1];
    for (int child : graph[1])
        dfs3(child, 1); // to compute result
    for (int i = 1; i <= nodes; i++)
        cout << result[i] << ' ';
}
