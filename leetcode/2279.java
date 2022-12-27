/*
 * https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/
 */



/*
 * Naive Approach, but got AC
 */ 
class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] remRocks = new int[n];
        for (int i = 0; i < n; i++)
            remRocks[i] = capacity[i] - rocks[i];
        Arrays.sort(remRocks);
        if (additionalRocks < remRocks[0])
            return 0;
        for (int i = 0; i < n; i++) {
            if (remRocks[i] > additionalRocks)
                return i;
            additionalRocks -= remRocks[i];
        }
        return n;
    }
}
