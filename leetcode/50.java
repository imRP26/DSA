/*
 * https://leetcode.com/problems/powx-n/
 */



/*
 * Proper Explanation, refer to Approaches 2 and 3 of 
 * https://leetcode.com/problems/powx-n/solutions/127497/pow-x-n/
 */ 
class Solution1 { // My Solution
    public double myPow(double x, int n) {
        long n1 = n;
        if (n1 < 0) {
            n1 *= -1;
            x = 1 / x;
        }
        double result = 1.0;
        while (n1 > 0) {
            if (n1 % 2 == 1)
                result *= x;
            x *= x;
            n1 /= 2;
        }
        return result;
    }
}



/*
 * Fast Exponentiation using Recursion
 */
class Solution2 {

    private double fastPow(double x, long n) {
        if (n == 0)
            return 1.0;
        double half = fastPow(x, n / 2);
        if (n % 2 == 0)
            return half * half;
        return half * half * x;
    }

    public double myPow(double x, int n) {
        long n1 = n;
        if (n1 < 0) {
            n1 = -n1;
            x = 1 / x;
        }
        return fastPow(x, n1);
    }
}
