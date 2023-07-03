/*
 * https://leetcode.com/problems/optimal-account-balancing/
 */



/*
 * Backtracking Approach from LC Official Editorial!
 */
class Solution {

    private int n;
    private Map<Integer, Integer> map = new HashMap<>();
    private List<Integer> list = new ArrayList<>();

    private int backtrack(int i) {
        while (i < n && list.get(i) == 0)
            i++;
        if (i == n)
            return 0;
        int ans = Integer.MAX_VALUE;
        for (int j = i + 1; j < n; j++) {
            if (list.get(i) * list.get(j) < 0) {
                list.set(j, list.get(j) + list.get(i));
                ans = Math.min(ans, 1 + backtrack(i + 1));
                list.set(j, list.get(j) - list.get(i));
            }
        }
        return ans;
    }

    public int minTransfers(int[][] transactions) {
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) + t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) - t[2]);
        }
        for (int t : map.values()) {
            if (t != 0)
                list.add(t);
        }
        n = list.size();
        return backtrack(0);
    }
}



/*
 * Approach of DP with Bitmasking from -> 
 * https://leetcode.com/problems/optimal-account-balancing/solutions/219187/short-o-n-2-n-dp-solution-with-detailed-explanation-and-complexity-analysis/
 */
class Solution {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
        }
        List<Integer> list = map.values().stream().filter(v -> v != 0).collect(Collectors.toList());
        int n = list.size();
        int[] dp = new int[1 << n], subsetSums = new int[1 << n];
        for (int currMask = 0; currMask < (1 << n); currMask++) {
            int setBit = 1;
            for (int i = 0; i < n; i++) {
                if ((currMask & setBit) == 0) {
                    int newMask = currMask | setBit;
                    subsetSums[newMask] = subsetSums[currMask] + list.get(i);
                    if (subsetSums[newMask] == 0)
                        dp[newMask] = Math.max(dp[newMask], 1 + dp[currMask]);
                    else
                        dp[newMask] = Math.max(dp[newMask], dp[currMask]);
                }
                setBit <<= 1;
            }
        }
        return n - dp[(1 << n) - 1];
    }
}
