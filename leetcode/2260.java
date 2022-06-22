import java.util.*;

/*
 * Question Link -> 
 * https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up/
 */



/*
 * Simple usage of HashMap - if an element has already been encountered, then 
 * just compute the required result from the current index and the one 
 * previously stored.
 */
class Solution {
    public int minimumCardPickup(int[] cards) {
		int result = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < cards.length; i++) {
			if (map.containsKey(cards[i]))
                result = Math.min(result, i - map.get(cards[i]) + 1);
            map.put(cards[i], i);
		}
		return result == Integer.MAX_VALUE ? -1 : result;
    }
}
