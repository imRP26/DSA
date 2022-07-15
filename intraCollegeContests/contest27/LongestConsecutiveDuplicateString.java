/*
 * https://binarysearch.com/problems/Longest-Consecutive-Duplicate-String
 */



// Simple, Ad-Hoc
class Solution {
    public int solve(String s) {
        int low = 0, high = 0, result = 0, len = s.length();
        if (len == 0)
            return result;
        char ch = s.charAt(high);
        for (high = 1; high < len; high++) {
            if (s.charAt(high) == ch)
                continue;
            else {
                result = Math.max(result, high - low);
                low = high;
                ch = s.charAt(high);
            }
        }
        result = Math.max(result, high - low);
        return result;
    }
}
