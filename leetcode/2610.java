/*
 * https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/
 */



/*
 * My Naive solution, almost useful though!
 */
class Solution {
    public List<List<Integer>> findMatrix(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums)
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> a[1] - b [1]);
        for (int x : counts.keySet())
            minPQ.offer(new int[] {x, counts.get(x)});
        List<List<Integer> > result = new ArrayList<>();
        while (!minPQ.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            List<int[]> tempList = new ArrayList<>();
            int sz = minPQ.size();
            for (int i = 0; i < sz; i++) {
                int[] temp = minPQ.poll();
                list.add(temp[0]);
                if (temp[1] > 1)
                    tempList.add(new int[] {temp[0], temp[1] - 1});
            }
            result.add(new ArrayList<>(list));
            for (int[] temp : tempList)
                minPQ.offer(temp);
        }
        return result;
    }
}



/*
 * Approach from LC Official Editorial!
 */
class Solution {
    public List<List<Integer>> findMatrix(int[] nums) {
        int n = nums.length;
        int[] frequencies = new int[n + 1];
        List<List<Integer> > result = new ArrayList<>();
        for (int c : nums) {
            if (frequencies[c] >= result.size())
                result.add(new ArrayList<>());
            result.get(frequencies[c]).add(c);
            frequencies[c]++;
        }
        return result;
    }
}
