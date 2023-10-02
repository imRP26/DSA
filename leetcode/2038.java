/*
 * https://leetcode.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/
 */



/*
 * Don't be concerned about the number of A's intermediate between some B's. If there's <= 2 
 * consecutive A's they can anyways not be taken. If at all there's too many A's or B's in between, 
 * then also they can be taken only till the number >= 3.
 */
class Solution {
    public boolean winnerOfGame(String colors) {
        int numa = 0, numb = 0;
        for (int i = 1; i < colors.length() - 1; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1) && colors.charAt(i) == colors.charAt(i + 1)) {
                numa += (colors.charAt(i) == 'A') ? 1 : 0;
                numb += (colors.charAt(i) == 'B') ? 1 : 0;
            }
        }
        return (numa > numb);
    }
}
