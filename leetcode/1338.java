import java.util.*;
import javafx.util.Pair;

/*
 * https://leetcode.com/problems/reduce-array-size-to-the-half/
 */



// My Naive Solution using HashMap and List - Simple Simulation
class Solution1 {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr)
            map.put(num, map.getOrDefault(num, 0) + 1);
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        int arrSize = arr.length, numRemovals = 0, i;
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            list.add(new Pair(entry.getKey(), entry.getValue()));
        Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
        for (i = 0; i < list.size(); i++) {
            numRemovals += list.get(i).getValue();
            if (numRemovals >= arrSize / 2)
                break;
        }
        return (i + 1);
    }
}



/*
 * Alternate method for the above, TC = O(NlogN), SC = O(N)
 */
class Solution2 {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : arr)
            map.put(x, map.getOrDefault(x, 0) + 1);
        int[] frequencies = new int[map.values().size()];
        int index = 0;
        for (int frequency : map.values())
            frequencies[index++] = frequency;
        Arrays.sort(frequencies);
        int result = 0, numRemoved = 0, halfSize = arr.length / 2;
        index = frequencies.length - 1;
        while (numRemoved < halfSize) {
            result++;
            numRemoved += frequencies[index--];
        }
        return result;
    }
}



// Method of Counting Sort - TC = O(N), SC = O(N)
class Solution3 {
    public int minSetSize(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : arr)
            map.put(x, map.getOrDefault(x, 0) + 1);
        int[] counting = new int[n + 1];
        for (int frequency : map.values())
            counting[frequency]++;
        int result = 0, numRemoved = 0, halfSize = n / 2, frequency = n;
        while (numRemoved < halfSize) {
            result++;
            while (counting[frequency] == 0)
                frequency--;
            numRemoved += frequency;
            --counting[frequency];
        }
        return result;
    }
}
