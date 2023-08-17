/*
 * https://leetcode.com/problems/trapping-rain-water/
 * Refer to Striver's videos!
*/



// Solution 1 -> Using 2 auxiliary arrays, Space Complexity = O(N)
class Solution1 {
    public int trap(int[] height) {
        /*
		 * We begin by calculating the amount of water that can be stored in 
         * a container at an index.
		 * For a particular index, the height of water that can be stored is 
         * bound by the maximum height of the container on both the left and 
         * the right sides. 
         * And since we're telling that this particular index itself has to 
         * store water so we find the minimum height between maxLeft and 
         * maxRight and then subtract the height of the container found at 
         * that particular index itself.
		*/
		int n = height.length, i, maxHeight = height[0], result = 0;
		int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
		for (i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxHeight, height[i - 1]);
            maxHeight = Math.max(maxHeight, height[i]);
		}
		maxHeight = height[n - 1];
		for (i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxHeight, height[i + 1]);
			maxHeight = Math.max(maxHeight, height[i]);
		}
		for (i = 0; i < n; i++) {
			int h = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (h > 0)
				result += h;
		}
		return result;
    }
}



// Solution 2 - using 2 pointers, space complexity = O(1)
class Solution {
    public int trap(int[] height) {
        int n = height.length, left = 0, right = n - 1, maxLeft = height[0], 
			maxRight = height[n - 1], result = 0, index;
		if (maxLeft <= maxRight)
			index = ++left;
		else
			index = --right;
		while (left < right) {
            // how much water can be stored in a container
			int h = Math.min(maxLeft, maxRight) - height[index];
			result += Math.max(0, h);
			maxLeft = Math.max(maxLeft, height[left]);
			maxRight = Math.max(maxRight, height[right]);
			if (maxLeft <= maxRight)
				index = ++left;
			else
				index = --right;
		}
		return result;
    }
}
