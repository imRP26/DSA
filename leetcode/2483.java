/*
 * https://leetcode.com/problems/minimum-penalty-for-a-shop/
 */



/*
 * Approach using Prefix and Suffix Sum Arrays!
 */
class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length(), res = -1, minPenalty = Integer.MAX_VALUE;
        int[] nPrefix = new int[n], ySuffix = new int[n];
        for (int i = 0; i < n; i++) {
            nPrefix[i] = (customers.charAt(i) == 'N') ? 1 : 0;
            nPrefix[i] += i > 0 ? nPrefix[i - 1] : 0;
        }
        for (int i = n - 1; i >= 0; i--) {
            ySuffix[i] = (customers.charAt(i) == 'Y') ? 1 : 0;
            ySuffix[i] += i < n - 1 ? ySuffix[i + 1] : 0;
        }
        for (int i = 0; i < n; i++) {
            int penalty = ySuffix[i] + ((i > 0) ? nPrefix[i - 1] : 0);
            res = penalty < minPenalty ? i : res;
            minPenalty = Math.min(penalty, minPenalty);
        }
        return nPrefix[n - 1] < minPenalty ? n : res;
    }
}



/*
 * Approach 1 from LC Official Editorial!
 */
class Solution {
    public int bestClosingTime(String customers) {
        int currPenalty = 0;
        for (char c : customers.toCharArray())
            currPenalty += (c == 'Y') ? 1 : 0;
        int minPenalty = currPenalty, earliestHour = 0;
        for (int i = 0; i < customers.length(); i++) {
            currPenalty += (customers.charAt(i) == 'Y') ? -1 : 1;
            if (currPenalty < minPenalty) {
                minPenalty = currPenalty;
                earliestHour = i + 1;
            }
        }
        return earliestHour;
    }
}



/*
 * Approach 2 from LC Official Editorial!
 */
class Solution {
    public int bestClosingTime(String customers) {
        int currPenalty = 0, minPenalty = 0, res = 0;
        for (int i = 0; i < customers.length(); i++) {
            currPenalty += (customers.charAt(i) == 'Y') ? -1 : 1;
            if (currPenalty < minPenalty) {
                minPenalty = currPenalty;
                res = i + 1;
            }
        }
        return res;
    }
}
