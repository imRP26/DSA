/*
 * https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/
 */



/*
 * Approach (and code) from this Youtube video -> 
 * https://www.youtube.com/watch?v=i6OZgQOc8oE
 */
class Solution {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        int res = 0, n = tiles.length;
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        int[] prefixsum = new int[n];
        for (int i = 0; i < n; i++) {
            prefixsum[i] = tiles[i][1] - tiles[i][0] + 1;
            prefixsum[i] += (i > 0) ? prefixsum[i - 1] : 0;
        }
        for (int i = 0; i < n; i++) {
            int low = i, high = n - 1, j = i - 1, endpos = tiles[i][0] + carpetLen - 1;
            // The j'th index will give the tiles that can be fully covered by the carpet
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (tiles[mid][1] <= endpos) {
                    j = mid;
                    low = mid + 1;
                }
                else
                    high = mid - 1;
            }
            int len = 0;
            if (j != i - 1) {
                len += prefixsum[j];
                if (i > 0)
                    len -= prefixsum[i - 1];
            }
            if (j + 1 < n)
                len += Math.max(0, endpos - tiles[j + 1][0] + 1);
            res = Math.max(res, len);
        }
        return res;
    }
}
