import java.util.*;

/*
 * https://leetcode.com/problems/minimum-number-of-refueling-stops/
 */



/*
 * Approach 2 (PriorityQueue) described here -> 
 * https://leetcode.com/problems/minimum-number-of-refueling-stops/solution/
 * TC = O(NlogN), SC = O(N)
 */
class Solution1 {
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		int numStops = 0, prevLocation = 0;
		for (int[] station : stations) {
			int location = station[0], capacity = station[1];
			startFuel -= location - prevLocation;
            /*
             * The stations whose fuel capacities have been added to the maxHeap 
             * are those that have been covered by startFuel, but not used up 
             * till now. So now, when its observed that in retrospect, refuelling 
             * should have been done, refuelling is done greedily, i.e., that 
             * station is chosen for refuelling that gives the "max bang for my 
             * buck", i.e., the station with the max. fuel capacity -> now this 
             * enables 2 things - 1 that I can travel max distance by "greedily" 
             * choosing the station with the max. fuel capacity and in return, 
             * I stop the minimum number of times.
             */
			while (!maxHeap.isEmpty() && startFuel < 0) {		
				startFuel += maxHeap.poll();
				numStops++;
			}
			if (startFuel < 0)
				return -1;
			maxHeap.offer(capacity);
			prevLocation = location;
		}
		startFuel -= target - prevLocation;
		while (!maxHeap.isEmpty() && startFuel < 0) {
			startFuel += maxHeap.poll();
			numStops++;
		}
		return (startFuel < 0) ? -1 : numStops;
	}
}



/*
 * DP Approach, TC = O(N^2), SC = O(N^2)
 */
class Solution2 {
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		int numStations = stations.length;
		int[][] dp = new int[numStations + 1][numStations + 1];
		/*
		 * dp[i][j] = maximum distance reached if refuelling is done at 'j' fuelstops 
		 *            from a total of i stops
		 */
		for (int i = 0; i <= numStations; i++)
			dp[i][0] = startFuel;
		/*
		 * No point in filling the 1st row since dp[0][j] means that refuelling is done 
		 * at 'j' stations out of 0 stations, which doesn't make any sense for j > 0.
		 */
		for (int i = 1; i <= numStations; i++) {
			for (int j = 1; j <= i; j++) {
				/*
				 * Case 1 - No Refuelling is done at the current station
				 * Now, maximum distance reached = maximum distance covered 
				 * from previous state.
				 */
				dp[i][j] = dp[i - 1][j];
				/*
				 * Case 2 - Refuelling is done at the current station
				 * Need to check if this station can be reached at through 
				 * previous fuelCapacity
				 */
				if (dp[i - 1][j - 1] >= stations[i - 1][0])
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + stations[i - 1][1]);
			}
		}
		for (int i = 0; i <= numStations; i++) {
			if (dp[numStations][i] >= target)
				return i;
		}
		return -1;
	}
}
