import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/merge-intervals/
 */



/*
 * Sort the given array by the start points.
 * Then either expand the considered interval or expand a new one.
 */
class Solution1 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1])
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {
                newInterval = interval;
                result.add(newInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}



// Another (more believable?) way
class Solution2 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0], end = intervals[0][1];
        for (int[] interval : intervals) {
            if (interval[0] <= end)
                end = Math.max(end, interval[1]);
            else {
                result.add(new int[] {start, end});
                start = interval[0];
                end = interval[1];
            }
        }
        result.add(new int[] {start, end});
        return result.toArray(new int[0][]);
    }
}
