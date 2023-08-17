/*
 * https://leetcode.com/problems/combinations/
 */



/*
 * Approach of Exhaustive Bitmasking
 */
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer> > res = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) != k)
                continue;
            int j = i;
            List<Integer> list = new ArrayList<>();
            for (int idx = 1; idx <= n; idx++) {
                if ((j & 1) != 0) {
                    list.add(idx);
                }
                j >>= 1;
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }
}



/*
 * Approach of Optimized Backtracking from LC Official Editorial!
 */
class Solution {

    private int n, k;
    private List<List<Integer> > res = new ArrayList<>();

    private void backtrack(List<Integer> currList, int firstNum) {
        if (currList.size() == k) {
            res.add(new ArrayList<>(currList));
            return;
        }
        int need = k - currList.size(), rem = n - firstNum + 1, avail = rem - need;
        for (int i = firstNum; i <= firstNum + avail; i++) {
            currList.add(i);
            backtrack(currList, i + 1);
            currList.remove(currList.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(new ArrayList<>(), 1);
        return res;
    }
}
