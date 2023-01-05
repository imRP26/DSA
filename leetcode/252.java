/*
 * https://leetcode.com/problems/meeting-rooms/
 */



/*
 * Sorting on the basis of end-time - gives us lesser residual headache
 */ 
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1])
                return false;
        }
        return true;
    }
}
