/*
 * Question Link -> https://binarysearch.com/problems/Furthest-From-Origin
 */



/*
 * Traverse through the whole input string, finding out the number of 'L's and 
 * 'R's encountered, also maintain a counter for the number of '?'s seen.
 * Since we need to know how far we can be from origin after traversing the 
 * entire input string (and not in any intermediate position), so we can just 
 * (greedily) add the number of '?'s to whichever is > out of the number of 'L's 
 * and 'R's.
 */
class Solution {
    public int solve(String s) {
        int position = 0, numq = 0;
        for (char c : s.toCharArray()) {
            if (c == 'R')
                position++;
            else if (c == 'L')
                position--;
            else
                numq++;
        }
        return Math.abs(position) + numq;
    }
}
