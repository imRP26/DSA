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



/*
 * Approach from LC Official Editorial!
 * Sorting greedily wrt the end-time of an interval - this ensures that not many intervals 
 * afterwards will have conflicts!
 */
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        //Arrays.sort(intervals, (a, b) -> (a[1] == b[1]) ? (a[0] - b[0]) : (a[1] - b[1]));
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return a[1] - b[1];
        });
        int res = 0, t = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < t)
                res++;
            else
                t = intervals[i][1];
        }
        return res;
    }
}
