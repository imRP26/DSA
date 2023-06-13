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
 * TC = O(N^2)
 * For reference purposes -> 
 * https://www.geeksforgeeks.org/difference-between-arrays-tostring-and-arrays-deeptostring-in-java/
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



/*
 * Approach 3 of Trie from -> https://leetcode.com/problems/equal-row-and-column-pairs/editorial/
 */
class Solution {

    class TrieNode {
        int count;
        Map<Integer, TrieNode> children;
        public TrieNode() {
            this.count = 0;
            this.children = new HashMap<>();
        }
    }

    TrieNode root = new TrieNode();

    private void insert(int[] arr) {
        TrieNode currNode = root;
        for (int a : arr) {
            if (!currNode.children.containsKey(a))
                currNode.children.put(a, new TrieNode());
            currNode = currNode.children.get(a);
        }
        currNode.count += 1;
    }

    private int search(int[] arr) {
        TrieNode currNode = root;
        for (int a : arr) {
            if (!currNode.children.containsKey(a))
                return 0;
            currNode = currNode.children.get(a);
        }
        return currNode.count;
    }

    public int equalPairs(int[][] grid) {
        int res = 0, n = grid.length;
        int[] colArray = new int[n];
        for (int[] row : grid)
            insert(row);
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++)
                colArray[i] = grid[i][j];
            res += search(colArray);
        }
        return res;
    }
}
