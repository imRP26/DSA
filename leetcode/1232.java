/*
 * https://leetcode.com/problems/check-if-it-is-a-straight-line/
 */



/*
 * Approach from LC Official Editorial
 */
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int dx01 = coordinates[1][0] - coordinates[0][0], dy01 = coordinates[1][1] - coordinates[0][1];
        for (int i = 2; i < coordinates.length; i++) {
            int dx = coordinates[i][0] - coordinates[0][0], dy = coordinates[i][1] - coordinates[0][1];
            if (dy01 * dx != dx01 * dy)
                return false;
        }
        return true;
    }
}
