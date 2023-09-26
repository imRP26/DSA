/*
 * https://cses.fi/problemset/task/1138/
 */
#include <bits/stdc++.h>
using namespace std;
#define ull unsigned long long

int timer = 1;
vector<int> nodeVal, inTime, outTime, eulerTour;
vector<ull> segtree;
vector<vector<int> > graph;

void dfs(int node, int parent) {
    inTime[node] = timer++;
    eulerTour.push_back(nodeVal[node]);
    for (int child : graph[node]) {
        if (child != parent)
            dfs(child, node);
    }
    outTime[node] = timer++;
    eulerTour.push_back(-nodeVal[node]);
}

void build(int nodeIdx, int low, int high) {
    if (low == high)
        segtree[nodeIdx] = eulerTour[low];
    else {
        int mid = low + (high - low) / 2;
        build(2 * nodeIdx, low, mid);
        build(2 * nodeIdx + 1, mid + 1, high);
        segtree[nodeIdx] = segtree[2 * nodeIdx] + segtree[2 * nodeIdx + 1];
    }
}

void update(int nodeIdx, int low, int high, int pos, int newVal) {
    if (low == high)
        segtree[nodeIdx] = newVal;
    else {
        int mid = low + (high - low) / 2;
        if (pos <= mid)
            update(2 * nodeIdx, low, mid, pos, newVal);
        else 
            update(2 * nodeIdx + 1, mid + 1, high, pos, newVal);
        segtree[nodeIdx] = segtree[2 * nodeIdx] + segtree[2 * nodeIdx + 1];
    }
}

ull query(int nodeIdx, int low, int high, int left, int right) {
    if (left > right)
        return 0;
    if (left == low && right == high)
        return segtree[nodeIdx];
    int mid = low + (high - low) / 2;
    ull leftSum = query(2 * nodeIdx, low, mid, left, min(mid, right));
    ull rightSum = query(2 * nodeIdx + 1, mid + 1, high, max(left, mid + 1), right);
    return leftSum + rightSum;
}

int main() {
    int n, q, u, v, newVal, qtype;
    cin >> n >> q;
    nodeVal.resize(n + 1);
    inTime.resize(n + 1);
    outTime.resize(n + 1);
    segtree.resize(8 * n + 2);
    eulerTour.push_back(0);
    graph.resize(n + 1);
    for (int i = 1; i <= n; i++)
        cin >> nodeVal[i];
    for (int i = 1; i < n; i++) {
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    dfs(1, 0);
    build(1, 1, 2 * n);
    while (q--) {
        cin >> qtype >> v;
        if (qtype == 1) {
            cin >> newVal;
            update(1, 1, 2 * n, inTime[v], newVal);
            update(1, 1, 2 * n, outTime[v], -newVal);
        }
        else {
            ull res = query(1, 1, 2 * n, 1, inTime[v]);
            cout << res << endl;
        }
    }
}
