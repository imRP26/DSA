/*
 * https://leetcode.com/problems/palindrome-permutation/
 */



// Ad-Hoc
class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray())
            charCount[c - 'a']++;
        int numOdd = 0;
        for (int i = 0; i < 26; i++) {
            if (charCount[i] % 2 != 0)
                numOdd++;
        }
        return numOdd <= 1;
    }
}
