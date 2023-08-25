import java.io.*;
import java.util.*;


public class SubarraySums2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]);
		long x = Long.parseLong(temp[1]);
		long[] nums = new long[n];
		temp = br.readLine().trim().split(" ");
		for (int i = 0; i < n; i++)
			nums[i] = Long.parseLong(temp[i]);
		Map<Long, Integer> map = new HashMap<>();
		long sum = 0, res = 0;
		map.put((long)0, 1);
		for (int i = 0; i < n; i++) {
			sum += nums[i];
			res += map.getOrDefault(sum - x, 0);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		System.out.println(res);
	}
}
