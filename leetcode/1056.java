/*
 * https://leetcode.com/problems/confusing-number/
 */



/*
 * My Easy-ish solution, but TC noot optimal.
 * stringBuilder.deleteCharAt(int index) takes O(N) time.
 */ 
class Solution1 {
    public boolean confusingNumber(int n) {
        StringBuilder sb = new StringBuilder();
        int num = n;
        while (n > 0) {
            int d = n % 10;
            if (d == 2 || d == 3 || d == 4 || d == 5 || d == 7)
                return false;
            if (d == 0 || d == 1 || d == 8)
                sb.append(d);
            else if (d == 6)
                sb.append(9);
            else if (d == 9)
                sb.append(6);
            n /= 10;
        }
        if (num == 0)
            sb.append('0');
        while (sb.length() > 1 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);
        return Integer.parseInt(sb.toString()) != num;
    }
}



/*
 * Much better and simpler solution, referenced from Approach 2 of 
 * https://leetcode.com/problems/confusing-number/solutions/2918441/confusing-number/
 */
class Solution2 {
    public boolean confusingNumber(int n) {
        int num1 = n, num2 = 0;
        while (n > 0) {
            int d = n % 10;
            if (d == 2 || d == 3 || d == 4 || d == 5 || d == 7)
                return false;
            if (d == 0 || d == 1 || d == 8)
                num2 = num2 * 10 + d;
            else if (d == 6)
                num2 = num2 * 10 + 9;
            else
                num2 = num2 * 10 + 6;
            n /= 10;
        }
        return num1 != num2;
    }
}
