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
		int t, n, x;
		t = sc.nextInt();
		while (t-- > 0) {
		    n = sc.nextInt();
		    x = sc.nextInt();
		    int[] degree = new int[n + 1];
		    for (int i = 1; i < n; i++) {
		        int u = sc.nextInt(), v = sc.nextInt();
		        degree[u]++;
		        degree[v]++;
		    }
		    if (degree[x] <= 1) {
		        out.println("Ayush");
		        continue;
		    }
		    if (n % 2 == 0)
		        out.println("Ayush");
		    else
		        out.println("Ashish");
		}
        //finishing the solution code here...
		
		out.close(); 
	}
}
