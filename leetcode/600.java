/*
 * https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/
 */



/*
 * Approch 3 (Bitmasking) from LC Official Editorial!
 */
class Solution {
    public int findIntegers(int n) {
        int[] fibo = new int[32];
        fibo[0] = 1;
        fibo[1] = 2;
        for (int i = 2; i < 32; i++)
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        int i = 31, res = 0, prevBit = 0;
        while (i >= 0) {
            if ((n & (1 << i)) != 0) {
                res += fibo[i];
                if (prevBit == 1) {
                    res--;
                    break;
                }
                prevBit = 1;
            }
            else
                prevBit = 0;
            i--;
        }
        return res + 1;
    }
}
