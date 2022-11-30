/*
 * Greedy Solution -> Iterate from the beginning and the end, check if the chars 
 * match and find the number of such non-matching pairs.
 */
class Solution {
    public boolean makePalindrome(String s) {
        int i = 0, j = s.length() - 1, num = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                num++;
            if (num > 2)
                return false;
            i++;
            j--;
        }
        return true;
    }
}
