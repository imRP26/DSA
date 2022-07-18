import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/longest-common-prefix/
 */



// Naive
class Solution1 {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            boolean flag = true;
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
                    flag = false;
                    break;
                }
            }
            if (!flag)
                break;
            sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }
}



/*
 * Say you sort an array of words. The 1st word starts with a "b" and the last 
 * word also starts with a "b". What does that tell you about all the words in 
 * between them? They also all start with "b"! We know this because the words 
 * are sorted and the only way the 1st & the last word can both start with the 
 * same letter & have the array be in order is if all the words in between start 
 * with the same letter. Using this logic, if we sort our array of words we only 
 * ever have to look at the 1st & the last 1. We can ignore all the middle words 
 * entirely since we know if the prefix matches for the first and last, it matches 
 * for the middle ones as well.
 */
class Solution2 {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        int i = 0;
        String firstStr = strs[0], lastStr = strs[strs.length - 1];
        for (i = 0; i < firstStr.length(); i++) {
            if (firstStr.charAt(i) != lastStr.charAt(i))
                break;
        }
        return i == 0 ? "" : firstStr.substring(0, i);
    }
}
