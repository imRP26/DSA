import java.io.*;
import java.lang.*;
import java.util.*;


/*
 * Simple, Greedy
 */
class Codechef {

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
		int t, n;
		long w, wr;
		Map<Integer, Integer> map = new HashMap<>();
		t = sc.nextInt();
		while (t-- > 0) {
		    n = sc.nextInt();
		    w = sc.nextLong();
		    wr = sc.nextLong();
		    map.clear();
		    for (int i = 0; i < n; i++) {
		        int wt = sc.nextInt();
		        map.put(wt, map.getOrDefault(wt, 0) + 1);
		    }
		    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
		        int wt = entry.getKey(), num = entry.getValue();
		        while (num >= 2) {
		            wr += 2 * wt;
		            num -= 2;
		        }
		    }
		    if (wr >= w)
		        out.println("YES");
		    else
		        out.println("NO");
		}
		//finishing the solution code here...
		
		out.close(); 
	}
}
