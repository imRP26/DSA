/*
 * https://leetcode.com/problems/insert-interval/
 */



/*
 * This is just bad!!
 * Copied Approach from :(
 * https://leetcode.com/problems/insert-interval/solutions/21602/short-and-straight-forward-java-solution/
 */
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0, n = intervals.length;
		List<int[]> result = new LinkedList<>();
		while (i < n && intervals[i][1] < newInterval[0])
			result.add(intervals[i++]);
		while (i < n && intervals[i][0] <= newInterval[1]) {
			newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
			newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
			i++;
		}
		result.add(newInterval);
		while (i < n)
			result.add(intervals[i++]);
		return result.toArray(new int[result.size()][]);
    }
} 
  