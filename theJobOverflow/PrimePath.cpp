/*
 * https://thejoboverflow.com/problem/328/
 */
#include <bits/stdc++.h>
using namespace std;

const int maxNum = 1e6;
vector<int> primeFactor(1 + maxNum), numUniquePrimeFactors(1 + maxNum, 0);
vector<bool> isPrime(1 + maxNum, 1);

void sieve() {
	isPrime[0] = isPrime[1] = 0;
	for (int i = 2; i <= maxNum; i++) {
		if (isPrime[i]) {
			primeFactor[i] = i;
			for (int j = 2 * i; j <= maxNum; j += i) {
				isPrime[j] = 0;
				primeFactor[j] = i;
			}
		}
	}
	for (int i = 2; i <= maxNum; i++) {
		int j = i;
		set<int> st;
		while (j > 1) {
			st.insert(primeFactor[j]);
			j /= primeFactor[j];
		}
		numUniquePrimeFactors[i] = st.size();
	}
}

/*
 * Simple Dijkstra's can be applied here!
 * Hardest part is figuring out the range of (i2, j2) from (i1, j1)!!
 */

int main() {
	int n;
	cin >> n;
	vector<vector<int> > grid(n, vector<int>(n)), dp(n, vector<int> (n, INT_MAX));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++)
			cin >> grid[i][j];
	}
	sieve();
	dp[0][0] = 0;
	priority_queue<pair<int, pair<int, int> >, vector<pair<int, pair<int, int> > >, greater<pair<int, pair<int, int> > > > minPQ;
	minPQ.push({0, {0, 0}});
	while (!minPQ.empty()) {
		int w = minPQ.top().first, x = minPQ.top().second.first, y = minPQ.top().second.second;
		minPQ.pop();
		if (x == n - 1 && y == n - 1)
			break;
		int p = numUniquePrimeFactors[grid[x][y]], val = sqrt(grid[x][y]);
		for (int i = max(0, x - p); i <= min(n - 1, x + p); i++) {
			for (int j = max(0, y - p); j <= min(n - 1, y + p); j++) {
				if (abs(x - i) + abs(y - j) > p)
					continue;
				if (dp[i][j] > w + val) {
					dp[i][j] = w + val;
					minPQ.push({dp[i][j], {i, j}});
				}
			}
		}
	}
	cout << dp[n - 1][n - 1];
}
