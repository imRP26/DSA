/*
 * https://leetcode.com/problems/repeated-substring-pattern/
 */



/*
 * A Naive Approach from -> 
 * 
 */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (len % (i + 1) != 0)
                continue;
            String s1 = s.substring(0, i + 1);
            int numAppend = len / (i + 1), j = 0;
            for (j = 1; j < numAppend; j++) {
                String s2 = s.substring(j * (i + 1), (j + 1) * (i + 1));
                if (!s1.equals(s2))
                    break;
            }
            if (j == numAppend)
                return true;
        }
        return false;
    }
}



/*
 * Best explanation through this comment -> 
 * https://leetcode.com/problems/repeated-substring-pattern/solutions/94334/easy-python-solution-with-explaination/comments/115092
 * Time Complexity reference from ->
 * https://stackoverflow.com/questions/4089558/what-is-the-big-o-of-string-contains-in-java
 * TC = O(n * (2n))
 */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}



/*
 * Approach of String Hashing
 */
class Solution {

    private final long mod = (long)1e9 + 7;

    private long computeHash(String s) {
        long ans = 0, expo = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            ans = (ans + ((s.charAt(i) - 'a' + 1) * expo) % mod) % mod;
            expo = (expo * 29) % mod;
        }
        return ans;
    }

    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        if (len == 1)
            return false;
        String s1 = s + s;
        long maxExpo = 1;
        long hashVal1 = computeHash(s), hashVal2 = computeHash(s1.substring(1, len + 1));
        if (hashVal2 == hashVal1)
            return true;
        for (int i = 1; i < len; i++)
            maxExpo = (maxExpo * 29) % mod;
        for (int i = len + 1; i < 2 * len - 1; i++) {
            hashVal2 = (hashVal2 - ((s1.charAt(i - len) - 'a' + 1) * maxExpo) % mod + mod) % mod;
            hashVal2 = (hashVal2 * 29) % mod;
            hashVal2 = (hashVal2 + (s1.charAt(i) - 'a' + 1)) % mod;
            if (hashVal2 == hashVal1)
                return true;
        }
        return false;
    }
}
