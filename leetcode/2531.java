/*
 * https://leetcode.com/problems/make-number-of-distinct-characters-equal/
 */



/*
 * HashMap, quite tricky!!
 * My Naive solution, but AC - this was unsatisfactory since this adhered to 
 * permutation programming!
 * This mayn't be correct, so don't refer to this!
 */ 
class Solution1 {
    public boolean isItPossible(String word1, String word2) {
        Map<Character, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (char c : word1.toCharArray())
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        for (char c : word2.toCharArray())
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        if (map1.size() == map2.size() && word1.length() == word2.length())
            return true;
        for (Map.Entry<Character, Integer> entry1 : map1.entrySet()) {
            for (Map.Entry<Character, Integer> entry2 : map2.entrySet()) {
                char ch1 = entry1.getKey(), ch2 = entry2.getKey();
                int sz1 = map1.size(), sz2 = map2.size();
                if (!map1.containsKey(ch2))
                    sz1++;
                if (!map2.containsKey(ch1))
                    sz2++;
                if (ch1 != ch2) {
                    if (map1.get(ch1) == 1)
                        sz1--;
                    if (map2.get(ch2) == 1)
                        sz2--;
                }
                if (sz1 == sz2)
                    return true;
            }
        }
        return false;
    }
}



/*
 * Simple character count array wala approach!
 * https://leetcode.com/problems/make-number-of-distinct-characters-equal/solutions/3016762/my-accepted-o-26-26-m-n-solution/
 */
class Solution2 {
    public boolean isItPossible(String word1, String word2) {
        int[] count1 = new int[26], count2 = new int[26];
        for (char c : word1.toCharArray())
            count1[c - 'a']++;
        for (char c : word2.toCharArray())
            count2[c - 'a']++;
        int uniqueChars1 = 0, uniqueChars2 = 0;
        for (int i = 0; i < 26; i++) {
            if (count1[i] > 0)
                uniqueChars1++;
            if (count2[i] > 0)
                uniqueChars2++;
        }
        if (uniqueChars1 == uniqueChars2 && word1.length() == word2.length())
            return true;
        for (int i = 0; i < 26; i++) {
            if (count1[i] == 0)
                continue;
            for (int j = 0; j < 26; j++) {
                if (count2[j] == 0)
                    continue;
                int sz1 = uniqueChars1, sz2 = uniqueChars2;
                if (count1[j] == 0)
                    sz1++;
                count1[j]++;
                if (count2[i] == 0)
                    sz2++;
                count2[i]++;
                if (count1[i] == 1)
                    sz1--;
                count1[i]--;
                if (count2[j] == 1)
                    sz2--;
                count2[j]--;
                if (sz1 == sz2)
                    return true;
                count1[j]--;
                count2[i]--;
                count1[i]++;
                count2[j]++;
            }
        }
        return false;
    }
}
