/*
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */



/*
 * Sliding Window, Easy!
 */ 
class Solution {

    private boolean isAnagram(int[] pcounts, int[] scounts) {
        for (int i = 0; i < 26; i++) {
            if (pcounts[i] != scounts[i])
                return false;
        }
        return true;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int[] pcounts = new int[26], scounts = new int[26];
        for (Character c : p.toCharArray())
            pcounts[c - 'a']++;
        int low = 0, high = 0, slen = s.length(), plen = p.length();
        List<Integer> result = new ArrayList<>();
        for (high = 0; high < slen && low <= slen - plen; high++) {
            scounts[s.charAt(high) - 'a']++;
            if (high - low + 1 < plen)
                continue;
            if (isAnagram(pcounts, scounts))
                result.add(low);
            scounts[s.charAt(low++) - 'a']--;
        }
        return result;
    }
}
