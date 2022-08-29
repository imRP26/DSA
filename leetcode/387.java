import java.util.*;

/*
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 */



// Easy Simulation - 2 Pass
class Solution1 {
    public int firstUniqChar(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray())
            charCount[c - 'a']++;    
        for (int i = 0; i < s.length(); i++) {
            if (charCount[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
}



// 1-Pass Solution
class Solution2 {
    public int firstUniqChar(String s) {
        int[] position = new int[26];
        Arrays.fill(position, -2);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (position[index] == -2)
                position[index] = i;
            else
                position[index] = -1;
        }
        for (int i = 0; i < 26; i++) {
            if (position[i] >= 0)
                result = Math.min(result, position[i]);
        }
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }
}
