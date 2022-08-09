import java.util.*;

/*
 * https://leetcode.com/problems/binary-trees-with-factors/
 */



/*
 * Refer to the explanation given in here -> 
 * https://leetcode.com/problems/binary-trees-with-factors/discuss/1107268/JS-Python-Java-C%2B%2B-or-Fastest-Solution-w-Explanation
 */
class Solution1 {
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        long result = 0, mod = (long)1e9 + 7;
        Map<Integer, Long> map = new HashMap<>();
        for (int num : arr) {
            long numWays = 1;
            double lim = Math.sqrt(num);
            for (int j = 0, fa = arr[0]; fa <= lim; fa = arr[++j]) {
                if (num % fa != 0)
                    continue;
                int fb = num / fa;
                if (map.containsKey(fb))
                    numWays = (numWays + map.get(fa) * map.get(fb) * ((fa == fb) ? 1 : 2)) % mod;
            }
            map.put(num, numWays);
            result = (result + numWays) % mod;
        }
        return (int)result;
    }
}



/*
 * Another simpler way referred to from here :- 
 * https://leetcode.com/problems/binary-trees-with-factors/discuss/126277/Concise-Java-solution-using-HashMap-with-detailed-explanation.-Easily-understand!!!
 */
class Solution2 {
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Long> map = new HashMap<>();
        long count = 1, result = 0, mod = (long)1e9 + 7;
        map.put(arr[0], count);
        for (int i = 1; i < arr.length; i++) {
            count = 1;
            for (Integer num : map.keySet()) {
                if (arr[i] % num == 0 && map.containsKey(arr[i] / num))
                    count += map.get(num) * map.get(arr[i] / num);
            }
            map.put(arr[i], count);
        }
        for (Integer num : map.keySet())
            result = (result + map.get(num)) % mod;
        return (int)result;
    }
}
