import java.util.*;


/* 
 * Simple HashMap
 */
class Solution1 {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = arr.length, result = 1;
        for (int i = 0; i < n; i++)
            map.put(arr[i], 1 + map.getOrDefault(arr[i] - difference, 0));
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            result = Math.max(result, entry.getValue());
        return result;
    }
}
