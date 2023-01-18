/*
 * https://leetcode.com/problems/maximum-product-subarray/
 */



/*
 * Approach 2 (Similar to Kadane's algorithm) from 
 * https://leetcode.com/problems/maximum-product-subarray/solutions/738529/maximum-product-subarray/
 */
class Solution {
    public int maxProduct(int[] nums) {
        int minSoFar = 1, maxSoFar = 1, result = Integer.MIN_VALUE;
        for (int num : nums) {
            int a = minSoFar, b = maxSoFar;
            minSoFar = Math.min(num, Math.min(a * num, b * num));
            maxSoFar = Math.max(num, Math.max(a * num, b * num));
            result = Math.max(result, maxSoFar);
        }
        return result;
    }
}
