/*
 * https://thejoboverflow.com/problem/266/
 */
import java.io.*;
import java.util.*;


public class Main {
	
	private static int countLessThan(List<Integer> list, int x) {
		int n = list.size(), low = 0, high = n - 1, ans = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (list.get(mid) < x) {
				ans = mid;
				low = mid + 1;
			}
			else
				high = mid - 1;
		}
		return ans + 1;
	}
	
	private static int countMoreThan(List<Integer> list, int x) {
		int n = list.size(), low = 0, high = n - 1, ans = n;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (list.get(mid) > x) {
				ans = mid;
				high = mid - 1;
			}
			else
				low = mid + 1;
		}
		return n - ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int x = Integer.parseInt(temp[0]), y = Integer.parseInt(temp[1]), z = Integer.parseInt(temp[2]);
		long res = 0;
		List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>(), list3 = new ArrayList<>();
		temp = br.readLine().trim().split(" ");
		for (int i = 0; i < x; i++)
			list1.add(Integer.parseInt(temp[i]));
		temp = br.readLine().trim().split(" ");
		for (int i = 0; i < y; i++)
			list2.add(Integer.parseInt(temp[i]));
		temp = br.readLine().trim().split(" ");
		for (int i = 0; i < z; i++)
			list3.add(Integer.parseInt(temp[i]));
		Collections.sort(list1);
		Collections.sort(list2);
		Collections.sort(list3);
		for (int i = 0; i < y; i++) {
			int count1 = countLessThan(list1, list2.get(i)), count2 = countMoreThan(list3, list2.get(i));
			res += (long)count1 * (long)count2;
		}
		System.out.println(res);
	}
}
