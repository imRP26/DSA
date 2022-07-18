/*
 * https://www.hackerrank.com/contests/practice-test-4-3/challenges/goal-and-miss
 */



// Greedy Based Solution - take the example as secret = "1101" and guess = "1111"
#include <bits/stdc++.h>
using namespace std;

int main() {
    int t, len, i, goals, misses;
    map<char, int> mp;
    string secret, guess;
    cin >> t;
    while (t --) {
        cin >> secret;
        cin >> guess;
        mp.clear();
        len = secret.length();
        for (i = 0; i < len; i++)
            mp[secret[i]]++;
        goals = 0;
        misses = 0;
        for (i = 0; i < len; i++) {
            if (secret[i] == guess[i]) {
                goals++;
                mp[guess[i]]--;
            }
        }
        for (i = 0; i < len; i++) {
            if (secret[i] != guess[i] && mp[guess[i]]) {
                misses++;
                mp[guess[i]]--;
            }
        }
        cout << goals << 'G' << misses << "M\n";
    }
}
