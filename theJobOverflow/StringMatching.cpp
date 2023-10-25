/*
 * https://thejoboverflow.com/problem/36/
 * Ekdum chutiya approach hai - my way or the highway kinda scenario!!
 */
#include <bits/stdc++.h>
using namespace std;

int main() {
  	int n, count = 0;
  	string s, res = "";
  	cin >> n;
  	cin >> s;
  	for (int i = 0; i < n; i++) {
    	if (s[i] == '(') {
      	count++;
      	res += '(';
    	}
    	else if (s[i] == ')') {
    	  if (count) {
    	    res += ')';
    	    count--;
    	  }
    	  else {
    	    res += '(';
    	    count++;
    	  }
    	}
  	}
  	while (count--)
    	res += ')';
  	cout << res;
}
