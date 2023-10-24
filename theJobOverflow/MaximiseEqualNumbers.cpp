/*
 * https://thejoboverflow.com/problem/390/
 */
// This question is exactly same as -> LC 2779 -> https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/




/*
 * Approach 1 of Sorting and then Sliding Window
 */
#include <bits/stdc++.h>
using namespace std;

int main() {
	int n, k, low = 0, high = 0, res = 0;
	cin >> n >> k;
	vector<int> v(n);
	for (int i = 0; i < n; i++)
		cin >> v[i];
	sort(v.begin(), v.end());
	while (high < n) {
		if (v[high] - v[low] <= 2 * k)
			res = max(res, high - low + 1);
		else {
			while (v[high] - v[low] > 2 * k)
				low++;
		}
		high++;
	}
	cout << res;
}



/*
 * Approach 2 of Prefix Sums and Difference Arrays
 */
#include <bits/stdc++.h>
using namespace std;

int main() {
	int n, k, a, sum = 0, res = 0;
	cin >> n >> k;
	map<int, int> mp;
	while (n--) {
		cin >> a;
		mp[a - k]++;
		mp[a + k + 1]--;
	}
	for (auto it = mp.begin(); it != mp.end(); it++) {
		sum += it->second;
		res = max(sum, res);
	}
	cout << res;
} 
