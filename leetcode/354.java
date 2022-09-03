import java.util.*;

/*
 * https://leetcode.com/problems/russian-doll-envelopes/
 */



/*
 * Approach 1 from Leetcode official solution
 * dp[i] = the smallest element that ends an increasing subsequence of length (i + 1)
 * Whenever a new element 'e' is encountered, binary search is done inside dp to 
 * find the largest index 'i' such that 'e' can end that subsequence.
 * Then dp[i] is updated with 'e'.
 * Consider an input [[1, 3], [1, 4], [1, 5], [2, 3]]. If we simply sort and 
 * extract the 2nd dimension we get [3, 4, 5, 3], which implies that we can fit 3 
 * envelopes (3, 4, 5). The problem is that we can only fit one envelope, since 
 * envelopes that are equal in the first dimension can't be put into each other.
 * In order to fix this, we don't just sort increasing in the 1st dimension - 
 * we also sort decreasing on the 2nd dimension, so 2 envelopes that are equal 
 * in the 1st dimension can never be in the same increasing subsequence.
 */
class Solution1 {

	int lengthOfLIS(int[] heights) {
		int n = heights.length, result = 0;
		int[] dp = new int[n];
		for (int height : heights) {
			/*
			 * Arrays.binarySearch(dataType arr, int fromIndex, int toIndex, 
			 					   dataType key)
			 * arr - the array to be searched
			 * fromIndex = the index of the 1st element (inclusive) to be 
			 			   searched.
			 * toIndex = the index of the last element (exclusive) to be 
			 			 searched.
			 * key = the value to be searched for.
			 */
			int index = Arrays.binarySearch(dp, 0, result, height);
			if (index < 0)
				index = -index - 1;
			dp[index] = height;
			if (index == result)
				result++;
		}
		return result;
	}

	public int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, new Comparator<int[]>() {
			public int compare(int[] arr1, int[] arr2) {
				if (arr1[0] == arr2[0])
					return arr2[1] - arr1[1];
				else
					return arr1[0] - arr2[0];
			}
		});
		int n = envelopes.length;
		int[] heights = new int[n];
		for (int i = 0; i < n; i++)
			heights[i] = envelopes[i][1];
		return lengthOfLIS(heights);
	}
}



// Same approach as above, but with a raw version of BinarySearch
class Solution2 {

	class EnvelopeComparator implements Comparator<int[]> {
		public int compare(int[] arr1, int[] arr2) {
			return arr1[0] == arr2[0] ? arr2[1] - arr1[1] : arr1[0] - arr2[0];
		}
	}

	public int maxEnvelopes(int[][] envelopes) {
		int n = envelopes.length, size = 0;
		Arrays.sort(envelopes, new EnvelopeComparator());
		int[] dp = new int[n];
		for (int[] envelope : envelopes) {
			int low = 0, high = size;
			while (low < high) {
				int mid = low + (high - low) / 2;
				if (dp[mid] < envelope[1])
					low = mid + 1;
				else
					high = mid;
			}
			dp[low] = envelope[1];
			if (low == size)
				size++;
		}
		return size;
	}
}
