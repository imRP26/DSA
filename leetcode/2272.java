/*
 * https://leetcode.com/problems/substring-with-largest-variance/
 */



/*
 * Approach of Kadane's algo DP from LC Official Editorial
 */
class Solution {
    public int largestVariance(String s) {
        int[] charCounts = new int[26];
        for (char ch : s.toCharArray()) 
            charCounts[ch - 'a']++;
        int globalMax = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i == j || charCounts[i] == 0 || charCounts[j] == 0)
                    continue;
                char minorChar = (char)('a' + i), majorChar = (char)('a' + j);
                int minorCharCount = 0, majorCharCount = 0, remMinorCharCount = charCounts[i];
                for (char ch : s.toCharArray()) {
                    if (ch == majorChar)
                        majorCharCount++;
                    if (ch == minorChar) {
                        minorCharCount++;
                        remMinorCharCount--;
                    }
                    if (minorCharCount > 0)
                        globalMax = Math.max(globalMax, majorCharCount - minorCharCount);
                    if (majorCharCount < minorCharCount && remMinorCharCount > 0)
                        majorCharCount = minorCharCount = 0;
                }
            }
        }
        return globalMax;
    }
}
