/*
 * Question Link -> https://binarysearch.com/problems/ASCII-String-to-Integer
 */



// My (naive) approach - TC = O(n), SC = O(n)
class Solution1 {
    public int solve(String s) {
        String number = "";
        int result = 0;
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9')
                number += ch;
            else {
                if (number.length() == 0)
                    continue;
                result += Integer.parseInt(number);
                number = "";
            }
        }
        if (number.length() > 0)
            result += Integer.parseInt(number);
        return result;
    }
}



// A better solution - TC = O(n), SC = O(1)
class Solution2 {
    public int solve(String s) {
        int currNum = 0, result = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c))
                currNum = 10 * currNum + (int)(c - '0');
            else {
                result += currNum;
                currNum = 0;
            }
        }
        result += currNum;
        return result;
    }
}
