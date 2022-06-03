import java.util.*;

/*
 * Question Link -> 
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
*/



// Neetcode's solutions are sexy AF! - using set
class Solution1 {
    public int lengthOfLongestSubstring(String s) {
        int low = 0, high, result = 0;
		Set<Character> set = new HashSet<>();
		for (high = 0; high < s.length(); high++) {
			while (set.contains(s.charAt(high))) { // O(1)
				set.remove(s.charAt(low)); // O(1)
				low += 1;
			}
			set.add(s.charAt(high)); // O(1)
			result = Math.max(result, high - low + 1);
		}
		return result;
    }
}



/* 
 * Basic idea is to use a hashmap which stores the characters in the string as 
 * keys and their positions as values.
 * 2 pointers are defined to store the maximum substring - the right pointer is
 * moved to scan through the string and the hashmap is updated as - while a 
 * particular character is already present in the hashmap, the left pointer is 
 * moved to the right of the same character last found.
*/
class Solution2 {
    public int lengthOfLongestSubstring(String s) {
		int len = s.length(), result = 0;
        if (len == 0)
			return result;
		Map<Character, Integer> map = new HashMap<>();
		for (int low = 0, high = 0; high < len; high++) {
			/* 
			 * here, "Math.max()" is done because if "low" has reached an index 
			 * of a large magnitude and a duplicate character is encountered at 
			 * "high", then we need to be at "low" only. 
			 * If we shift back to "map.get(s.charAt(high)) + 1", then we might 
			 * move back to a position which is invalid - the middle portion 
			 * between low and high might contain duplicates.
			*/ 
			if (map.containsKey(s.charAt(high)))
				low = Math.max(low, map.get(s.charAt(high)) + 1);
			map.put(s.charAt(high), high);
			result = Math.max(result, high - low + 1);
		}
		return result;
    }
}



// Using the same concept as above, with an int array
class Solution3 {
	public int lengthOfLongestSubstring(String s) {
		int len = s.length(), result = 0;
		if (len == 0)
			return result;
		int[] latestCharIndex = new int[256];
		Arrays.fill(latestCharIndex, -1);
		for (int low = 0, high = 0; high < len; high++) {
			if (latestCharIndex[s.charAt(high)] != -1)
				low = Math.max(low, latestCharIndex[s.charAt(high)] + 1);
			latestCharIndex[s.charAt(high)] = high;
			result = Math.max(result, high - low + 1);
		}
		return result;
	}
}
