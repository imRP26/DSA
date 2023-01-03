/*
 * https://codeforces.com/problemset/problem/1567/C
 */



/*
 * FAILED!!
 * https://codeforces.com/blog/entry/94581
 * https://www.youtube.com/watch?v=7ou1zqT55LQ
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
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt(), turn = 1;
			if (n < 10) {
			    out.println(n - 1);
			    continue;
			}
			StringBuilder num1 = new StringBuilder(), num2 = new StringBuilder();
			while (n > 0) {
				int d = n % 10;
				n /= 10;
				if (turn == 1)
					num1.append((char)(d + '0'));
				else
					num2.append((char)(d + '0'));
				turn = 3 - turn;
			}
			int n1 = Integer.parseInt(num1.reverse().toString());
            //int n1 = Integer.valueOf(num1.reverse().toString());
			int n2 = Integer.parseInt(num2.reverse().toString());
            //int n2 = Integer.valueOf(num2.reverse().toString());
			n = (n1 + 1) * (n2 + 1) - 2;
			out.println(n);
		}
    	//finishing the solution code here...
		
		out.close(); 
	}
}
