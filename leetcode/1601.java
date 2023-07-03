/*
 * https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests/
 */



/*
 * Naive Bitmasking Brute Force!
 */
class Solution {

    private int countSetBits(int n) {
        int ans = 0;
        while (n > 0) {
            if (n % 2 == 1)
                ans++;
            n /= 2;
        }
        return ans;
    }

    public int maximumRequests(int n, int[][] requests) {
        int res = 0, req = requests.length;
        int[] degree = new int[n];
        for (int bitmask = 0; bitmask < (1 << req); bitmask++) {
            Arrays.fill(degree, 0);
            int j = bitmask, i = 0;
            while (i < req) {
                if (j % 2 == 1) {
                    degree[requests[i][0]]--;
                    degree[requests[i][1]]++;
                }
                i++;
                j /= 2;
            }
            boolean flag = true;
            for (int d : degree) {
                if (d != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                //res = Math.max(res, countSetBits(bitmask));
                res = Math.max(res, Integer.bitCount(bitmask)); // Aliter Java goodie! :P
        }
        return res;
    }
}



/*
 * Backtracking using Recursion - not that difficult though but need to drill down upon 
 * computing TC accurately in the very 1st fucking go!
 */
class Solution {

    private int res = 0, req;

    private void backtrack(int[][] requests, int[] degree, int idx, int num) {
        if (idx == requests.length) {
            boolean flag = true;
            for (int d : degree) {
                if (d != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                res = Math.max(res, num);
            return;
        }
        // don't consider the current transfer
        backtrack(requests, degree, idx + 1, num);
        // consider the current transfer
        degree[requests[idx][0]]--;
        degree[requests[idx][1]]++;
        backtrack(requests, degree, idx + 1, num + 1);
        degree[requests[idx][0]]++;
        degree[requests[idx][1]]--;
    }

    public int maximumRequests(int n, int[][] requests) {
        req = requests.length;
        int[] degree = new int[n];
        backtrack(requests, degree, 0, 0);
        return res;
    }
}
