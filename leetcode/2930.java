/*
 * https://leetcode.com/problems/number-of-strings-which-can-be-rearranged-to-contain-substring/
 */



/*
 * Approach from -> 
 * https://leetcode.com/problems/number-of-strings-which-can-be-rearranged-to-contain-substring/solutions/4276710/inclusion-exclusion-math-o-logn/
 */
class Solution {

    private final long mod = (long)1e9 + 7;

    private long fastExpo(long base, long expo) {
        long ans = 1;
        while (expo > 0) {
            if (expo % 2 == 1)
                ans = (ans * base) % mod;
            base = (base * base) % mod;
            expo /= 2;
        }
        return ans;
    }

    public int stringCount(int n) {
        long res = fastExpo(26, n);
        res = (res - (3 * fastExpo(25, n)) % mod) % mod;
        res += res < 0 ? mod : 0;
        res = (res - (n * fastExpo(25, n - 1)) % mod) % mod;
        res += res < 0 ? mod : 0;
        res = (res + (3 * fastExpo(24, n)) % mod) % mod;
        res += res < 0 ? mod : 0;
        res = (res + (2 * (n * fastExpo(24, n - 1)) % mod) % mod) % mod;
        res += res < 0 ? mod : 0;
        res = (res - fastExpo(23, n)) % mod;
        res += res < 0 ? mod : 0;
        res = (res - (n * fastExpo(23, n - 1)) % mod) % mod;
        res += res < 0 ? mod : 0;
        return (int)res;
    }
}
