/*
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 */



/*
 * Simple Approach of Binary Search!!
 */ 
class Solution {

    private boolean isConfigPossible(int[] weights, int days, int shipCapacity) {
        int numDays = 1, val = 0;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] > shipCapacity)
                return false;
            if (val + weights[i] <= shipCapacity)
                val += weights[i];
            else {
                numDays += 1;
                val = weights[i];
            }
        }
        return numDays <= days;
    }

    public int shipWithinDays(int[] weights, int days) {
        int low = 1, high = 25000000, result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isConfigPossible(weights, days, mid)) {
                result = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return result;
    }
}
