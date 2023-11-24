/*
 * https://leetcode.com/problems/maximum-score-of-spliced-array/
 */



/*
 * Since we can swap only once, consider 2 cases :-
 * (1) When nums1[l ... r] is to be swapped with nums2[l ... r] -> compute an array comprising of the 
 *     values of nums2[i] - nums1[i] for all i -> and then perform Kadane's to determine the appropriate 
 *     subarray to be swapped!
 * (2) Vice-Versa!
 */
class Solution {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int n = nums1.length, sum = 0, maxSum12 = 0, maxSum21 = 0;
        int[] profit = new int[n];
        for (int i = 0; i < n; i++)
            profit[i] = nums2[i] - nums1[i];
        for (int i = 0; i < n; i++) {
            sum += profit[i];
            maxSum12 = Math.max(maxSum12, sum);
            sum = Math.max(sum, 0);
        }
        for (int i = 0; i < n; i++)
            maxSum12 += nums1[i];
        for (int i = 0; i < n; i++)
            profit[i] *= -1;
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += profit[i];
            maxSum21 = Math.max(maxSum21, sum);
            sum = Math.max(sum, 0);
        }
        for (int i = 0; i < n; i++)
            maxSum21 += nums2[i];
        return Math.max(maxSum12, maxSum21);
    }
}
