/*
 * Question Link -> 
 * https://leetcode.com/problems/minimum-moves-to-reach-target-score/
*/



/*
 * A bit of being Greedy and a bit of intuition!!
 * We go from target to 1, this serves us 2 purposes :- 
 * (1) We know exactly when to subtract 1 and when to half by 2 - this gets a 
 *     bit trickier when we go the opposite way.
 * (2) We get more bang for our buck when we go from target to 1 - when a 
 *     number having a greater magnitude is halved, we go further towards 1 
 *     than if we halve a number with a lower magnitude.
*/

// Iterative Approach
class Solution1 {
    public int minMoves(int target, int maxDoubles) {
        int moves = 0;
        while (target > 1) {
            if (maxDoubles == 0)
                return (moves + target - 1);
            if (maxDoubles > 0 && target % 2 == 0) {
                target /= 2;
                maxDoubles--;
            }
            else
                target--;
            moves++;
        }
        return moves;
    }
}



// Recursive Approach
class Solution2 {
    
    public int recurse(int target, int maxDoubles, int moves) {
        if (target == 1)
            return moves;
        if (maxDoubles == 0)
            return (moves + target - 1);
        if (target % 2 == 0)
            return recurse(target / 2, maxDoubles - 1, moves + 1);
        return recurse(target - 1, maxDoubles, moves + 1);
    }
    
    public int minMoves(int target, int maxDoubles) {
        return recurse(target, maxDoubles, 0);
    }
}
