/*
 * https://leetcode.com/problems/permutation-in-string/
 */



/*
 * My Simple Approach, TC = O(len2 * 26) !!!
 */
class Solution {

    private boolean isValidPermutation(int[] counts1, int[] counts2) {
        for (int i = 0; i < 26; i++) {
            if (counts1[i] != counts2[i])
                return false;
        }
        return true;
    }

    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length(), low = 0, high = 0;
        int[] counts1 = new int[26], counts2 = new int[26];
        for (int i = 0; i < len1; i++)
            counts1[s1.charAt(i) - 'a']++;
        for (high = 0; high < len2 && low <= len2 - len1; high++) {
            counts2[s2.charAt(high) - 'a']++;
            if (high - low + 1 < len1)
                continue;
            if (isValidPermutation(counts1, counts2))
                return true;
            counts2[s2.charAt(low++) - 'a']--;
        }
        return false;
    }
} 
