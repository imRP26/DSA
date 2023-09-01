/*
 * https://leetcode.com/problems/counting-bits/
 */



/*
 * Approach of Pop-Count from LC Official Editorial!
 */
class Solution {

    private int popCount(int x) {
        int ans = 0;
        while (x != 0) {
            x &= x - 1;
            ans++;
        }
        return ans;
    }

    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++)
            res[i] = popCount(i);
        return res;
    }
}



/*
 * Approach of DP + MSB from LC Official Editorial!
 */
class Solution {
    /*
     * Recurrence Function :-
     * P(x + b) = P(x) + 1, b = 2^m > x
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        int x = 0, b = 1;
        while (b <= n) {
            while (x < b && x + b <= n) {
                res[x + b] = res[x] + 1;
                x++;
            }
            x = 0;
            b *= 2;
        }
        return res;
    }
}



/*
 * Approach of DP + LSB from LC Official Editorial!
 * This method was quite intuitive -> P(x) = P(x / 2) + (x % 2)
 */
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++)
            res[i] = res[i >> 1] + (i & 1);
        return res;
    }
}



/*
 * Approach of DP + Last Set Bit from LC Official Editorial!
 * P(x) = P(x & (x - 1)) + 1
 */
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++)
            res[i] = res[x & (x - 1)] + 1;
        return res;
    }
}



/*
 * Good solution to understand more about the intuition -> 
 * https://leetcode.com/problems/counting-bits/solutions/79557/how-we-handle-this-question-on-interview-thinking-process-dp-solution/
 */
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        int j = 1;
        for (int i = 1; i <= n; i++) {
            j *= (j * 2 <= i) ? 2 : 1;
            res[i] = res[i - j] + 1;
        }
        return res;
    }
}
