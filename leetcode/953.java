/*
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 */



/*
 * A very Good Ad-Hoc based question!! - done using HashMap
 */ 
class Solution1 {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++)
            map.put(order.charAt(i), i);
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i], word2 = words[i + 1];
            int numEqual = 0, len1 = word1.length(), len2 = word2.length(), len = Math.min(len1, len2);
            boolean check = false;
            for (int j = 0; j < len; j++) {
                int diff = map.get(word1.charAt(j)) - map.get(word2.charAt(j));
                if (diff == 0) {
                    numEqual++;
                    continue;
                }
                if (diff < 0) {
                    check = true;
                    break;
                }
                if (diff > 0)
                    return false;
            }
            if (check)
                continue;
            if (numEqual == len && len1 > len2)
                return false;
        }
        return true;
    }
}



/*
 * Done using arrays
 */
class Solution2 {
    public boolean isAlienSorted(String[] words, String order) {
        int[] orderMap = new int[26];
        for (int i = 0; i < 26; i++)
            orderMap[order.charAt(i) - 'a'] = i;
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i], word2 = words[i + 1];
            int numEqual = 0, len1 = word1.length(), len2 = word2.length(), len = Math.min(len1, len2);
            boolean check = false;
            for (int j = 0; j < len; j++) {
                int diff = orderMap[word1.charAt(j) - 'a'] - orderMap[word2.charAt(j) - 'a'];
                if (diff == 0) {
                    numEqual++;
                    continue;
                }
                if (diff < 0) {
                    check = true;
                    break;
                }
                if (diff > 0)
                    return false;
            }
            if (check)
                continue;
            if (numEqual == len && len1 > len2)
                return false;
        }
        return true;
    }
}
