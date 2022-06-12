/*
 * Question Link -> 
 * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/
 */



 /*
 * We first find the total number of 1s - now if all the 1s are to be grouped 
 * together through swaps, then it means that there exists a subarray wherein 
 * all the elements are 1s.
 * So we iterate through each subarray having size = the number of 1s in the 
 * parent array and the number of swaps to be made in that corresponding 
 * subarray = the number of 0s.
 * The minimum such swap number for a subarray gives us the final result.
*/
class Solution { // TC = O(n)
    public int minSwaps(int[] data) {
		int n = data.length, numOnes = 0, numZeroes = 0, low = 0, result = Integer.MAX_VALUE;
		for (int d : data) {
            numOnes += d; // adding up 1s
            numZeroes += 1 - d; // adfing up 0s
        }
        if (numZeroes == n || numOnes <= 1) // base case (sort of...)
            return 0;
        numZeroes = 0;
        for (int high = 0; high < n; high++) {
			if (data[high] == 0)
				numZeroes++;
            if (high - low + 1 == numOnes) {
                result = Math.min(result, numZeroes);
				if (data[low++] == 0)
					numZeroes--;
			}
		}
		return result == Integer.MAX_VALUE ? -1 : result;
    }
}
