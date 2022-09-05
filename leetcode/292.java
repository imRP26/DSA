/*
 * https://leetcode.com/problems/nim-game/
 */



// A pattern can be easily observed
class Solution {
    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }
}
