import java.util.*;

/*
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 */



/*
 * Approach 1 from here :- 
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/solution/
 * The space complexity of the sorting algorithm depends on the implementation 
 * of each programming language. For instance, in Java, the Arrays.sort() for 
 * primitives is implemented as a variant of the quicksort algorithm whose space 
 * complexity is O(log N). 
 * In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort 
 * and Insertion Sort with the worst-case space complexity of O(log N). Thus the 
 * use of inbuilt sort() function adds O(log N) to space complexity.
 * The result for each 'index' will be stored in 'dp' and 'index' can have the 
 * values from 0 to N, thus the space required is O(N). Also, stack space in 
 * recursion = the maximum number of active functions. In the scenario where 
 * none of the jobs is scheduled, the function call stack will be of size N.
 * The total space complexity is therefore equal to O(N).
 */
class Solution1 {

	int findNextJob(int[] startTime, int lastEndingTime) {
		int low = 0, high = startTime.length - 1, nextIndex = startTime.length;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (startTime[mid] >= lastEndingTime) {
				nextIndex = mid;
				high = mid - 1;
			}
			else
				low = mid + 1;
		}
		return nextIndex;
	}

	int findMaxProfit(List<List<Integer>> jobs, int[] startTime, int numJobs, int index, int[] dp) {
		if (index == numJobs)
			return 0;
		if (dp[index] != -1)
			return dp[index];
		int nextIndex = findNextJob(startTime, jobs.get(index).get(1));
		int maxProfit = Math.max(findMaxProfit(jobs, startTime, numJobs, index + 1, dp), 
								 findMaxProfit(jobs, startTime, numJobs, nextIndex, dp) + 
								 jobs.get(index).get(2));
		return dp[index] = maxProfit;
	}

	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		/*
         * dp[i] = maximum profit 1 can get from the jobs at indices from i 
         * to the end of the array.
         */
		int numJobs = startTime.length;
		int[] dp = new int[numJobs];
		Arrays.fill(dp, -1);
		
		// storing all the jobs' details in 1 list and sorting them according to startTime
		List<List<Integer>> jobs = new ArrayList<>();
		for (int i = 0; i < numJobs; i++) {
			List<Integer> currJob = new ArrayList<>();
			currJob.add(startTime[i]);
			currJob.add(endTime[i]);
			currJob.add(profit[i]);
			jobs.add(currJob);
		}
		jobs.sort(Comparator.comparingInt(a -> a.get(0)));
		for (int i = 0; i < numJobs; i++)
			startTime[i] = jobs.get(i).get(0);
		
		return findMaxProfit(jobs, startTime, numJobs, 0, dp);
	}
}



/*
 * Approach 2 from here :- 
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/solution/
 */
class Solution2 {
    
}
