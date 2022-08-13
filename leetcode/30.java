import java.util.*;

/*
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 */



/*
 * Initially, a mapping is made for all the words belonging to words[] and now 
 * we iterate through s, treating each character as a starting character and then 
 * generating another map and verify its equality with the map constructed earlier.
 */
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words)
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        int wordLen = words[0].length(), totalLen = words.length * wordLen, slen = s.length();
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> temp = new HashMap<>();
        for (int i = 0; i < slen; i++) {
            if (i + totalLen - 1 >= slen)
                break;
            temp.clear();
            for (int j = i; j < i + totalLen; j += wordLen) {
                String str = s.substring(j, j + wordLen);
                temp.put(str, temp.getOrDefault(str, 0) + 1);
            }
            if (wordMap.equals(temp))
                result.add(i);
        }
        return result;
    }
}
