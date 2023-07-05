/*
 * https://leetcode.com/problems/power-of-two/
 */



/*
 * Naive Method
 */
class Solution {
    public boolean isPowerOfTwo(int n) {
        while (n > 1) {
            if (n % 2 == 1)
                return false;
            n /= 2;
        }
        return (n <= 0) ? false : true;
    }
}



/*
 * Clever hack of bit manipulation
 */
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;
        return ((n & (n - 1)) == 0);
    }
}
