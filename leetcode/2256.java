/*
 * Use Prefix Sum concept and don't forget to use long
 */
class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length, minIndex = 0;
        long totalSum = 0, minDiff = Integer.MAX_VALUE, leftAvg, rightAvg;
        if (n == 1)
            return 0;
        long[] prefixSum = new long[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++)
            prefixSum[i] = nums[i] + prefixSum[i - 1];
        totalSum = prefixSum[n - 1];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                leftAvg = prefixSum[0];
                rightAvg = (totalSum - prefixSum[0]) / (n - i - 1);
            }
            else if (i == n - 1) {
                leftAvg = prefixSum[i] / (i + 1);
                rightAvg = 0;    
            }
            else {
                leftAvg = prefixSum[i] / (i + 1);
                rightAvg = (totalSum - prefixSum[i]) / (n - i - 1);
            }
            long diff = Math.abs(rightAvg - leftAvg);
            if (diff < minDiff) {
                minDiff = diff;
                minIndex = i;
            }
        }
        return minIndex;
    }
}
