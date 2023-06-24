/*
 * https://leetcode.com/problems/construct-the-longest-new-string/
 */



/*
 * Just follow the hints given to this question by LC! :(
 */
class Solution {
    public int longestString(int x, int y, int z) {
        // case - 1 -> (AB)*AABBAABB...
        int len1 = 2 * z + 4 * Math.min(x, y);
        if (x > y)
            len1 += 2;
        // case - 2 -> BB(AB)*AABBAABB...
        int len2 = 2 + 2 * z + 4 * Math.min(x, y - 1);
        if (x > y - 1)
            len2 += 2;
        return Math.max(len1, len2);
    }
}
