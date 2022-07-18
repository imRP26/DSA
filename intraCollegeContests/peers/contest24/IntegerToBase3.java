/*
 * Question Link -> https://binarysearch.com/problems/Integer-to-Base-3
 */



// Simple
class Solution1 {
    public String solve(int n) {
        if (n == 0)
            return "0";
        String result = "";
        while (n > 0) {
            result = Integer.toString(n % 3) + result;
            n /= 3;
        }
        return result;
    }
}



// Same solution as above, but using StringBuilder
class Solution2 {
    public String solve(int n) {
        if (n == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 3);
            n /= 3;
        }
        return new String(sb.reverse());
    }
}
