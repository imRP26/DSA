/*
 * Question Link -> 
 * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
*/



// Dirt-Easy Implementation
class Solution {
    public int numberOfSteps(int num) {
        int steps = 0;
        while (num > 0) {
            if (num % 2 == 0)
                num /= 2;
            else
                num--;
            steps++;
        }
        return steps;
    }
}
