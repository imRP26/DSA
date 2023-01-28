/*
 * https://leetcode.com/problems/confusing-number-ii/
 */



/*
 * DFS based Approach from 
 * https://leetcode.com/problems/confusing-number-ii/solutions/312767/java-dfs-solution/
 */
class Solution {

    Map<Integer, Integer> map = new HashMap<>();
    int[] num = {0, 1, 6, 8, 9};

    private boolean isConfused(long s) {
        long res = 0, x = s;
        while (x > 0) {
            int i = (int)(x % 10);
            if (!map.containsKey(i))
                return false;
            long digit = map.get(i);
            res = res * 10 + digit;
            x /= 10;
        }
        return res != s;
    }

    private void dfs(long start, int n, int[] count) {
        if (start > n)
            return;
        if (start <= n && isConfused(start))
            count[0]++;
        for (int i = start == 0 ? 1 : 0; i < 5; i++)
            dfs(start * 10 + num[i], n, count);
    }

    public int confusingNumberII(int n) {
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        int[] count = new int[1];
        dfs(0, n, count);
        return count[0];
    }
}
