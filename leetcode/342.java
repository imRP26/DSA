/*
 * https://leetcode.com/problems/power-of-four/
 */



// Simple Ad-Hoc
class Solution {
    public boolean isPowerOfFour(int n) {
        while (n > 1) {
            if (n % 4 != 0)
                return false;
            n /= 4;
        }
        return (n == 1);
    }
}
