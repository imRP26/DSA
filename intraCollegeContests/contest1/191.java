/*
 * Question Link -> https://leetcode.com/problems/number-of-1-bits/
 */



// Bit of Theory here
class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int numberOfOnes = 0;
        while (n != 0) {
            numberOfOnes += (n & 1);
            /*
             * Right-Shift operator for an unsigned number -> >>>
             * >> depends on sign extension
             */
            n >>>= 1;
        }
        return numberOfOnes;
    }
}
