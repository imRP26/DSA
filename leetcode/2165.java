import java.util.*;

/*
 * Question Link -> 
 * https://leetcode.com/problems/smallest-value-of-the-rearranged-number/ 
 */



// My Naive Solution
class Solution1 {
    public long smallestNumber(long num) {
		if (num == 0)
			return 0;
		int num0 = 0;
        long num1 = num;
		List<Character> list = new ArrayList<>();
        num = num > 0 ? num : -num;
		while (num > 0) {
			int digit = (int)(num % 10);
			if (digit != 0)
				list.add(Character.forDigit(digit, 10));
                //list.add((char)(digit + '0'));
			else
				num0++;
            num /= 10;
		}
        StringBuilder sb = new StringBuilder();
		if (num1 > 0) {
			Collections.sort(list);
			sb.append(list.get(0));
            list.remove(0);
			while (num0 > 0) {
				sb.append('0');
				num0--;
			}
			for (char c : list)
				sb.append(c);
		}
		else {
			Collections.sort(list, Collections.reverseOrder());
			for (char c : list)
				sb.append(c);
			while (num0 > 0) {
				sb.append('0');
				num0--;
			}
		}
        long result = Long.parseLong(sb.toString());
		return num1 > 0 ? result : -result;
    }
}



// Simplified, sexier version!!
class Solution2 {
    public long smallestNumber(long num) {
        if (num == 0)
            return num;
        char[] numChars = ("" + Math.abs(num)).toCharArray();
        Arrays.sort(numChars);
        if (num < 0)
            return -Long.parseLong(new StringBuilder(new String(numChars))
                        .reverse()
                        .toString());
        int index = 0;
        while (numChars[index] == '0')
            index++;
        numChars[0] = (char)(numChars[0] ^ numChars[index] ^ 
                                            (numChars[index] = numChars[0]));
        return Long.parseLong(new String(numChars));
    }
}
