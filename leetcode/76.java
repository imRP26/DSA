/*
 * https://leetcode.com/problems/minimum-window-substring/
 */



/*
 * StackOverflow Reference ->
 * https://stackoverflow.com/questions/57741909/is-calling-intvalue-method-required-when-getting-an-integer-value-from-java-ha
 *
 * LC Reasoning of using map.get(c).intValue() ->
 * https://leetcode.com/problems/minimum-window-substring/editorial/comments/230822
 * 
 * Approach of Sliding Window from LC Official Editorial!
 */
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> tmap = new HashMap<>(), smap = new HashMap<>();
        for (char c : t.toCharArray())
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        int low = 0, minLen = -1, leftPtr = -1, rightPtr = -1, numMatchingChars = 0;
        for (int high = 0; high < s.length(); high++) {
            char c = s.charAt(high);
            smap.put(c, smap.getOrDefault(c, 0) + 1);
            if (tmap.containsKey(c) && tmap.get(c).intValue() == smap.get(c).intValue())
                numMatchingChars++;
            System.out.println(numMatchingChars + " " + tmap.size());
            while (low <= high && numMatchingChars == tmap.size()) {
                if (minLen == -1 || high - low + 1 < minLen) {
                    minLen = high - low + 1;
                    leftPtr = low;
                    rightPtr = high;
                }
                c = s.charAt(low);
                smap.put(c, smap.get(c) - 1);
                if (tmap.containsKey(c) && tmap.get(c).intValue() > smap.get(c).intValue())
                    numMatchingChars--;
                low++;
            }
        }
        return minLen == -1 ? "" : s.substring(leftPtr, rightPtr + 1);
    }
}
