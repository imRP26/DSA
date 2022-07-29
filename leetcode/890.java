import java.util.*;

/*
 * https://leetcode.com/problems/find-and-replace-pattern/
 */



/*
 * My Naive Approach :-
 * (1) If the number of unique characters in a word and in a pattern aren't equal, 
 *     then its an easy NO case.
 * (2) Try mapping each character of a word to that of a pattern, if there's a 
 *     disagreement anywhere, then again its a NO case, else its a YES!!!
 */
class Solution1 {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        int[] charMapping = new int[26];
        Set<Character> wordSet = new HashSet<>();
        Set<Character> patternSet = new HashSet<>();
        for (char c : pattern.toCharArray()) // O(pattern.length())
            patternSet.add(c);
        for (String word : words) {
            Arrays.fill(charMapping, -1); // O(26)
            boolean flag = true;
            wordSet.clear();
            for (int i = 0; i < word.length(); i++) { // O(word.length())
                wordSet.add(word.charAt(i));
                int wordIndex = word.charAt(i) - 'a', patternIndex = pattern.charAt(i) - 'a';
                if (charMapping[wordIndex] == -1)
                    charMapping[wordIndex] = patternIndex;
                else if (charMapping[wordIndex] != patternIndex) {
                    flag = false;
                    break;
                }
            }
            if (flag && wordSet.size() == patternSet.size())
                result.add(word);
        }
        return result;
    }
}



// Another approach of @lee215
class Solution2 {

    public int[] helper(String w) {
        Map<Character, Integer> map = new HashMap<>();
        int n = w.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(w.charAt(i), map.size());
            arr[i] = map.get(w.charAt(i));
        }
        return arr;
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int[] tempPattern = helper(pattern);
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (Arrays.equals(helper(word), tempPattern))
                result.add(word);
        }
        return result;
    }
}
