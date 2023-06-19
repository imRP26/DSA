import java.io.*;
import java.lang.*;
import java.util.*;


public class PalindromeReorder {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine().trim();
		Map<Character, Integer> map = new HashMap<>();
		int len = s.length(), idx = 0, numOdd = 0;
		boolean flag = true;
		char[] chars = new char[len];
		for (char c : s.toCharArray())
			map.put(c, map.getOrDefault(c, 0) + 1);
		for (char c : map.keySet()) {
			int freq = map.get(c);
			if (freq % 2 == 1) {
				if (numOdd > 0) {
					flag = false;
					break;
				} 
				numOdd++;
				int pos = len / 2, offset = 1;
				chars[pos] = c;
				freq--;
				while (freq > 0) {
					chars[pos + offset] = c;
					chars[pos - offset] = c;
					freq -= 2;
					offset++;
				}
			}
			else {
				while (freq > 0) {
					chars[idx] = c;
					chars[len - idx - 1] = c;
					idx++;
					freq -= 2;
				}
			}
		}
		if (flag)
			System.out.println(new String(chars));
		else 
			System.out.println("NO SOLUTION");
	}
}