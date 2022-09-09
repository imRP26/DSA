import java.util.*;

/*
 * https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/
 */



/*
 * Refer from this link :-
 * https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/discuss/2551895/JAVA-oror-Easy-Solution-With-Explanation-oror-93-Faster-Code
 * Need to be able to solve such questions on own
 */
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> (a[0] == b[0]) ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]));
        int n = properties.length, max = properties[n - 1][1], result = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (properties[i][1] < max)
                result++;
            else
                max = properties[i][1];
        }
        return result;
    }
}
