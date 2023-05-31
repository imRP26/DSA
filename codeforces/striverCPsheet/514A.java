import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine(), result = "";
		for (int i = 0; i < s.length(); i++) {
			int d = s.charAt(i) - '0';
			if (d > 4) {
				if (d == 9 && i == 0)
					result += (char)(d + '0');
				else
					result += (char)((9 - d) + '0');
			}
			else
				result += (char)(d + '0');
		}
		System.out.println(result);
	}
}
