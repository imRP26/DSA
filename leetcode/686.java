/*
 * https://leetcode.com/problems/repeated-string-match/
 */



/*
 * Intuition from ->
 * https://leetcode.com/problems/repeated-string-match/description/comments/1574525
 */
class Solution {

    private final long mod = 1_000_000_007;

    private long computeHash(String s) {
        long ans = 0, expo = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            ans = (ans + ((s.charAt(i) - 'a' + 1) * expo) % mod) % mod;
            expo = (expo * 29) % mod;
        }
        return ans;
    }

    /*
     * We may need to subtract 1 from the final result if for a particular index i, 
     * the hash values match up and the last position of the required subtring from 
     * i < the 1st index of the last occurence of a in sa (repeated a).
     */
    public int repeatedStringMatch(String a, String b) {
        int len1 = a.length(), len2 = b.length(), res = 1;
        String sa = a, sb = b;
        /*
         * The last possible index for which we have to check is len1 - 1, and from there,
         * the maximum possible ending index is len1 - 1 + len2.
         */
        while (sa.length() < len1 - 1 + len2) {
            sa += a;
            res++;
        }
        long maxExpo = 1, hash1 = computeHash(b), hash2 = computeHash(sa.substring(0, len2));
        if (hash1 == hash2)
            return ((res - 1) * len1 > len2 - 1) ? res - 1 : res;
        for (int i = 1; i < len2; i++)
            maxExpo = (maxExpo * 29) % mod;
        for (int i = 1; i < len1; i++) {
            hash2 = (hash2 - (((sa.charAt(i - 1) - 'a' + 1) * maxExpo) % mod) + mod) % mod;
            hash2 = (hash2 * 29) % mod;
            hash2 = (hash2 + (sa.charAt(i + len2 - 1) - 'a' + 1)) % mod;
            if (hash1 == hash2)
                return ((res - 1) * len1 > i + len2 - 1) ? res - 1 : res;
        }
        return -1;
    }
}
