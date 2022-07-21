/*
 * https://www.hackerrank.com/contests/practice-test-5-3/challenges/unique-balls
 */



// Simulation
#include <bits/stdc++.h>
using namespace std;

bool comp(pair<int, int> &a, pair<int, int> &b) {
   return a.second > b.second; 
}

int main() {
    int i, a, n, result = 0;
    map<int, int> mp;
    cin >> n;
    for (i = 1; i <= n; i++) {
        cin >> a;
        mp[a]++;
    }
    vector<pair<int, int> > v;
    for (auto &it : mp)
        v.push_back(it);
    sort(v.begin(), v.end(), comp);
    a = 0;
    for (auto &it : v) {
        a += it.second;
        result++;
        if (a >= n / 2)
            break;
    }
    cout << result;
}
