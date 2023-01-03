/*
 * https://codeforces.com/problemset/problem/1395/C
 */



/*
 * Fail!!!
 * https://codeforces.com/blog/entry/81355
 */
import java.io.*;
import java.lang.*;
import java.util.*;


public class Codeforces {

	public static PrintWriter out; //PrintWriter for faster output

	public static class FastScanner { //MyScanner class for faster input
		BufferedReader br;
		StringTokenizer st;

		public FastScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		
		public FastScanner(String s) {
		    try {
		        br = new BufferedReader(new FileReader(s));    
		    }
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}

	public static void main(String[] args) throws java.lang.Exception {
		FastScanner sc = new FastScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		
		//starting the solution below...
		int n = sc.nextInt(), m = sc.nextInt(), x, result;
		Set<Integer> a = new HashSet<>(), b = new HashSet<>();
		while (n-- > 0) {
			x = sc.nextInt();
			a.add(x);
		}
		while (m-- > 0) {
			x = sc.nextInt();
			b.add(x);
		}
		for (result = 0; result < 512; result++) {
			boolean flag = false;
			int num = 0;
			for (int i : a) {
				for (int j : b) {
					if (((i & j) | result) == result) {
						num++;
						break;
					}
				}
			}
			if (num == a.size())
				break;
		}
		out.println(result);
		//finishing the solution code here...
		
		out.close(); 
	}
}
