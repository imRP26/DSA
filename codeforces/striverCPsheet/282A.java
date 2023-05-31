import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inp = br.readLine();
		int n = Integer.parseInt(inp), result = 0;
		while (n-- > 0) {
			inp = br.readLine();
			if (inp.charAt(0) == '+' || inp.charAt(inp.length() - 1) == '+')
				result++;
			else
				result--;
		}
		System.out.println(result);
	}
}
