#include <bits/stdc++.h>
using namespace std;

/*
 * https://www.hackerrank.com/contests/practice-test-5-3/challenges/just-another-day-in-obh
 */



// Clever Ad-Hoc, let this question be a lesson in never ever letting down your guard
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t, i, n, a, minval, minpos;
    cin >> t;
    while (t--) {
        cin >> n;
        minval = INT_MAX;
        minpos = -1;
        for (i = 0; i < n; i++) {
            cin >> a;
            if (a < minval) {
                minval = a;
                minpos = i;
            }
        }
        if (n & 1) {
            cout << "YASH\n";
            continue;
        }
        if (minpos & 1)
            cout << "YASH\n";
        else
            cout << "NIKHIL\n";
    }
}
