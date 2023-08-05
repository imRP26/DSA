import java.io.*;
import java.util.*;


public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
		    int n = Integer.parseInt(br.readLine().trim()), res = 1;
		    String[] temp = br.readLine().trim().split(" ");
		    int[] arr = new int[n + 1], dp = new int[n + 1];
		    Arrays.fill(dp, 1);
		    arr[0] = -1;
		    for (int i = 0; i < n; i++)
		        arr[i + 1] = Integer.parseInt(temp[i]);
		    for (int i = 1; i <= n; i++) {
		        for (int j = 2 * i; j <= n; j += i) { // clever optimization!
		            if (arr[i] < arr[j])
		                dp[j] = Math.max(dp[j], 1 + dp[i]);
		        }
		    }
		    for (int val : dp)
		        res = Math.max(res, val);
		    System.out.println(res);
		}
	}
}
