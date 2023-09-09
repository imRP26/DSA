/*
 * https://leetcode.com/problems/text-justification/
 */



/*
 * Approach from -> 
 * https://leetcode.com/problems/text-justification/solutions/24902/java-easy-to-understand-broken-into-several-functions/comments/912729
 */
class Solution {

    private int findLastWordIndex(String[] words, int i, int maxWidth) {
        int currLen = -1, j = i; // initial value is -1 here since no space is added at the end of the last word
        while (j < words.length && currLen + 1 + words[j].length() <= maxWidth)
            currLen += 1 + words[j++].length();
        return j - 1;
    }

    private String blanks(int count) {
        StringBuilder sb = new StringBuilder();
        while (count-- > 0)
            sb.append(" ");
        return sb.toString();
    }

    private String padWithSpaces(String w, int maxWidth) {
        return w + blanks(maxWidth - w.length());
    }

    private String justify(String[] words, int low, int high, int maxWidth) {
        if (low == high)
            return padWithSpaces(words[low], maxWidth);
        boolean isLastLine = high == words.length - 1;
        int totalWordsLen = 0;
        for (int i = low; i <= high; i++)
            totalWordsLen += words[i].length();
        int numSpaces = maxWidth - totalWordsLen, numPaddedWords = high - low;
        StringBuilder sb = new StringBuilder();
        String spaceStr = isLastLine ? " " : blanks(numSpaces / numPaddedWords);
        int remSpaceCount = isLastLine ? 0 : numSpaces % numPaddedWords;
        for (int i = low; i <= high; i++) {
            sb.append(words[i]).append(spaceStr);
            if (remSpaceCount > 0) {
                sb.append(" ");
                remSpaceCount--;
            }
        }
        String line = sb.toString().trim();
        return padWithSpaces(line, maxWidth);
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int low = 0, high = 0;
        while (low < words.length) {
            high = findLastWordIndex(words, low, maxWidth);
            String line = justify(words, low, high, maxWidth);
            res.add(line);
            low = high + 1;
        }
        return res;
    }
}
