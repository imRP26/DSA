/*
 * https://leetcode.com/problems/minimum-speed-to-arrive-on-time/
 */



/*
 * Approach of Binary Search from LC Official Editorial!
 * Since trains can only leave in integral hours, its important to take Math.ceil(), except for the 
 * last train ride.
 */
class Solution {

    private boolean isPossible(int[] dist, int v, double hour) {
        int n = dist.length;
        double time = 0.0;
        for (int i = 0; i < n; i++) {
            double t = (double)dist[i] / (double)v;
            time += (i == n - 1) ? t : Math.ceil(t);
        }
        return time <= hour;
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int low = 1, high = (int)1e7, res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(dist, mid, hour)) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res;
    }
}
