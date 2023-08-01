/*
 * https://leetcode.com/problems/valid-word-abbreviation/
 */



/*
 * Good Ad-Hoc Implementation based question!
 */
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int w = 0, a = 0, wlen = word.length(), alen = abbr.length();
        while (a < alen && w < wlen) {
            if (abbr.charAt(a) >= 'a' && abbr.charAt(a) <= 'z') {
                if (word.charAt(w) != abbr.charAt(a))
                    return false;
                w++;
            }
            else {
                StringBuilder num = new StringBuilder();
                if (abbr.charAt(a) == '0')
                    return false;
                while (a < alen && abbr.charAt(a) >= '0' && abbr.charAt(a) <= '9')
                    num.append(abbr.charAt(a++));
                int n = Integer.parseInt(num.toString());
                if (n > wlen - w)
                    return false;
                w += n;
                a--;
            }
            a++;
        }
        return a == alen && w == wlen;
    }
}
