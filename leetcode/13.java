import java.util.*;

/*
 * https://leetcode.com/problems/roman-to-integer/
 */



/*
 * Simple Simulation - I just like my solution that I'd done earlier in an 
 * intra-college BinarySeach contest
 */
class Solution {

    boolean checkAmbiguity(char ch1, char ch2) {
        return ((ch1 == 'I' && ch2 == 'V') || (ch1 == 'I' && ch2 == 'X') || 
                (ch1 == 'X' && ch2 == 'L') || (ch1 == 'X' && ch2 == 'C') || 
                (ch1 == 'C' && ch2 == 'D') || (ch1 == 'C' && ch2 == 'M'));
    }

    public int romanToInt(String s) {
        Map<Character, Integer> mapping = new HashMap<>();
        mapping.put('I', 1);
        mapping.put('V', 5);
        mapping.put('X', 10);
        mapping.put('L', 50);
        mapping.put('C', 100);
        mapping.put('D', 500);
        mapping.put('M', 1000);
        int result = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            if (i < len - 1 && checkAmbiguity(s.charAt(i), s.charAt(i + 1))) {
                result += mapping.get(s.charAt(i + 1)) - mapping.get(s.charAt(i));
                i++;
            }
            else
                result += mapping.get(s.charAt(i));
        }
        return result;
    }
}
