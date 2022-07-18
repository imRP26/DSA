/*
 * Question Link -> https://binarysearch.com/problems/N-Rooks
 */



// Here, all the rooks are considered to be the same
class Solution {
    public int solve(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++)
            result *= i;
        return result;
    }
}
