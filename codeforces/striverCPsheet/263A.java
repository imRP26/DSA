import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] row;
		int i = 0, j = 0;
		boolean flag = false;
		for (i = 0; i < 5; i++) {
			row = br.readLine().split(" ");
			for (j = 0; j < 5; j++) {
				if (row[i].equals("1")) {
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}
		System.out.println(Math.abs(i - 2) + Math.abs(j - 2));
	}
}
