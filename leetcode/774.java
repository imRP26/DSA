/*
 * https://leetcode.com/problems/minimize-max-distance-to-gas-station/
 */



/*
 * DP Approach (Approach 1) from 
 * https://leetcode.com/problems/minimize-max-distance-to-gas-station/solutions/127767/minimize-max-distance-to-gas-station/?orderBy=most_votes
 */
class Solution1 {
	public double minmaxGasDist(int[] stations, int k) {
		int n = stations.length;
		double[] distances = new double[n - 1];
		for (int i = 0; i < n - 1; i++)
			distances[i] = stations[i + 1] - stations[i];
		double[][] dp = new double[n - 1][k + 1];
		/*
		 * dp[n][k] = answer for adding 'k' more gas stations to the 1st 'n' intervals 
		 * 			  of stations.
		 * i'th interval = deltas[i] = stations[i + 1] - stations[i]
		 * dp[n + 1][k] => putting 'x' gas stations in the (n + 1)'th interval for a 
		 * 				   best-case distance of {deltas[n + 1] / (x + 1)}
		 */
		for (int i = 0; i <= k; i++)
			dp[0][i] = distances[0] / (i + 1);
		for (int i = 1; i < n - 1; i++) {
			for (int j = 0; j <= k; j++) {
				// adding 'j' more stations to the 1st (i - 1) intervals
				double val = Integer.MAX_VALUE;
				// when 'x' out of the 'j' stations have already been added to the previous (i - 1) intervals
				for (int x = 0; x <= j; x++) 
					val = Math.min(val, Math.max(distances[i] / (x + 1), dp[i - 1][j - x]));
				dp[i][j] = val;
			}
		}
		return dp[n - 2][k];
	}
} 



/*
 * Approach 2 (Brute Force) from 
 * https://leetcode.com/problems/minimize-max-distance-to-gas-station/solutions/127767/minimize-max-distance-to-gas-station/?orderBy=most_votes
 */
class Solution2 {
	public double minmaxGasDist(int[] stations, int k) {
		int n = stations.length;
		double[] deltas = new double[n - 1];
		for (int i = 0; i < n - 1; i ++)
			deltas[i] = stations[i + 1] - stations[i];
		int[] count = new int[n - 1];
		Arrays.fill(count, 1);
		for (int i = 0; i < k; i++) {
			// finding the interval with the largest distance
			int maxDistanceInterval = 0;
			for (int j = 0; j < n - 1; j++) {
				if (deltas[j] / count[j] > deltas[maxDistanceInterval] / count[maxDistanceInterval])
					maxDistanceInterval = j;
			}
			count[maxDistanceInterval]++;
		}
		double result = 0;
		for (int i = 0; i < n - 1; i++)
			result = Math.max(result, deltas[i] / count[i]);
		return result;
	}
} 



/*
 * Approach 3 (Heaps) from 
 * https://leetcode.com/problems/minimize-max-distance-to-gas-station/solutions/127767/minimize-max-distance-to-gas-station/?orderBy=most_votes
 */
class Solution3 {
	public double minmaxGasDist(int[] stations, int k) {
		int n = stations.length;
		PriorityQueue<int[]> pq = new PriorityQueue<>(
			(a, b) -> ((double)b[0] / b[1] < (double)a[0] / a[1]) ? -1 : 1);
		for (int i = 0; i < n - 1; i++)
			pq.add(new int[] {stations[i + 1] - stations[i], 1});
		for (int i = 0; i < k; i++) {
			int[] node = pq.poll();
			node[1]++;
			pq.add(node);
		}
		int[] node = pq.poll();
		return (double)node[0] / node[1];
	}
} 



/*
 * Approach 4 from 
 * https://leetcode.com/problems/minimize-max-distance-to-gas-station/solutions/127767/minimize-max-distance-to-gas-station/?orderBy=most_votes
 */
class Solution4 {
	
	private boolean isConfigurationPossible(double distance, int[] stations, int k) {
		int stationsUsed = 0;
		for (int i = 0; i < stations.length - 1; i++)
			stationsUsed += (int)((stations[i + 1] - stations[i]) / distance);
		return stationsUsed <= k;
	}
	
	public double minmaxGasDist(int[] stations, int k) {
		double low = 0, high = 1e8;
		while (high - low > 1e-6) {
			double mid = low + (high - low) / 2.0;
			if (isConfigurationPossible(mid, stations, k))
				high = mid;
			else
				low = mid + 1e-6;
		}
		return low;
	}
}
