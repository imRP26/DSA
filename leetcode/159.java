import java.util.*;

/* 
 * Question Link -> 
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
*/



// My O(N) TC, O(1) SC solution
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int low = 0, high = 0, n = s.length(), result = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (high = 0; high < n; high++) {
			while (map.size() > 2) {
                char ch = s.charAt(low);
				if (map.get(ch) == 1)
					map.remove(ch);
                else
                    map.put(ch, map.get(ch) - 1);
				low++;
			}
            char ch = s.charAt(high);
			if (map.containsKey(ch))
				map.put(ch, map.get(ch) + 1);
			else
				map.put(ch, 1);
			if (map.size() <= 2)
				result = Math.max(result, high - low + 1);
		}
		return result;
    }
}



/*
 * There's another solution which just uses plain iteration :- 
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/discuss/179386/beats-91-Java-only-1-loop-and-4-variables-without-any-long-expression
 * So far, I've been unable to understand it, will update this when I get to 
 * know of the intuition behind the above problem.
*/
