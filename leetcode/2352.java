/*
 * https://leetcode.com/problems/equal-row-and-column-pairs/
 */



/*
 * My Simple, Naive Solution -> TC = O(N^3) :(
 */
class Solution {
    public int equalPairs(int[][] grid) {
        List<List<Integer> > rowList = new ArrayList<>();
        int rows = grid.length, cols = grid[0].length, res = 0;
        for (int i = 0; i < rows; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < cols; j++)
                temp.add(grid[i][j]);
            rowList.add(new ArrayList<>(temp));
        }
        for (int j = 0; j < cols; j++) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < rows; i++)
                temp.add(grid[i][j]);
            for (int i = 0; i < rows; i++) {
                if (rowList.get(i).equals(temp))
                    res++;
            }
        }
        return res;
    }
}



/*
 * Approach 2 of Hashing from -> https://leetcode.com/problems/equal-row-and-column-pairs/editorial/
 */
class Solution {
    public int equalPairs(int[][] grid) {
        Map<String, Integer> map = new HashMap<>();
        int rows = grid.length, cols = grid[0].length, res = 0;
        int[] gridCol = new int[rows];
        for (int[] row : grid) {
            String rowString = Arrays.toString(row);
            map.put(rowString, 1 + map.getOrDefault(rowString, 0));
        }
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++)
                gridCol[i] = grid[i][j];
            res += map.getOrDefault(Arrays.toString(gridCol), 0);
        }
        return res;
    }
}
