import java.io.*;
import java.util.*;


public class SubarraySums1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]);
		long x = Long.parseLong(temp[1]), sum = 0, res = 0;
		int[] nums = new int[n];
		temp = br.readLine().trim().split(" ");
		for (int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(temp[i]);
		Map<Long, Integer> map = new HashMap<>();
		map.put((long)0, 1);
		for (int i = 0; i < n; i++) {
			sum += nums[i];
			res += map.getOrDefault(sum - x, 0);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		System.out.println(res);
	}
}
