import java.util.*;

/*
 * Question Link -> 
 * https://www.codingninjas.com/codestudio/problems/count-subarrays-with-given-xor_1115652
 */



/*
 * Maintain a variable prexor to store the prefix XORs.
 * If upon iteration through the input array, the prefix XOR is found to be = x, 
 * then increment the count, otherwise search if a subarray XOR of value 
 * (prexor ^ x) is recorded in the hashmap or not, if yes, then increment the 
 * count. - concept similar to Two Sum of Leetcode.
 * Just refer to Striver's video on this question, if the above doesn't make sense.
 */ 
class Solution {
	public int subarraysXor(ArrayList<Integer> arr, int x) {
		Map<Integer, Integer> map = new HashMap<>();
        int prexor = 0, result = 0;
        for (int i = 0; i < arr.size(); i++) {
            prexor ^= arr.get(i);
            if (prexor == x)
                result++;
            if (map.containsKey(prexor ^ x))
                result += map.get(prexor ^ x);
            map.put(prexor, map.getOrDefault(prexor, 0) + 1);
        }
        return result;
	}
}
