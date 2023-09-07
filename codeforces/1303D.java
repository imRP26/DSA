/*
 * https://codeforces.com/problemset/problem/1303/D
 */
import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			String[] temp = br.readLine().trim().split(" ");
			long target = Long.parseLong(temp[0]), sum = 0, res = 0;
			int n = Integer.parseInt(temp[1]);
			temp = br.readLine().trim().split(" ");
			PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
			for (int i = 0; i < n; i++) {
				int x = Integer.parseInt(temp[i]);
				sum += x;
				maxPQ.offer(x);
			}
			if (sum < target) {
				System.out.println(-1);
				continue;
			}
			while (target > 0) {
				int x = maxPQ.poll();
				if (sum - x >= target)
					sum -= x;
				else if (x <= target) {
					sum -= x;
					target -= x;
				}
				else {
					res++;
					maxPQ.offer(x / 2);
					maxPQ.offer(x / 2);
				}
			}
			System.out.println(res);
		}
	}
}
