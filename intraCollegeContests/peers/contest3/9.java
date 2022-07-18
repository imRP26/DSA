/* 
 * Question Link -> https://leetcode.com/problems/palindrome-number/
*/



class Solution {
    public boolean isPalindrome(int x) {
        int xRev = 0, a = x;
        while (a > 0) {
            xRev = xRev * 10 + (a % 10);
            a /= 10;
        }
        return (x == xRev);
    }
}
