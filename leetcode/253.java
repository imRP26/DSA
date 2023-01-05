/*
 * https://leetcode.com/problems/meeting-rooms-ii/
 */



/*
 * Approach from 
 * https://leetcode.com/problems/meeting-rooms-ii/solutions/168762/meeting-rooms-ii/
 */
class Solution1 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        // sorting according to interval start time, sinze is natural for us to 1st of all look for a meeting room for a metting that starts at 9 AM, than at 5 PM, for instance.
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = intervals.length;
        /*
         * creating imaginary rooms, as soon as a room becomes free, we remove that room the PQ, meaning we just remove that particular element from the minPQ.
         */
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(n, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });
        minPQ.add(intervals[0][1]);
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= minPQ.peek())
                minPQ.poll();
            minPQ.add(intervals[i][1]);
        }
        return minPQ.size();
    }
}



/*
 * Bheja Fry!!
 * Approach 2 from 
 * https://leetcode.com/problems/meeting-rooms-ii/solutions/168762/meeting-rooms-ii/
 */
class Solution2 {
	public int minMeetingRooms(int[][] intervals) {
		int n = intervals.length;
		int[] startTimes = new int[n], endTimes = new int[n];
		for (int i = 0; i < n; i++) {
			startTimes[i] = intervals[i][0];
			endTimes[i] = intervals[i][1];
		}
		Arrays.sort(endTimes);
		Arrays.sort(startTimes);
		int startPtr = 0, endPtr = 0, result = 0;
		while (startPtr < n) {
			if (startTimes[startPtr] >= endTimes[endPtr]) {
				result--;
				endPtr++;
			}
			result++;
			startPtr++;
		}
		return result;
	}
}
