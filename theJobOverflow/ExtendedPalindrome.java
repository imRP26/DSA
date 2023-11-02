/*
 * https://thejoboverflow.com/problem/337/
 */
// Just go about doing simple simulation, don't think too much!
import java.io.*;
import java.util.*;


public class Main {
	
	private static final int limit = (int)1e5;
	private static boolean[] isPrime = new boolean[1 + limit];
	
	private static void sieve() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i <= limit; i++) {
			if (isPrime[i]) {
				for (int j = 2 * i; j <= limit; j += i)
					isPrime[j] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine().trim();
		sieve();
		int len = s.length(), res = -1;
		for (int degree = 1; degree <= len; degree++) {
			if ((degree == 1 || isPrime[degree]) && len % degree == 0) {
				List<String> list = new ArrayList<>();
				for (int i = 0; i < len; i++) {
					list.add(s.substring(i, i + degree));
					i += degree - 1;
				}
				int sz = list.size(), flag = 1;
				for (int i = 0; i < sz; i++) {
					if (!list.get(i).equals(list.get(sz - i - 1))) {
						flag = 0;
						break;
					}
				}
				if (flag == 1) {
					res = degree;
					break;
				}
			}
		}
		System.out.println(res);
	}
}
