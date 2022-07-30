import java.util.*;

/*
 * https://leetcode.com/problems/word-subsets/
 */



// Ad-Hoc, Simulation
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] charCounts = new int[26];
        int[] temp = new int[26];
        for (String word : words2) {
            Arrays.fill(temp, 0);
            for (int i = 0; i < word.length(); i++)
                temp[word.charAt(i) - 'a']++;
            for (int i = 0; i < 26; i++)
                charCounts[i] = Math.max(charCounts[i], temp[i]);
        }
        List<String> result = new ArrayList<>();
        for (String word : words1) {
            Arrays.fill(temp, 0);
            for (int i = 0; i < word.length(); i++)
                temp[word.charAt(i) - 'a']++;
            boolean flag = true;
            for (int i = 0; i < 26; i++) {
                if (temp[i] < charCounts[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                result.add(word);
        }
        return result;
    }
}
