/*
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */



/*
 * Sorting - Approach 2 from 
 * https://leetcode.com/problems/longest-consecutive-sequence/solutions/127576/longest-consecutive-sequence/?orderBy=most_votes
 */ 
class Solution1 {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int longestStreak = 1, currentStreak = 1, prevIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[prevIndex] == 1)
                longestStreak = Math.max(longestStreak, ++currentStreak);
            else if (nums[i] - nums[prevIndex] > 1)
                currentStreak = 1;
            prevIndex = i;
        }
        return longestStreak;
    }
}



/*
 * Approach 3 (HashSet and Intelligent Sequence Building) from 
 * https://leetcode.com/problems/longest-consecutive-sequence/solutions/127576/longest-consecutive-sequence/?orderBy=most_votes
 */
// The HashSet is to allow for lookups of those numbers that are 1 < the current number, meaning, 
// in every outer iteration, its assumed that the current number is the start of a sequence.
class Solution2 {
	public int longestConsecutive(int[] nums) {
		Set<Integer> numSet = new HashSet<>();
		for (int num : nums)
			numSet.add(num);
		int longestStreak = 0;
		for (int num : numSet) {
			if (numSet.contains(num - 1))
				continue;
			int currentNum = num, currentStreak = 1;
			while (numSet.contains(currentNum + 1)) {
				currentStreak++;
				currentNum++;
			}
			longestStreak = Math.max(longestStreak, currentStreak);
		}
		return longestStreak;
	}
}
