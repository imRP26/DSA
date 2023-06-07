/*
 * https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/
 */



/*
 * Ad-Hoc, Simple
 */
class Solution {

    private void getBitsList(int n, List<Integer> ln) {
        while (ln.size() < 30) {
            if (n % 2 == 1)
                ln.add(0, 1);
            else
                ln.add(0, 0);
            n /= 2;
        }
    }

    public int minFlips(int a, int b, int c) {
        List<Integer> la = new ArrayList<>(), lb = new ArrayList<>(), lc = new ArrayList<>();
        getBitsList(a, la);
        getBitsList(b, lb);
        getBitsList(c, lc);
        int res = 0;
        for (int i = 0; i < 30; i++) {
            int ci = lc.get(i), ai = la.get(i), bi = lb.get(i);
            if (ci == (ai | bi))
                continue;
            if (ci == 0 && ai == 1 && bi == 1)
                res += 2;
            else
                res += 1;
        }
        return res;
    }
}
