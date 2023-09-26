/*
 * https://cses.fi/problemset/task/1137/
 */
#include <bits/stdc++.h>
using namespace std;
#define ull unsigned long long

int timer = 1;
vector<ull> segtree;
vector<int> eulerTour, inTime, outTime, nodeVal;
vector<vector<int> > graph;

void dfs(int node, int parent) {
    inTime[node] = timer++;
    eulerTour.push_back(nodeVal[node]);
    for (int child : graph[node]) {
        if (child != parent)
            dfs(child, node);
    }
    outTime[node] = timer++;
    eulerTour.push_back(nodeVal[node]);
}

void build(int nodeIdx, int low, int high) {
    if (low == high)
        segtree[nodeIdx] = eulerTour[low];
    else {
        int mid = low + (high - low) / 2, leftChild = 2 * nodeIdx, rightChild = 2 * nodeIdx + 1;
        build(leftChild, low, mid);
        build(rightChild, mid + 1, high);
        segtree[nodeIdx] = segtree[leftChild] + segtree[rightChild];
    }
}

void update(int nodeIdx, int low, int high, int pos, ull newVal) {
    if (low == high)
        segtree[nodeIdx] = newVal;
    else {
        int mid = low + (high - low) / 2, leftChild = 2 * nodeIdx, rightChild = 2 * nodeIdx + 1;
        if (pos <= mid)
            update(leftChild, low, mid, pos, newVal);
        else 
            update(rightChild, mid + 1, high, pos, newVal);
        segtree[nodeIdx] = segtree[leftChild] + segtree[rightChild];
    }
}

ull sumQuery(int nodeIdx, int low, int high, int left, int right) {
    if (left > right)
        return 0;
    if (left == low && right == high)
        return segtree[nodeIdx];
    int mid = low + (high - low) / 2;
    ull leftSum = sumQuery(2 * nodeIdx, low, mid, left, min(right, mid));
    ull rightSum = sumQuery(2 * nodeIdx + 1, mid + 1, high, max(left, mid + 1), right);
    return leftSum + rightSum;
}

int main() {
    int nodes, queries, u, v, type;
    ull newVal;
    cin >> nodes >> queries;
    graph.resize(nodes + 1);
    nodeVal.resize(nodes + 1);
    inTime.resize(nodes + 1);
    outTime.resize(nodes + 1);
    segtree.resize(8 * nodes + 2);
    for (int i = 1; i <= nodes; i++)
        cin >> nodeVal[i];
    for (int i = 1; i < nodes; i++) {
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    eulerTour.push_back(0);
    dfs(1, 0);
    build(1, 1, 2 * nodes);
    while (queries--) {
        cin >> type >> v;
        if (type == 1) {
            cin >> newVal;
            update(1, 1, 2 * nodes, inTime[v], newVal);
            update(1, 1, 2 * nodes, outTime[v], newVal);
        }
        else {
            ull res = sumQuery(1, 1, 2 * nodes, inTime[v], outTime[v]) / 2;
            cout << res << endl;
        }
    }
}
