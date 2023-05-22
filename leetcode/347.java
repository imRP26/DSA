/*
 * https://leetcode.com/problems/top-k-frequent-words/
 */



/*
 * TC = O(N), SC = O(N)
 * Approach of Bucket Sorting from -> 
 * https://leetcode.com/problems/top-k-frequent-elements/solutions/81602/java-o-n-solution-bucket-sort/
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);
        List<List<Integer> > list = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++)
            list.add(new ArrayList<>());
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            list.get(entry.getValue()).add(entry.getKey());
        int index = 0, flag = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            for (int j = 0; j < list.get(i).size(); j++) {
                result[index++] = list.get(i).get(j);
                if (index == k) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1)
                break;
        }
        return result;
    }
}



/*
 * TC = O(N log N), SC = O(N)
 * My Naive Approach of Hashing
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);
        int[][] arr = new int[map.size()][2];
        int[] result = new int[k];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            arr[index++] = new int[] {entry.getKey(), entry.getValue()};
        Arrays.sort(arr, (a, b) -> (b[1] - a[1]));
        for (int i = 0; i < k; i++)
            result[i] = arr[i][0];
        return result;
    }
}



/*
 * TC = O(K log K), SC = O(N)
 * Approach of Heaps from official LC Editorial
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);
        Queue<Integer> minPQ = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (int n : map.keySet()) {
            minPQ.offer(n);
            if (minPQ.size() > k)
                minPQ.poll();
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--)
            result[i] = minPQ.poll();
        return result;
    }
}
