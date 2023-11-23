/*
 * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 */



/*
 * 1stly, store the counts of characters in an array of size 26.
 * 2ndly, store the counts of individual counts (e.g., how many distint characters 
 * occur thrice each) - now from the maxCharCount onwards, run a loop and just 
 * simulate the given problem.
 */
class Solution {
    public int minDeletions(String s) {
        int maxCharCount = 0, res = 0;
        int[] letterCount = new int[26];
        for (char c : s.toCharArray())
            maxCharCount = Math.max(maxCharCount, ++letterCount[c - 'a']);
        int[] freqCounts = new int[1 + maxCharCount];
        for (int i = 0; i < 26; i++)
            freqCounts[letterCount[i]]++;
        for (int i = maxCharCount; i > 0; i--) {
            if (freqCounts[i] <= 1)
                continue;
            res += freqCounts[i] - 1;
            freqCounts[i - 1] += freqCounts[i] - 1;
        }
        return res;
    }
}



/*
 * Approach of Priority Queue from LC Official Editorial!
 */

