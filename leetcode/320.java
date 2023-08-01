/*
 * https://leetcode.com/problems/generalized-abbreviation/
 */



/*
 * My own approach of Bitmasking!
 */
class Solution {
    public List<String> generateAbbreviations(String word) {
        int n = word.length();
        int[] arr = new int[n];
        List<String> res = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            int j = i;
            for (int k = 0; k < n; k++) {
                arr[k] = ((j & 1) == 1) ? 1 : 0;
                j >>= 1;
            }
            StringBuilder sb = new StringBuilder();
            j = 0;
            while (j < n) {
                if (arr[j] == 1)
                    sb.append(word.charAt(j++));
                else {
                    int num0 = 0;
                    while (j < n && arr[j] == 0) {
                        num0++;
                        j++;
                    }
                    sb.append(String.valueOf(num0));
                }
            }
            res.add(sb.toString());
        }
        return res;
    }
}



/*
 * Approach of Backtracking from LC Official Editorial!
 */
class Solution {

    private List<String> res = new ArrayList<>();

    private void backtrack(StringBuilder sb, String w, int i, int k) {
        int len = sb.length();
        if (i == w.length()) {
            if (k != 0)
                sb.append(k);
            res.add(sb.toString()); // O(n)
        }
        else {
            backtrack(sb, w, i + 1, k + 1); // abbreviation to be done
            if (k != 0)
                sb.append(k);
            sb.append(w.charAt(i)); // abbreviation not to be done
            backtrack(sb, w, i + 1, 0);
        }
        sb.setLength(len);
    }

    public List<String> generateAbbreviations(String word) {
        backtrack(new StringBuilder(), word, 0, 0);
        return res;
    }
}
