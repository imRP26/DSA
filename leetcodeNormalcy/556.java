/*
 * Question Link -> https://leetcode.com/problems/next-greater-element-iii/
*/



// Same concept as Leetcode 31 : Next Permutation
class Solution1 {
    public int nextGreaterElement(int n) {
        char[] number = (n + "").toCharArray();
		int len = number.length, i = len - 2, j = len - 1;
		while (i >= 0 && number[i] >= number[i + 1])
			i--;
		if (i == -1)
			return -1;
		while (j > i && number[j] <= number[i])
			j--;
		number[i] ^= number[j];
		number[j] ^= number[i];
		number[i] ^= number[j];
		i++;
		j = len - 1;
		while (i < j) {
			number[i] ^= number[j];
			number[j] ^= number[i];
			number[i++] ^= number[j--];
		}
		long value = Long.parseLong(new String(number));
		return (value <= Integer.MAX_VALUE) ? (int) value : -1;
    }
}



// Just going through another way of writing the same return statement
class Solution2 {
    public int nextGreaterElement(int n) {
        char[] number = (n + "").toCharArray();
		int len = number.length, i = len - 2, j = len - 1;
		while (i >= 0 && number[i] >= number[i + 1])
			i--;
		if (i == -1)
			return -1;
		while (j > i && number[j] <= number[i])
			j--;
		number[i] ^= number[j];
		number[j] ^= number[i];
		number[i] ^= number[j];
		i++;
		j = len - 1;
		while (i < j) {
			number[i] ^= number[j];
			number[j] ^= number[i];
			number[i++] ^= number[j--];
		}
		try {
			return Integer.valueOf(String.valueOf(number));
		}
		catch (NumberFormatException e) {
			return -1;
		}
    }
}



// Different way of converting an integer to a char array
class Solution3 {
    public int nextGreaterElement(int n) {
        char[] number = String.valueOf(n).toCharArray();
		int len = number.length, i = len - 2, j = len - 1;
		while (i >= 0 && number[i] >= number[i + 1])
			i--;
		if (i == -1)
			return -1;
		while (j > i && number[j] <= number[i])
			j--;
		number[i] ^= number[j];
		number[j] ^= number[i];
		number[i] ^= number[j];
		i++;
		j = len - 1;
		while (i < j) {
			number[i] ^= number[j];
			number[j] ^= number[i];
			number[i++] ^= number[j--];
		}
		try {
			return Integer.valueOf(String.valueOf(number));
		}
		catch (NumberFormatException e) {
			return -1;
		}
    }
}