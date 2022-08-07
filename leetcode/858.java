/*
 * https://leetcode.com/problems/mirror-reflection/
 */



/*
 * Refer to the approach given in the following link :-
 * https://leetcode.com/problems/mirror-reflection/discuss/2377070/Pseudocode-Explain-Why-Odd-and-Even-Matter
 * Also, refer to this video :- https://www.youtube.com/watch?v=-6lCRlY1ocw
 */ 
class Solution {
    public int mirrorReflection(int p, int q) {
        while (p % 2 == 0 && q % 2 == 0) {
            p /= 2;
            q /= 2;
        }
        if (p % 2 == 0 && q % 2 != 0)
            return 2;
        if (p % 2 != 0 && q % 2 == 0)
            return 0;
        return 1;
    }
}
