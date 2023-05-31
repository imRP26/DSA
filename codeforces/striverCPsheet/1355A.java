import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			String[] row = br.readLine().split(" ");
			long a1 = Long.parseLong(row[0]), k = Long.parseLong(row[1]), a2 = a1;
			while (k-- > 1) {
				long maxd = Integer.MIN_VALUE, mind = Integer.MAX_VALUE;
				while (a2 > 0) {
					long d = a2 % 10;
					maxd = Math.max(maxd, d);
					mind = Math.min(mind, d);
					a2 /= 10;
				}
				a2 = a1 + mind * maxd;
				if (a2 == a1)
					break;
				a1 = a2;
			}
			System.out.println(a2);
		}
	}
}
