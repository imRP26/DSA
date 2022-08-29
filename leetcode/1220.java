import java.util.*;

/*
 * https://leetcode.com/problems/count-vowels-permutation/
 */



/*
 * My complete explanation of DP using memoization 
 * Let's have the following mappings :- 'a' = 0, 'e' = 1, 'i' = 2, 'o' = 3, 'u' = 4.
 * dp[i][j] = number of strings formed when we have character-mapped number j at 
 * index i.
 * Let's see the possibilities as to what the previous characters may be if we're 
 * at a particular character :- 
  (1) If currChar == 'a', then prevChar = 'e' or prevChar = 'i' or prevChar = 'u'.
       => dp[i][0] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4].
  (2) If currChar == 'e', then prevChar = 'a' or prevChar = 'i'.
       => dp[i][1] = dp[i - 1][0] + dp[i - 1][2].
  (3) If currChar == 'i', then prevChar = 'e'.
       => dp[i][2] = dp[i - 1][1].
  (4) If currChar == 'o', then prevChar = 'i'.
       => dp[i][3] = dp[i - 1][2].
  (5) If currChar == 'u', then prevChar = 'i'.
       => dp[i][4] = dp[i - 1][2].
 * While doing recursion, it can be observed that we may have overlapping 
 * subproblems and hence we can resort to DP (memoization).
 */
class Solution1 {
    
    int recurse(int n, char currChar) {
        if (n == 0)
           return 1;
        if (currChar == 'a')
            return recurse(n - 1, 'e') + recurse(n - 1, 'i') + recurse(n - 1, 'u');
        if (currChar == 'e')
            return recurse(n - 1, 'a') + recurse(n - 1, 'i');
        if (currChar == 'i')
            return recurse(n - 1, 'e') + recurse(n - 1, 'o');
        if (currChar == 'o')
            return recurse(n - 1, 'i');
        return recurse(n - 1, 'i') + recurse(n - 1, 'o');
    }
    
    long memoize(int index, int charVal, long[][] dp, long mod) {
        if (dp[index][charVal] != -1)
            return dp[index][charVal];
        long answer;
        if (charVal == 0)
            answer = (memoize(index - 1, 1, dp, mod) + memoize(index - 1, 2, dp, mod) + memoize(index - 1, 4, dp, mod)) % mod;
        else if (charVal == 1)
            answer = (memoize(index - 1, 0, dp, mod) + memoize(index - 1, 2, dp, mod)) % mod;
        else if (charVal == 2)
            answer = (memoize(index - 1, 1, dp, mod) + memoize(index - 1, 3, dp, mod)) % mod;
        else if (charVal == 3)
            answer = memoize(index - 1, 2, dp, mod) % mod;
        else
            answer = (memoize(index - 1, 2, dp, mod) + memoize(index - 1, 3, dp, mod)) % mod;
        if (answer < 0)
            answer += mod;
        return dp[index][charVal] = answer;
    }
    
    public int countVowelPermutation(int n) {
        //return recurse(n - 1, 'a') + recurse(n - 1, 'e') + recurse(n - 1, 'i') + recurse(n - 1, 'o') + recurse(n - 1, 'u');
        long[][] dp = new long[n][5];
        long result = 0, mod = 1000000007;
        for (long[] row : dp)
            Arrays.fill(row, -1);
        for (int i = 0; i < 5; i++)
            dp[0][i] = 1;
        for (int i = 0; i < 5; i++) {
            result = (result + memoize(n - 1, i, dp, mod)) % mod;
            if (result < 0)
                result += mod;
        }
        return (int) result;
    }
}



/*
 * Bottom-Up Solution :- same concept as above
 */
class Solution2 {
    public int countVowelPermutation(int n) {
        int mod = (int)(1e9 + 7);
        long[][] dp = new long[n + 1][5];
        for (int i = 0; i < 5; i++)
            dp[1][i] = 1;
        for (int i = 1; i < n; i++) {
            dp[i + 1][0] = (dp[i][1] + dp[i][2] + dp[i][4]) % mod;
            dp[i + 1][1] = (dp[i][0] + dp[i][2]) % mod;
            dp[i + 1][2] = (dp[i][1] + dp[i][3]) % mod;
            dp[i + 1][3] = dp[i][2] % mod;
            dp[i + 1][4] = (dp[i][2] + dp[i][3]) % mod;
        }
        long result = 0;
        for (int i = 0; i < 5; i++)
            result = (result + dp[n][i]) % mod;
        return (int) result;
    }
} 



/*
 * Space-optimized version of Bottom-Up DP
 */
class Solution3 {
    public int countVowelPermutation(int n) {
        long mod = (long)(1e9 + 7), a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int j = 2; j <= n; j++) {
            long a1 = e % mod;
            if (a1 < 0)
                a1 += mod;
            long e1 = (a + i) % mod;
            if (e1 < 0)
                e1 += mod;
            long i1 = (a + e + o + u) % mod;
            if (i1 < 0)
                i1 += mod;
            long o1 = (i + u) % mod;
            if (o1 < 0)
                o1 += mod;
            long u1 = a % mod;
            if (u1 < 0)
                u1 += mod;
            a = a1;
            e = e1;
            i = i1;
            o = o1;
            u = u1;    
        }
        long result = (a + e + i + o + u) % mod;
        if (result < 0)
            result += mod;
        return (int) result;
    }
}



/*
 * Approach of Matrix Exponentiation - the problem basically boils down to number 
 * of paths of length N in a directed graph.
*/
class Solution4 {
    public int countVowelPermutation(int n) {
        long mod = (long)(1e9 + 7);
    }
}
