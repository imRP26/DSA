/*
 * https://www.hackerrank.com/contests/practice-test-4-3/challenges/the-office-i/problem
 */

#include <bits/stdc++.h>
using namespace std;

/*
 * Clearly, this is a DSU based problem. However, we shouldn't do path 
 * compression here.
 * If we do path compression, then we miss the step of updation of componentSize 
 * of intermediate parents.
 */
int main() {
    int n, a, b, i;
    cin >> n;
    int parent[n + 1], componentSize[n + 1];
    for (i = 1; i <=  n; i++)
        parent[i] = i;
    memset(componentSize, 0, sizeof(componentSize));
    for (i = 1; i < n; i++) {
        cin >> a >> b;
        parent[b] = a;
    }
    for (i = 1; i <= n; i++) {
        if (parent[i] == i)
            continue;
        a = i;
        while (a != parent[a]) {
            componentSize[parent[a]]++;
            a = parent[a];
        }
    }
    for (i = 1; i <= n; i++)
        cout << componentSize[i] << ' ';
}
