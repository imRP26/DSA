/*
 * Question Link -> https://leetcode.com/problems/excel-sheet-column-number/
 */



// Simple Observation based
class Solution {
    public int titleToNumber(String columnTitle) {
        int result = 0, powVal = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            int diff = columnTitle.charAt(i) - 'A' + 1;
            result += diff * powVal;
            powVal *= 26;
        }
        return result;
    }
}
