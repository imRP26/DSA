/*
 * Question Link -> https://leetcode.com/problems/xor-queries-of-a-subarray/
 */



// Simple Prefix Sum (XOR) concept
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length, numQueries = queries.length;
        int[] prefixXor = new int[n];
        int[] result = new int[numQueries];
        prefixXor[0] = arr[0];
        for (int i = 1; i < n; i++)
            prefixXor[i] = prefixXor[i - 1] ^ arr[i];
        for (int i = 0; i < numQueries; i++) {
            result[i] = prefixXor[queries[i][1]];
            if (queries[i][0] != 0)
                result[i] ^= prefixXor[queries[i][0] - 1];
        }
        return result;
    }
}
