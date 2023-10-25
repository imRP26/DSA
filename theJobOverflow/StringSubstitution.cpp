/*
 * https://thejoboverflow.com/problem/331/
 */
// Sort using L's or using R's - both got AC!!
#include <bits/stdc++.h>
using namespace std;
#define ll long long

int main() {
	int n, l, r, j = 0;
	string s, str;
	cin >> s;
	cin >> n;
	vector<pair<int, pair<int, string> > > v(n); // {R, L, str}
	/*
	for (int i = 0; i < n; i++)
		cin >> v[i].second.first >> v[i].first >> v[i].second.second;
	*/
	for (int i = 0; i < n; i++)
		cin >> v[i].first >> v[i].second.first >> v[i].second.second;
	sort(v.begin(), v.end());
	/*
	for (int i = 0; i < s.length(); i++) {
		if (i == v[j].second.first) {
			cout << v[j].second.second;
			i = v[j].first;
			j++;
		}
		else
			cout << s[i];
	}
	*/
	for (int i = 0; i < s.length(); i++) {
		if (i == v[j].first) {
			cout << v[j].second.second;
			i = v[j].second.first;
			j++;
		}
		else
			cout << s[i];
	}
}
