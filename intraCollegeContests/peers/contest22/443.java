/*
 * Question Link -> https://leetcode.com/problems/string-compression/ 
 */



// Simple Simulation of the problem at hand
class Solution1 {
    public int compress(char[] chars) {
        int i, len = 1, index = 0;
        for (i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1])
                len++;
            else {
                chars[index++] = chars[i - 1];
                if (len == 1)
                    continue;
                String groupLen = String.valueOf(len);
                for (int j = 0; j < groupLen.length(); j++)
                    chars[index++] = groupLen.charAt(j);
                len = 1;
            }
        }
        chars[index++] = chars[i - 1];
        if (len > 1) {
            String groupLen = String.valueOf(len);
            for (int j = 0; j < groupLen.length(); j++)
                chars[index++] = groupLen.charAt(j);
        }
        return index;
    }
}



// Jab aap thoda zyada dimaag laga lete ho
class Solution2 {
    public int compress(char[] chars) {
        int indexAns = 0, index = 0;
        while (index < chars.length) {
            char currentChar = chars[index];
            int count = 0;
            while (index < chars.length && chars[index] == currentChar) {
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if (count > 1) {
                for (char c : Integer.toString(count).toCharArray())
                    chars[indexAns++] = c;
            }
        }
        return indexAns;
    }
}
