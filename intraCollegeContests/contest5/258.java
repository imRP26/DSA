/*
 * Question Link -> https://leetcode.com/problems/add-digits/
*/



// Simple Simulation
class Solution1 {
    public int addDigits(int num) {
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }
}



/*
 * A bit of an observation reveals a cyclic pattern wherein for every 9 numbers, 
 * we get the final result from 1 to 9.
 */
class Solution2 {
    public int addDigits(int num) {
        if (num == 0)
            return 0;
        if (num % 9 == 0)
            return 9;
        return num % 9;
    }
}
