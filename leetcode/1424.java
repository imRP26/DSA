/*
 * https://leetcode.com/problems/diagonal-traverse-ii/
 */



/*
 * Approach of HashMap from LC Official Editorial!
 */
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int rows = nums.size(), size = 0, idx = 0, maxRowColVal = Integer.MIN_VALUE;
        Map<Integer, List<Integer> > map = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                map.computeIfAbsent(i + j, k -> new ArrayList<>()).add(nums.get(i).get(j));
                size++;
                maxRowColVal = Math.max(maxRowColVal, i + j);
            }
        }
        int[] res = new int[size];
        for (int i = 0; i <= maxRowColVal; i++) {
            List<Integer> list = map.get(i);
            for (int j = list.size() - 1; j >= 0; j--)
                res[idx++] = list.get(j);
        }
        return res;
    }
}



/*
 * Approach of BFS from LC Official Editorial -> look at the pattern of cells getting added into the queue
 * and then see how BFS can be applied - small optimization being for col == 0 only, add the corresponding 
 * cell immediately down and for others, add the cell immediately to the right.
 */
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Queue<int[]> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        q.offer(new int[] {0, 0});
        int rows = nums.size();
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int row = temp[0], col = temp[1];
            result.add(nums.get(row).get(col));
            if (col == 0 && row + 1 < rows && col < nums.get(row + 1).size())
                q.offer(new int[] {row + 1, col});
            if (col + 1 < nums.get(row).size())
                q.offer(new int[] {row, col + 1});
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
