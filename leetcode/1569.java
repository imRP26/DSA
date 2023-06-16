/*
 * https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/
 */



/*
 * Approach from LC Official Editorial!
 */
class Solution {

    private long[][] pascal;
    private final long mod = (long)1e9 + 7;

    private void buildPascal(int n) {
        pascal = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    pascal[i][j] = 1;
                else
                    pascal[i][j] = (pascal[i - 1][j - 1] + pascal[i - 1][j]) % mod;
            }
        }
    }

    private long dfs(List<Integer> list) {
        int n = list.size();
        if (n < 3)
            return 1;
        List<Integer> leftNodes = new ArrayList<>(), rightNodes = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (list.get(i) < list.get(0))
                leftNodes.add(list.get(i));
            else
                rightNodes.add(list.get(i));
        }
        long numWaysLeft = dfs(leftNodes), numWaysRight = dfs(rightNodes);
        return (((numWaysLeft * numWaysRight) % mod) * pascal[n - 1][leftNodes.size()]) % mod;
    }

    public int numOfWays(int[] nums) {
        buildPascal(nums.length);
        List<Integer> list = new ArrayList<>();
        for (int n : nums)
            list.add(n);
        return (int)((dfs(list) - 1) % mod);
    }
}
