import java.util.*;

/*
 * Question Link -> 
 * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */



// Simple TreeMap based Solution - TC = O(n * log(n) + n * log(n) * k * log(n))
class Solution1 {
    public boolean isPossibleDivide(int[] nums, int k) {
        /*
         * TreeMap implementation provides guaranteed log(n) time cost for the 
         * containsKey(), get(), put() and remove() operations.
         */
		Map<Integer, Integer> map = new TreeMap<>();
		for (int num : nums) // O(n * log n)
			map.put(num, map.getOrDefault(num, 0) + 1);
		for (int num : map.keySet()) { // O(n)
			if (map.get(num) > 0) { // O(log n)
				for (int i = k - 1; i >= 0; i--) { // O(k)
					if (map.getOrDefault(num + i, 0) < map.get(num)) // O(log n)
						return false;
					map.put(num + i, map.get(num + i) - map.get(num)); // O(log n)
				}
			}
		}
		return true;
    }
}



// Exactly same concept as above, but using a HashMap and a SortedSet
class Solution2 { // TC = O(n * log(n))
    public boolean isPossibleDivide(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) // O(n)
			map.put(num, map.getOrDefault(num, 0) + 1);
        SortedSet<Integer> numVals = new TreeSet<>(map.keySet()); // O(n * log(n))
		for (int num : numVals) { // O(n)
        	if (map.get(num) > 0) {
				for (int i = k - 1; i >= 0; i--) { // O(k)
					if (map.getOrDefault(num + i, 0) < map.get(num))
						return false;
					map.put(num + i, map.get(num + i) - map.get(num));
				}
			}
		}
		return true;
    }
}



// Another slightly similar concept - TC = O(N * log(N))
class Solution3 {
    public boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        while (!map.isEmpty()) {
            Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
            int key = entry.getKey(), value = entry.getValue();
            for (int key1 = key + 1; key1 < key + k; key1++) {
                Integer oldVal = map.put(key1, map.getOrDefault(key1, 0) - value);
                if (oldVal == null || oldVal < value)
                    return false;
                map.remove(key1, 0);
            }
        }
        return true;
    }
}
