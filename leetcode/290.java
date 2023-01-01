/*
 * https://leetcode.com/problems/word-pattern/
 */



/*
 * My useless solution that got AC somehow - need to avoid!!
 */ 
class Solution1 {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> charToString = new HashMap<>();
        Map<String, Character> stringToChar = new HashMap<>();
        s += ' ';
        int i = 0, j = 0, slen = s.length(), plen = pattern.length();
        for (j = 0; j < plen; j++) {
            char c = pattern.charAt(j);
            String temp = "";
            while (i < slen && s.charAt(i) != ' ')
                temp += s.charAt(i++);
            i++;
            if (!charToString.containsKey(c))
                charToString.put(c, temp);
            if (!stringToChar.containsKey(temp))
                stringToChar.put(temp, c);
            if (!(temp.equals(charToString.get(c))))
                return false;
            if (stringToChar.get(temp) != c)
                return false;
        }
        return (j == plen && i == slen);
    }
}



/*
 * Approach 1 of 
 * https://leetcode.com/problems/word-pattern/solutions/711754/word-pattern/
 */
class Solution2 {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> charMap = new HashMap<>();
        Map<String, Character> wordMap = new HashMap<>();
        String[] words = s.split(" ");
        if (words.length != pattern.length())
            return false;
        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            if (!charMap.containsKey(c)) {
                if (wordMap.containsKey(w))
                    return false;
                charMap.put(c, w);
                wordMap.put(w, c);
            }
            else {
                String mappedWord = charMap.get(c);
                if (!mappedWord.equals(w))
                    return false;
            }
        }
        return true;
    }
} 



/*
 * Approach 2 of 
 * https://leetcode.com/problems/word-pattern/solutions/711754/word-pattern/
 */
class Solution3 {
    public boolean wordPattern(String pattern, String s) {
        HashMap indexMap = new HashMap();
        String[] words = s.split(" ");
        if (words.length != pattern.length())
            return false;
        for (Integer i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            if (!indexMap.containsKey(c))
                indexMap.put(c, i);
            if (!indexMap.containsKey(w))
                indexMap.put(w, i);
            if (indexMap.get(c) != indexMap.get(w))
                return false;
        }
        return true;
    }
}



/*
 * Ditto same solution as above, but a different representation of the map
 */
class Solution4 {
    public boolean wordPattern(String pattern, String s) {
        Map<Object, Integer> indexMap = new HashMap<>();
        String[] words = s.split(" ");
        if (words.length != pattern.length())
            return false;
        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            if (!indexMap.containsKey(c))
                indexMap.put(c, i);
            if (!indexMap.containsKey(w))
                indexMap.put(w, i);
            if (indexMap.get(c) != indexMap.get(w))
                return false;
        }
        return true;
    }
} 
