/*
 * https://atcoder.jp/contests/dp/tasks/dp_m
 */
import java.io.*;
import java.util.*;


public class Main {
    
    /*
     * DP State :-
     * dp[n] = True, if the First player can win when 'n' stones remain.
     *       = False, if the Second player wins when 'n' stones remain.
     * 
     * DP Transitions :-
     * dp[n] = True, if for any stone 'stones[i]', dp[n - stones[i]] = False. 
               (because of alternating turns)
     * 
     * Final Answer :-
     * dp[k + 1]
     */
    
    private static int[] dp;
    private static int[] stones;
    
    private static int memoize(int n) {
        if (n == 0)
            return dp[n] = 2;
        if (dp[n] != 0)
            return dp[n];
        for (int i = 0; i < stones.length; i++) {
            if (n >= stones[i] && memoize(n - stones[i]) == 2)
                return dp[n] = 1;
        }
        return dp[n] = 2;
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]), k = Integer.parseInt(temp[1]);
		stones = new int[n];
		temp = br.readLine().trim().split(" ");
		for (int i = 0; i < n; i++)
			stones[i] = Integer.parseInt(temp[i]);
		/*
		boolean[] dp = new boolean[k + 1];
		for (int i = 1; i <= k; i++) {
			for (int j = 0; j < n; j++) {
			    int x = stones[j];
			    if (i >= x && !dp[i - x])
			        dp[i] = true;
			}
		}
		*/
		//if (dp[k])
		dp = new int[k + 1];
		System.out.println(memoize(k) == 1 ? "First" : "Second");
	}
}
