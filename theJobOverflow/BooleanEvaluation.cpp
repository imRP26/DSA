/*
 * https://thejoboverflow.com/problem/89/
 */
#include <bits/stdc++.h>
using namespace std;

/*
 * Explore bit-by-bit how A, B and X can interact (in 8 possible ways) to give C.
 * A -> a0 a1 ... a31
 * B -> b0 b1 ... b31
 * C -> c0 c1 ... c31
 * X -> x0 x1 ... x31
 * ((A | X) & (B | X)) = C
 * => ((a31 ... a0 | x31 ... x0) & (b31 ... b0 | x31 ... x0)) = c31 ... c0
 * => (a[i] | x[i]) & (b[i] | x[i]) = c[i]
 * Impossible case is when c[i] == 0 and a[i] = b[i] = 1.
 */
int main() {
	int t, a, b, c;
	vector<int> va, vb, vc, vx;
	bool isPossible;
	cin >> t;
	while (t--) {
		cin >> a >> b >> c;
		va.clear();
		vb.clear();
		vc.clear();
		while (va.size() < 32) {
			va.push_back(a % 2);
			a /= 2;
		}
		while (vb.size() < 32) {
			vb.push_back(b % 2);
			b /= 2;
		}
		while (vc.size() < 32) {
			vc.push_back(c % 2);
			c /= 2;
		}
		vx.clear();
		isPossible = 1;
		for (int i = 0; i < 32; i++) {
			if (!vc[i] && va[i] && vb[i]) {
				isPossible = 0;
				break;
			}
			if (vc[i] && va[i] && vb[i])
				vx.push_back(0);
			else if (vc[i] && va[i] && !vb[i])
				vx.push_back(1);
			else if (!vc[i] && va[i] && !vb[i])
				vx.push_back(0);
			else if (vc[i] && !va[i] && vb[i])
				vx.push_back(1);
			else if (!vc[i] && !va[i] && vb[i])
				vx.push_back(0);
			else if (vc[i] && !va[i] && !vb[i])
				vx.push_back(1);
			else
				vx.push_back(0);
		}
		if (!isPossible)
			cout << -1 << endl;
		else {
			int expo = 1, res = 0;
			for (int i = 0; i < 32; i++) {
				res += expo * vx[i];
				expo *= 2;
			}
			cout << res << endl;
		}
	}
}
