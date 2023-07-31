/*
 * https://leetcode.com/problems/one-edit-distance/
 */



/*
 * Bhaari bakchodi, not at all satisfied, 1-1 TC milaake dekhna pada!
 */
class Solution {

    private boolean insertDelete(String s1, String s2) {
        boolean oneDiff = false;
        //assumption : s1.length() < s2.length()
        int i1 = 0, i2 = 0, len1 = s1.length(), len2 = s2.length();
        while (i1 < len1 && i2 < len2) {
            if (s1.charAt(i1) != s2.charAt(i2)) {
                if (oneDiff)
                    break;
                oneDiff = true;
                i2++;
            }
            else {
                i1++;
                i2++;
            }   
        }
        return i1 == len1 && i2 >= len2 - 1; // ye bohot sahi condition hai, jab len1 = 0 and len2 = 1 hota hai!
    }

    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length(), len2 = t.length(), numDiffs = 0;
        if (len1 - len2 > 1 || len1 - len2 < -1)
            return false;
        if (len1 < len2 && insertDelete(s, t)) 
            return true;
        if (len1 > len2 && insertDelete(t, s))
            return true;
        if (len1 == len2) {
            for (int i = 0; i < len1; i++)
                numDiffs += (s.charAt(i) != t.charAt(i)) ? 1 : 0;
            return numDiffs == 1;
        }
        return false;
    }
}



/*
 * Such an easy 1-pass method, try looking at the bigger picture, as to how if 1 diff char is observed, then 
 * what can be done with the rest of the strings?
 * This was done by me in 2017!!!
 */
class Solution {
	public boolean isOneEditDistance(String s, String t) {
		int slen = s.length(), tlen = t.length();
		for (int i = 0; i < Math.min(slen, tlen); i++) {
			if (s.charAt(i) != t.charAt(i)) {
				if (slen == tlen)
					return s.substring(i + 1).equals(t.substring(i + 1));
				if (slen < tlen)
					return s.substring(i).equals(t.substring(i + 1));
				return s.substring(i + 1).equals(t.substring(i));
			}
		}
		return Math.abs(slen - tlen) == 1;
	}
}
