/*
 * https://leetcode.com/problems/decoded-string-at-index/
 */



/*
 * Approach of Reversal Traversal from -> 
 * https://leetcode.com/problems/decoded-string-at-index/solutions/4094710/100-reverse-stack-commented-code/?envType=daily-question&envId=2023-09-27
 */
class Solution {
    public String decodeAtIndex(String s, int k) {
        long decodedLength = 0;
        for (char c : s.toCharArray())
            decodedLength = (Character.isDigit(c)) ? decodedLength * (c - '0') : decodedLength + 1;
        System.out.println(decodedLength);
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                decodedLength /= (c - '0');
                k %= decodedLength;
            }
            else {
                /*
                 * k == 0 will handle that case when only 1 string gets repeated, e.g., "leet3"!
                 * decodedLength == k will handle that case when there are 2 or more strings that get repeated, e.g., "prob3solve4"!
                 */
                if (k == 0 || decodedLength == k)
                    return String.valueOf(c);
                decodedLength--;
            }
        }
        return "";
    }
}



/*
 * Approch of Stack from -> 
 * https://leetcode.com/problems/decoded-string-at-index/solutions/4094710/100-reverse-stack-commented-code/?envType=daily-question&envId=2023-09-27
 */
// Ye baad mein dekha jayega, bohot complex laga!
