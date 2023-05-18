/*
 * https://leetcode.com/problems/nth-digit/
 */



/*
 * Approach copied wholesomely from -> 
 * https://leetcode.com/problems/nth-digit/solutions/334907/just-wanted-to-share-my-java-solution-too-with-easy-explanation-the-submit-gives-100-fast/
 */
class Solution {
    public int findNthDigit(int n) {
        int digits = 1, numIntervalDigits = 9;
        while (n - numIntervalDigits > 0) {
            n -= numIntervalDigits;
            digits += 1;
            numIntervalDigits = 9 * ((int)Math.pow(10, digits - 1)) * digits;
            if (numIntervalDigits < 0) // breaking out of the loop in case of overflow!
                break;
        }
        int base = ((int)Math.pow(10, digits - 1)), number = base + (n - 1) / digits;
        char numDigit = String.valueOf(number).charAt((n - 1) % digits);
        return numDigit - '0';
    }
}
