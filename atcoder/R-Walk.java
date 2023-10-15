/*
 * https://atcoder.jp/contests/dp/tasks/dp_r
 */
// Somewhat of a prelude as to why matrix exponentiation is being applied here -> 
// https://discuss.codechef.com/t/r-walk-atcoder-educational-dp/58938/2?u=rahul1003
import java.io.*;
import java.util.*;


public class Main {

    /*
     * If A is the adjacency matrix of a graph, then the (i, j)-th entry of A^k gives the 
     * number of k-length walks connecting the vertices 'i' and 'j'.
     * In a walk, vertices and edges repeat.
     */

    private static int n;
	private static final long mod = (long)1e9 + 7;
	private static long[][] baseMatrix, finalMatrix, tempMatrix;

	private static void multiply1() {
	    for (long[] row : tempMatrix)
	        Arrays.fill(row, 0);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					long val = (finalMatrix[i][k] * baseMatrix[k][j]) % mod;
					tempMatrix[i][j] = (tempMatrix[i][j] + val) % mod;
				}
			}
		}
		for (int i = 0; i < n; i++) {
		    for (int j = 0; j < n; j++)
		        finalMatrix[i][j] = tempMatrix[i][j];
		}
	}

	private static void multiply2() {
	    for (long[] row : tempMatrix)
	        Arrays.fill(row, 0);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					long val = (baseMatrix[i][k] * baseMatrix[k][j]) % mod;
					tempMatrix[i][j] = (tempMatrix[i][j] + val) % mod;
				}
			}
		}
		for (int i = 0; i < n; i++) {
		    for (int j = 0; j < n; j++)
		        baseMatrix[i][j] = tempMatrix[i][j];
		}
	}

	private static void fastExponentiation(long expo) {
		while (expo > 0) {
			if (expo % 2 == 1)
				multiply1();
			multiply2();
			expo /= 2;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		n = Integer.parseInt(temp[0]);
		long k = Long.parseLong(temp[1]), res = 0;
		baseMatrix = new long[n][n];
		finalMatrix = new long[n][n];
		for (int i = 0; i < n; i++) {
			temp = br.readLine().trim().split(" ");
			for (int j = 0; j < n; j++) {
				baseMatrix[i][j] = Integer.parseInt(temp[j]);
				finalMatrix[i][j] = i == j ? 1 : 0;
			}
		}
		tempMatrix = new long[n][n];
		fastExponentiation(k);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
			    res = (res + finalMatrix[i][j]) % mod;
		}
		System.out.println(res);
	}
}
