/*
 * https://leetcode.com/problems/reverse-integer/
 */



/*
 * Approach from Official LC Editorial!
 */
class Solution {
    public int reverse(int x) {
        int rev = 0, maxINT = Integer.MAX_VALUE, minINT = Integer.MIN_VALUE;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > maxINT / 10 || (rev == maxINT / 10 && pop > 7))
                return 0;
            if (rev < minINT / 10 || (rev == minINT / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}



/*
 * My own previous approach of 2021!
 */
class Solution {
	public int reverse(int x) {
		int res = 0;
		while (x > 0) {
			int d = x % 10;
			if (res > 0 && res > (Integer.MAX_VALUE - d) / 10)
				return 0;
			if (res < 0 && res < (Integer.MIN_VALUE - d) / 10)
				return 0;
			res = res * 10 + d;
			x /= 10;
		}
		return res;
	}
}
