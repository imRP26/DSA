/*
 * Question Link -> https://leetcode.com/problems/container-with-most-water/
 * Refer to Neetcode's video for this if any further doubt arises
*/



// Solution using 2 pointers
class Solution {
    public int maxArea(int[] height) {
        int n = height.length, low = 0, high = n - 1, 
            result = Integer.MIN_VALUE;
		while (low < high) {
			result = Math.max(result, Math.min(height[low], height[high]) * 
                                                            (high - low));
			/*
             * Idea (properly stated) by Stefan Pochmann :-
             * (1) The widest container (using first and last line) is a good 
             * candidate, because of its width. Its water level is the height 
             * of the smaller one of first and last lines.
             * All other containers are less wide and thus would need a 
             * higher water level in order to hold more water.
             * The smaller one of first and last lines doesn't support a 
             * higher water level and can thus be safely removed from further 
             * consideration.
            */
            if (height[low] < height[high])
				low++;
			else
				high--;
		}
		return result;
    }
}
