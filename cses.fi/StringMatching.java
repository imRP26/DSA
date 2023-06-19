/*
 * https://cses.fi/problemset/task/1753/
 */
import java.io.*;
import java.lang.*;
import java.util.*;
 
 
class StringMatching {
 
	private static final long mod = 1_000_000_007;
 
	private static long hashVal(String s) {
		long hash = 0, pow = 1;
		for (int i = s.length() - 1; i >= 0; i--) {
			hash = (hash + ((s.charAt(i) - 'a' + 1) * pow) % mod) % mod;
			pow = (pow * 41) % mod;
		} 
		return hash;
	}
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String t = br.readLine().trim(), p = br.readLine().trim();
		int tlen = t.length(), plen = p.length(), res = 0;
		if (tlen >= plen) {
			long maxPower = 1, phash = hashVal(p), thash = hashVal(t.substring(0, plen));
			for (int i = 1; i < plen; i++)
				maxPower = (maxPower * 41) % mod;
			res += thash == phash ? 1 : 0;
			for (int i = plen; i < tlen; i++) {
			    thash = (thash - ((t.charAt(i - plen) - 'a' + 1) * maxPower) % mod + mod) % mod;
				thash = (thash * 41) % mod;
				thash = (thash + (t.charAt(i) - 'a' + 1)) % mod;
				res += thash == phash ? 1 : 0;
			}
		}
		System.out.println(res);
	}
}
