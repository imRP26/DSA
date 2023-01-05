/*
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 */



/*
 * Nick White's video on Sliding Window
 * https://www.youtube.com/watch?v=00FmUN1pkGE
 */ 
class Solution1 {
    public int characterReplacement(String s, int k) {
        int n = s.length(), low = 0, high, result = 0, maxCharCount = 0;
        int[] charCount = new int[26];
        for (high = 0; high < n; high++) { // window is s[low ... high]
            // incorporating this char into the current sliding window
			char cHigh = s.charAt(high);
            charCount[cHigh - 'A']++;
			// which char to be considered as the major / dominating char, i.e., the char which needn't be flipped into another char 
            maxCharCount = Math.max(maxCharCount, charCount[cHigh - 'A']);
            while (high - low + 1 - maxCharCount > k) { // shrinking the window
                charCount[s.charAt(low) - 'A']--;
				low++;
            }
            result = Math.max(result, high - low + 1); // max wrt current window size
        }
		return result; 
    }
}
