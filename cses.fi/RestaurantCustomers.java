/*
 * https://cses.fi/problemset/task/1619
 */
import java.io.*;
import java.lang.*;
import java.util.*;


public class RestaurantCustomers {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim()), res = 0, num = 0;
		TreeMap<Integer, Integer> map = new TreeMap<>();
		while (n-- > 0) {
			String[] temp = br.readLine().trim().split(" ");
			int a = Integer.parseInt(temp[0]), b = Integer.parseInt(temp[1]);
			map.put(a, map.getOrDefault(a, 0) + 1);
			map.put(b + 1, map.getOrDefault(b + 1, 0) - 1);
		}
		for (int x : map.keySet()) {
			num += map.get(x);
			res = Math.max(res, num);
		}
		System.out.println(res);
	}
}
