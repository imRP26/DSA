/*
 * https://leetcode.com/problems/find-longest-awesome-substring/
 */



/*
 * What a wonderful motherfucking approach from -> 
 * https://leetcode.com/problems/find-longest-awesome-substring/solutions/785213/example-input-3242415-explanation-with-bitmask/
 * A string encountered so far can be palindrome if upon XOR operations, the same string is obtained at a later 
 * point of time. 
 * Or, a bitmask is obtained that differs from a previously obtained bitmask by 1 bit, signifying the presence of 
 * a character occuring odd number of times!
 */
class Solution {
    public int longestAwesome(String s) {
        int currMask = 0, res = 1;
        Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
        for (int i = 0; i < s.length(); i++) {
        	currMask ^= (1 << (s.charAt(i) - '0'));
        	if (!map.containsKey(currMask))
        		map.put(currMask, i);
        	else
        		res = Math.max(res, i - map.get(currMask));
        	for (int j = 0; j < 10; j++) {
        		int otherMask = currMask ^ (1 << j);
        		if (map.containsKey(otherMask))
        			res = Math.max(res, i - map.get(otherMask));
        	}
        }
        return res;
    }
}
