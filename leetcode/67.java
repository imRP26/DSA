/*
 * https://leetcode.com/problems/add-binary/
 */



/*
 * Binary Full Adder Logic (from GATE-CSE!! :) )
 * SUM = (A) XOR (B) XOR (inputCarry)
 * CARRY = (A AND B) OR (B AND inputCarry) OR (inputCarry AND A)
 */
class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1, carry = 0, sum, d1, d2;
        String result = "";
        while (i >= 0 && j >= 0) {
            d1 = (a.charAt(i--) - '0');
            d2 = (b.charAt(j--) - '0');
            sum = d1 ^ d2 ^ carry;
            result = (sum == 0) ? '0' + result : '1' + result;
            carry = (d1 & d2) | (d2 & carry) | (carry & d1); 
        }
        while (i >= 0) {
            d1 = (a.charAt(i--) - '0');
            sum = d1 ^ carry;
            result = (sum == 0) ? '0' + result : '1' + result;
            carry = (carry & d1);
        }
        while (j >= 0) {
            d2 = (b.charAt(j--) - '0');
            sum = d2 ^ carry;
            result = (sum == 0) ? '0' + result : '1' + result;
            carry = (carry & d2);
        }   
        if (carry == 1)
            result = '1' + result;
        return result;
    }
}
