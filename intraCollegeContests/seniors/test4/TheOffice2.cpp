/*
 * https://www.hackerrank.com/contests/practice-test-4-3/challenges/office-ii
 */



// This boils down to finding LCA of multiple nodes in a DAG
#include <bits/stdc++.h>
using namespace std;

int findLCA(vector<vector<int> > &graph, int src, unordered_set<int> &nodes) {
    if (nodes.find(src) != nodes.end())
        return src;
    int numChildren = graph[src].size();
    int leftChild = (numChildren) ? findLCA(graph, graph[src][0], nodes) : 0;
    int rightChild = (numChildren == 2) ? findLCA(graph, graph[src][1], nodes) : 0;
    if (leftChild && rightChild)
        return src;
    return (leftChild) ? leftChild : rightChild;
}

int main() {
    int n, m, i, u, v;
    vector<vector<int> > graph;
    unordered_set<int> nodes;
    cin >> n;
    graph.resize(n + 1);
    for (i = 1; i < n; i++) {
        cin >> u >> v;
        graph[u].push_back(v);
    }
    cin >> m;
    while (m--) {
        cin >> v;
        nodes.insert(v);
    }
    cout << findLCA(graph, 1, nodes);
}
