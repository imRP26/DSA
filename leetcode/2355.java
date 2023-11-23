/*
 * https://leetcode.com/problems/maximum-number-of-books-you-can-take/
 */



/*
 * Approach of Brute Force from -> 
 * https://leetcode.com/problems/maximum-number-of-books-you-can-take/solutions/3345604/java-editorial-level-explanation-o-n-dynamic-programming-monotonic-stack-brute-force/
 */
class Solution {
    public long maximumBooks(int[] books) {
        int n = books.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            int prev = books[i];
            long numBooks = prev;
            for (int j = i - 1; j >= 0; j--) {
                int curr = Math.min(books[j], prev - 1);
                if (curr < 1)
                    break;
                numBooks += curr;
                prev = curr;
            }
            res = Math.max(res, numBooks);
        }
        return res;
    }
}



/*
 * Approach of 
 */
