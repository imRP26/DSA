/*
 * Solution 1 using HashMap
 */
class Solution1 {
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < 26; i++)
            map.put(keyboard.charAt(i), i);
        for (int i = 0; i < word.length(); i++) {
            if (i == 0)
                result += map.get(word.charAt(0));
            else
                result += Math.abs(map.get(word.charAt(i)) - map.get(word.charAt(i - 1)));
        }
        return result;
    }
}


/*
 * Solution 2 using Arrays
 */
class Solution2 {
    public int calculateTime(String keyboard, String word) {
        int[] position = new int[26];
        int result = 0;
        for (int i = 0; i < 26; i++)
            position[keyboard.charAt(i) - 'a'] = i;
        for (int i = 0; i < word.length(); i++) {
            if (i == 0)
                result += position[word.charAt(0) - 'a'];
            else
                result += Math.abs(position[word.charAt(i) - 'a'] - position[word.charAt(i - 1) - 'a']);
        }
        return result;
    }
}
