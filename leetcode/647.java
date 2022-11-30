/*
 * Take separate cases for even and odd substrings
 */
class Solution {
    public int countSubstrings(String s) {
        int i, n = s.length(), result = 0, low, high;
		for (i = 0; i < n; i++) {
			if (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
				low = i;
				high = i + 1;
				while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
					result++;
					low--;
					high++;
				}
			}
			low = i;
			high = i;
			while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
				result++;
				low--;
				high++;
			}
		}
		return result;
    }
}
