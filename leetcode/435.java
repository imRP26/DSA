/*
 * https://leetcode.com/problems/non-overlapping-intervals/
 */



/*
 * Approach 5 from 
 * https://leetcode.com/problems/non-overlapping-intervals/solutions/127610/non-overlapping-intervals/
 * Question yourself as to why sorting is done wrt end-time of an interval!
 */ 
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length, result = 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && intervals[i][1] > intervals[j][0]) {
                result++;
                j++;
            }
            i = j - 1;
        }
        return result;
    }
}
