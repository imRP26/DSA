import java.io.*;
import java.lang.*;
import java.util.*;


class Codeforces {

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

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

	public static void main(String[] args) throws java.lang.Exception {
		FastScanner sc = new FastScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		
		//starting the solution below...
		int n = sc.nextInt(), maxVal = 0, gcdVal = 1;
		for (int i = 1; i <= n; i++) {
		    int a = sc.nextInt();
		    maxVal = Math.max(maxVal, a);
		    if (i == 1)
		        gcdVal = a;
		    else
		        gcdVal = gcd(gcdVal, a);
		}
		int rounds = maxVal / gcdVal - n;
		if (rounds % 2 == 0)
		    out.println("Bob");
		else
		    out.println("Alice");
        //finishing the solution code here...
		
		out.close(); 
	}
}
