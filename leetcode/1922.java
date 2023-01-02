/*
 * https://leetcode.com/problems/count-good-numbers/
 */



/*
 * Simple observation and iterative fast exponentiation
 */
class Solution {

    private static final long mod = (long)1e9 + 7;

    private long customPow(long base, long expo) {
        long answer = 1;
        while (expo > 0) {
            if (expo % 2 == 1)
                answer = answer * base % mod;
            base = base * base % mod;
            expo /= 2;
        }
        return answer;
    }

    public int countGoodNumbers(long n) {
        if (n % 2 == 0)
            return (int)(customPow(5L, n / 2) * customPow(4L, n / 2) % mod);
        return (int)(customPow(5L, n / 2 + 1) * customPow(4L, n / 2) % mod);
    }
}
