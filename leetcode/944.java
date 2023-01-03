/*
 * https://leetcode.com/problems/delete-columns-to-make-sorted/
 */



/*
 * Simple, Ad-Hoc
 */
class Solution {
    public int minDeletionSize(String[] strs) {
        int result = 0, strlen = strs[0].length(), numStrs = strs.length;
        for (int j = 0; j < strlen; j++) {
            for (int i = 1; i < numStrs; i++) {
                char c1 = strs[i - 1].charAt(j), c2 = strs[i].charAt(j);
                if (c1 > c2) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }
}
