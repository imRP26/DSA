import java.util.*;


// Simple Map based solution
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (Integer n : arr)
            map1.put(n, map1.getOrDefault(n, 0) + 1);
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            int n = entry.getValue();
            map2.put(n, map2.getOrDefault(n, 0) + 1);
            if (map2.get(n) > 1)
                return false;
        }
        return true;
    }
}
