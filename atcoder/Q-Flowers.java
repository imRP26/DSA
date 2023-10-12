/*
 * https://atcoder.jp/contests/dp/tasks/dp_q
 */
// Approach from -> https://discuss.codechef.com/t/atcoder-dp-contest-q-flowers/35722/6?u=rahul1003
import java.io.*;
import java.util.*;


public class Main {

	private static int[] heights, beauties;
	private static long[] segtree, beautySum;

	private static void build(int node, int low, int high) {
		if (low == high)
			segtree[node] = beautySum[low];
		else {
			int mid = low + (high - low) / 2, leftChild = 2 * node, rightChild = 2 * node + 1;
			build(leftChild, low, mid);
			build(rightChild, mid + 1, high);
			segtree[node] = Math.max(segtree[leftChild], segtree[rightChild]);
		}
	}

	private static long query(int node, int low, int high, int left, int right) {
		if (left > right)
			return 0;
		if (low == left && high == right)
			return segtree[node];
		int mid = low + (high - low) / 2;
		return Math.max(query(2 * node, low, mid, left, Math.min(right, mid)), 
						query(2 * node + 1, mid + 1, high, Math.max(left, mid + 1), right));
	}

	private static void update(int node, int low, int high, int pos, long newVal) {
		if (low == high)
			segtree[node] = newVal;
		else {
			int mid = low + (high - low) / 2, leftChild = 2 * node, rightChild = 2 * node + 1;
			if (pos <= mid)
				update(leftChild, low, mid, pos, newVal);
			else 
				update(rightChild, mid + 1, high, pos, newVal);
			segtree[node] = Math.max(segtree[leftChild], segtree[rightChild]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		String[] temp = br.readLine().trim().split(" ");
		heights = new int[n];
		for (int i = 0; i < n; i++)
			heights[i] = Integer.parseInt(temp[i]);
		temp = br.readLine().trim().split(" ");
		beauties = new int[n];
		for (int i = 0; i < n; i++)
			beauties[i] = Integer.parseInt(temp[i]);
		int[][] array = new int[n][3];
		for (int i = 0; i < n; i++) {
			array[i][0] = heights[i];
			array[i][1] = beauties[i];
			array[i][2] = i;
		}
		Arrays.sort(array, (a, b) -> b[0] - a[0]);
		segtree = new long[5 * n];
		beautySum = new long[n]; // max summation of beauty val starting at a particular pos
		build(1, 0, n - 1);
		for (int i = 0; i < n; i++) {
			int pos = array[i][2];
			long maxVal = query(1, 0, n - 1, pos, n - 1);
			beautySum[pos] = maxVal + array[i][1];
			update(1, 0, n - 1, pos, beautySum[pos]);
		}
		long res = 0;
		for (long val : beautySum)
			res = Math.max(res, val);
		System.out.println(res);
	}
}
