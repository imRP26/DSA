/*
 * https://leetcode.com/problems/meeting-rooms-iii/
 */



/*
 * Approach from 
 * https://leetcode.com/problems/meeting-rooms-iii/solutions/2527347/java-o-nlogn-heaps-and-sort-with-comments/
 * https://leetcode.com/problems/meeting-rooms-iii/solutions/2535735/detailed-explanation-simulation-using-min-heaps-c-clean-code/
 */
class Solution1 {
	public int mostBooked(int n, int[][] meetings) {
		// sorting the 'meetings' array by start time of each meeting
		Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
		var freeRooms = new PriorityQueue<Integer>();
		for (int i = 0; i < n; i++)
			freeRooms.offer(i);
		// {meetingRoom, roomTaken} -> Sort by end time and by room number
		var runningMeetings = new PriorityQueue<int[]>(
			(a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		var meetingRoomCount = new int[n];
		for (int[] meeting : meetings) {
			// return room to availableRooms if the meeting has ended
			while (!runningMeetings.isEmpty() && runningMeetings.peek()[0] <= meeting[0])
				freeRooms.offer(runningMeetings.poll()[1]);
			var delayedStart = meeting[0];
			/*
			 * when there are no available rooms, then we need to adjust the next 
			 * meeting start time with delay
			 */
			if (freeRooms.isEmpty()) {
				var await = runningMeetings.poll();
				delayedStart = await[0];
				freeRooms.offer(await[1]);
			}
			var freeRoom = freeRooms.poll();
			meetingRoomCount[freeRoom]++;
			runningMeetings.offer(new int[] {delayedStart + meeting[1] - meeting[0], freeRoom});
		}
		var maxCountRoom = 0;
		for (int i = 1; i < n; i++) {
			if (meetingRoomCount[i] > meetingRoomCount[maxCountRoom])
				maxCountRoom = i;
		}
		return maxCountRoom;
	}
} 



/*
 * Approach from 
 * https://leetcode.com/problems/meeting-rooms-iii/solutions/2528000/java-clean-simple-one-heap/
 */

