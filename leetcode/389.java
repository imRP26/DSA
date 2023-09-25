/*
 * https://leetcode.com/problems/find-the-difference/
 */



/*
 * Naive Approach of Sorting
 */
class Solution {
    public char findTheDifference(String s, String t) {
        char[] schars = s.toCharArray(), tchars = t.toCharArray();
        Arrays.sort(schars);
        Arrays.sort(tchars);
        int tlen = t.length();
        for (int i = 0; i < tlen - 1; i++) {
            if (schars[i] != tchars[i])
                return tchars[i];
        }
        return tchars[tlen - 1];
    }
}



/*
 * Naive Approach of HashMap
 */
class Solution {
    public char findTheDifference(String s, String t) {
        Map<Character, Integer> smap = new HashMap<>(), tmap = new HashMap<>();
        for (char c : s.toCharArray())
            smap.put(c, smap.getOrDefault(c, 0) + 1);
        for (char c : t.toCharArray())
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        for (char c : t.toCharArray()) {
            if (smap.get(c) != tmap.get(c))
                return c;
        }
        return '#';
    }
}



/*
 * Approach of Bit Manipulation from LC Official Editorial!
 */
class Solution {
    public char findTheDifference(String s, String t) {
        int xorval = 0;
        for (char c : s.toCharArray())
            xorval ^= (c - 'a');
        for (char c : t.toCharArray())
            xorval ^= (c - 'a');
        return (char)(xorval + 'a');
    }
}
