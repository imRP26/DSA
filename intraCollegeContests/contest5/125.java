/*
 * Question Link -> https://leetcode.com/problems/valid-palindrome/
 */



 // Simple 2-Pointers
 class Solution {
    
    public boolean checkAlphaNumeric(char ch) {
        return ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9'));
    }
    
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int n = s.length(), low = 0, high = n - 1;
        while (low < high) {
            while (low < n && !checkAlphaNumeric(s.charAt(low)))
                low++;
            while (high >= 0 && !checkAlphaNumeric(s.charAt(high)))
                high--;
            if (low < high && s.charAt(low) != s.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }
}
