/*
 * https://leetcode.com/problems/shortest-palindrome/
 */



/*
 * Approach of Rolling Hash from -> 
 * https://leetcode.com/problems/shortest-palindrome/solutions/60153/8-line-o-n-method-using-rabin-karp-rolling-hash/
 */
class Solution {
    public String shortestPalindrome(String s) {
        int len = s.length(), idx = -1;
        long mod = 1_000_000_007, hash1 = 0, hash2 = 0, expo = 1;
        for (int i = 0; i < len; i++) {
            hash1 = (hash1 * 29 + s.charAt(i) - 'a' + 1) % mod;
            hash2 = (hash2 + (s.charAt(i) - 'a' + 1) * expo) % mod;
            expo = (expo * 29) % mod;
            if (hash1 == hash2)
                idx = i;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = idx + 1; i < len; i++)
            sb.insert(0, s.charAt(i));
        return sb.toString();
    }
}
