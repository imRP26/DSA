/*
 * https://leetcode.com/problems/excel-sheet-column-title/
 */



/*
 * Approach from -> 
 * https://leetcode.com/problems/excel-sheet-column-title/solutions/441430/detailed-explanation-here-s-why-we-need-n-at-first-of-every-loop-java-python-c/
 */
class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber -= 1;
            int rem = columnNumber % 26;
            sb.append((char)(rem + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }
}
