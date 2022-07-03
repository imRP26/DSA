import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/summary-ranges/
 */



// Simulation
class Solution1 {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0)
            return result;
		int i, startPos = 0;
		String temp = Integer.toString(nums[0]);
		for (i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1] + 1) {
                if (startPos < i - 1)
                    temp += "->" + Integer.toString(nums[i - 1]);
				result.add(temp);
                startPos = i;
				temp = Integer.toString(nums[i]);
			}
		}
		if (i >= 2 && nums[i - 1] == nums[i - 2] + 1)
			temp += "->" + Integer.toString(nums[i - 1]);
		result.add(temp);
		return result;
    }
}



// Different representation for a string
class Solution2 {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            /*
             * Will there be any overflow when nums[i + 1] = Integer.MAX_VALUE 
             * and nums[i] = -1?
             * No, since the difference is being evaluated in an expression and 
             * the value isn't stored in the code, so even if the intermediate 
             * value exceeds the range of integer, it will get automatically 
             * stored in a larger data type, just for the sake of evaluating the 
             * expression.
             */
            while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1])
                i++;
            if (start != nums[i])
                result.add("" + start + "->" + nums[i]);
            else
                result.add("" + start);
        }
        return result;
    }
}
