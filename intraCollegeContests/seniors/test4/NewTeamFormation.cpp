/*
 * https://www.hackerrank.com/contests/practice-test-4-3/challenges/new-team-formation
 */



// Simple HashMap based solution
#include <bits/stdc++.h>
using namespace std;

int main() {
    int n, k, a, sum = 0, result = 0;
    unordered_map<int, int> mp;
    cin >> n >> k;
    mp[sum]++;
    while (n--) {
        cin >> a;
        sum += a;
        result += mp[sum - k];
        mp[sum]++; 
    }
    cout << result;
}
