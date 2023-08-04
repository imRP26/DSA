/*
 * https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
 */



/*
 * Simple Application of HashMap and Bitmasking from -> 
 * https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/solutions/1471541/c-1-using-sliding-window-tle-2-using-mask-linear-approach-accepted/
 */
class Solution {
    public int findTheLongestSubstring(String s) {
        int bitmask = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        map.put(0, -1);
        for (int i = 0; i < s.length(); i++) {
            int val = 0;
            if (vowels.contains(s.charAt(i)))
                val = s.charAt(i);
            bitmask ^= val;
            map.putIfAbsent(bitmask, i);
            res = Math.max(res, i - map.get(bitmask));
        }
        return res;
    }
}
