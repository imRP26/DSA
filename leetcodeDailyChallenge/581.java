import java.util.Arrays;

/*
 * Question Link -> 
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
*/



// Solution using input array cloning - naive
class Solution1 {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length, i = 0, low = -1, high = -1, temp[] = nums.clone();
        Arrays.sort(temp);
        while (i < n && nums[i] == temp[i])
            i++;
        if (i < n)
            low = i;
        i = n - 1;
        while (i >= 0 && nums[i] == temp[i])
            i--;
        if (i >= 0)
            high = i;
        if (low == -1 && high == -1)
            return 0;
        return (high - low + 1);
    }
}



// Efficient solution
class Solution2 {
    public int findUnsortedSubarray(int[] nums) {
        int i, n = nums.length, high = -1, low = -1, maxElement = nums[0], 
            minElement = nums[n - 1];
		for (i = 1; i < n; i++) {
			if (maxElement <= nums[i]) // normal sorted condition, expected
				maxElement = nums[i];
			else // breaks the sortedness of the array, need to find the extreme end
				high = i;
		}
		for (i = n - 2; i >= 0; i--) {
			if (minElement >= nums[i]) // normal sorted condition, expected
				minElement = nums[i];
			else // breaks the sortedness of the array, need to find the extreme end
				low = i;
		}
        if (low == -1 && high == -1)
            return 0;
		return (high - low + 1);
    }
}