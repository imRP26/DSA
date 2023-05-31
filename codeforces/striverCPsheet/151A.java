import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] row = br.readLine().split(" ");
		int n, k, l, c, d, p, nl, np; 
		n = Integer.parseInt(row[0]);
		k = Integer.parseInt(row[1]);
		l = Integer.parseInt(row[2]);
		c = Integer.parseInt(row[3]);
		d = Integer.parseInt(row[4]);
		p = Integer.parseInt(row[5]);
		nl = Integer.parseInt(row[6]);
		np = Integer.parseInt(row[7]);
		int n1 = k * l / (nl * n), n2 = c * d / n, n3 = p / (np * n);
		System.out.println(Math.min(n1, Math.min(n2, n3)));
	}
}
