import java.util.*;

/*
 * https://leetcode.com/problems/split-array-into-consecutive-subsequences/
 */



/*
 * Reference -> Approach 1 from here :- 
 * https://leetcode.com/problems/split-array-into-consecutive-subsequences/solution/
 * TC = O(NlogN), SC = O(N)
 */
class Solution1 {
	public boolean isPossible(int[] nums) {
		PriorityQueue<int[]> subs = new PriorityQueue<>((int[] sub1, int[] sub2) -> {
			if (sub1[1] == sub2[1])
				return ((sub1[1] - sub1[0]) - (sub2[1] - sub2[0]));
			return (sub1[1] - sub2[1]);
		});
		for (int num : nums) {
			// Case 1 - Removal of non-qualifying subsequences
			while (!subs.isEmpty() && subs.peek()[1] + 1 < num) {
				int[] currSub = subs.poll();
				int subLength = currSub[1] - currSub[0] + 1;
				if (subLength < 3)
					return false;
			}
			// Case 2 - Creating of a new subsequence
			if (subs.isEmpty() || subs.peek()[1] == num)
				subs.add(new int[] {num, num});
			else {
				// Case 3 - Addition of num to an existing subsequence
				int[] currSub = subs.poll();
				subs.add(new int[] {currSub[0], num});
			}
		}
		while (!subs.isEmpty()) {
			int[] currSub = subs.poll();
			if (currSub[1] - currSub[0] + 1 < 3)
				return false; 
		}
		return true;
	}
}



/*
 * Approach 2 from here :-
 * https://leetcode.com/problems/split-array-into-consecutive-subsequences/solution/
 * TC = O(N), SC = O(N)
 * Personally, the most intuitive solution!!
 */
class Solution2 {
	public boolean isPossible(int[] nums) {
		Map<Integer, Integer> subs = new HashMap<>();
		Map<Integer, Integer> freq = new HashMap<>();
		for (int num : nums)
			freq.put(num, freq.getOrDefault(num, 0) + 1);
		for (int num : nums) {
			// Case 1 : When num is already a part of a valid subsequence
			if (freq.get(num) == 0)
				continue;
			// Case 2 : If a valid subsequence exists with last element = num - 1
			if (subs.getOrDefault(num - 1, 0) > 0) {
				subs.put(num - 1, subs.getOrDefault(num - 1, 0) - 1);
				subs.put(num, subs.getOrDefault(num, 0) + 1);
			}
            // Case 3 : If num can be considered to be the start of a valid subsequence
			else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
				subs.put(num + 2, subs.getOrDefault(num + 2, 0) + 1);
				freq.put(num + 1, freq.getOrDefault(num + 1, 0) - 1);
				freq.put(num + 2, freq.getOrDefault(num + 2, 0) - 1);
			}
            // Case 4 : No valid subsequence is possible with num
			else
				return false;
			freq.put(num, freq.get(num) - 1);
		}
		return true;
	}
}



/*
 * Approach 3 from here :- 
 * https://leetcode.com/problems/split-array-into-consecutive-subsequences/solution/
 * TC = O(), SC = O()
 * Leaving this for some other day, Approach 2 should be preferred, this approach 
 * just looks too complex!!
 */

