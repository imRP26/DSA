/*
 * https://leetcode.com/problems/super-pow/
 */



/*
 * https://leetcode.com/problems/super-pow/solutions/84485/8ms-java-solution-using-fast-power/
 */
class Solution {

    private static final int mod = 1337;

    private int customPow(int a, int b) {
        int answer = 1;
        while (b != 0) {
            if (b % 2 != 0)
                answer = answer * a % mod;
            a = a * a % mod;
            b /= 2;
        }
        return answer;
    }

    public int superPow(int a, int[] b) {
        a %= mod;
        int result = 1;
        for (int i = b.length - 1; i >= 0; i--) {
            result = result * customPow(a, b[i]) % mod;
            a = customPow(a, 10);
        }
        return result;
    }
} 
