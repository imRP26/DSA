/*
 * https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
 */



/*
 * My Naive, useless, complicated solution!
 */
class Solution {
    public int numberOfBeams(String[] bank) {
        int rows = bank.length, columns = bank[0].length(), result = 0;
        for (int row1 = 0; row1 < rows - 1; row1++) {
            int devices1 = 0, devices2 = 0, row2 = row1 + 1;
            String b = bank[row1];
            for (int i = 0; i < columns; i++)
                devices1 += b.charAt(i) == '1' ? 1 : 0;
            if (devices1 == 0)
                continue;
            while (row2 < rows) {
                b = bank[row2];
                for (int i = 0; i < columns; i++)
                    devices2 += b.charAt(i) == '1' ? 1 : 0;
                row2++;
                if (devices2 == 0)
                    continue;
                break;
            }
            result += devices1 * devices2; 
        }
        return result;
    }
}



/*
 * Greedy Approach from LC Official Editorial!
 */
class Solution {
    public int numberOfBeams(String[] bank) {
        int result = 0, prevCount = 0;
        for (String s : bank) {
            int currCount = 0;
            for (int i = 0; i < s.length(); i++)
                currCount += s.charAt(i) == '1' ? 1 : 0;
            if (currCount > 0) {
                result += prevCount * currCount;
                prevCount = currCount;
            }
        }
        return result;
    }
}
