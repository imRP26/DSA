/*
 * https://leetcode.com/problems/find-original-array-from-doubled-array/
 */



/*
 * My approach of Sorting + HashMap -> take the edge case of 0 very very carefully!
 */
class Solution {
    public int[] findOriginalArray(int[] changed) {
        Arrays.sort(changed);
        Map<Integer, Integer> map = new HashMap<>();
        int n = changed.length, idx = 0;
        if (n % 2 == 1)
            return new int[0];
        for (int a : changed)
            map.put(a, map.getOrDefault(a, 0) + 1);
        int[] res = new int[n / 2];
        for (int i = 0; i < n; i++) {
            int a = changed[i], b = 2 * a;
            if (a == 0) {
                int count = map.get(0);
                if (count % 2 != 0)
                    return new int[0];
                i += count - 1;
                count /= 2;
                while (count-- > 0)
                    res[idx++] = 0;
                continue;
            }
            if (map.getOrDefault(a, 0) != 0 && map.getOrDefault(b, 0) != 0) {
                res[idx++] = a;
                map.put(a, map.get(a) - 1);
                map.put(b, map.get(b) - 1);
            }
            else if (map.getOrDefault(a, 0) != 0 && map.getOrDefault(b, 0) == 0)
                return new int[0];
        }
        return res;
    }
}



/*
 * 
 */
