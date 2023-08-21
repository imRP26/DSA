/*
 * https://leetcode.com/problems/longest-happy-prefix/
 */



/*
 * Simple Approach of Rolling Hash from -> 
 * https://leetcode.com/problems/longest-happy-prefix/solutions/547237/java-python-rolling-hash/
 */
class Solution {
    public String longestPrefix(String s) {
        int len = s.length(), idx = -1;
        long mod = (long)1e9 + 7, prefixHash = 0, suffixHash = 0, expo = 1;
        for (int i = 0; i < len - 1; i++) {
            prefixHash = (prefixHash + (s.charAt(i) - 'a' + 1) * expo) % mod;
            suffixHash = (suffixHash * 29 + s.charAt(len - i - 1) - 'a' + 1) % mod;
            if (prefixHash == suffixHash)
                idx = i;
            expo = (expo * 29) % mod;
        }
        return s.substring(0, idx + 1);
    }
}
