/*
 * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 */



/*
 * 1stly, store the counts of characters in an array of size 26.
 * 2ndly, store the counts of individual counts (e.g., how many distint characters 
 * occur thrice each) - now from the maxCharCount onwards, run a loop and just 
 * simulate the given problem.
 */
class Solution1 {
    public int minDeletions(String s) {
        int[] charCount = new int[26];
        int maxCharCount = 0;
        for (char c : s.toCharArray())
            maxCharCount = Math.max(maxCharCount, ++charCount[c - 'a']);    
        int[] alphabetCount = new int[maxCharCount + 1];
        for (int i = 0; i < 26; i++)
            alphabetCount[charCount[i]]++;
        int result = 0;
        for (int i = maxCharCount; i > 0; i--) {
            if (alphabetCount[i] <= 1)
                continue;
            result += alphabetCount[i] - 1;
            alphabetCount[i - 1] += alphabetCount[i] - 1;
        }
        return result;
    }
}
