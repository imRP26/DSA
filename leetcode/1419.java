/*
 * https://leetcode.com/problems/minimum-number-of-frogs-croaking/
 */



/*
 * Somewhat approach of Difference Arrays from -> 
 * https://leetcode.com/problems/minimum-number-of-frogs-croaking/solutions/586653/c-python-java-lucid-code-with-documened-comments-visualization/
 */
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0, count = 0, result = 0;
        for (char ch : croakOfFrogs.toCharArray()) {
            switch(ch) {
                case 'c' : c++;
                           count++;
                           break;
                case 'r' : r++;
                           break;
                case 'o' : o++;
                           break;
                case 'a' : a++;
                           break;
                case 'k' : k++;
                           count--;
            }
            result = Math.max(result, count);
            if (c < r || r < o || o < a || a < k)
                return -1;
        }
        return (count == 0 && c == r && r == o && o == a && a == k) ? result : -1;
    }
}
