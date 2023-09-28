/*
 * https://codeforces.com/contest/1389/problem/B
 */



/*
 * Referred from official tutorial and SkSama's YouTube video!
 */
import java.io.*;
import java.util.*;


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String[] temp = br.readLine().trim().split(" ");
            int n = Integer.parseInt(temp[0]), k = Integer.parseInt(temp[1]), z = Integer.parseInt(temp[2]);
            temp = br.readLine().trim().split(" ");
            int[] nums = new int[n];
            long[] prefixSum = new long[n];
            nums[0] = Integer.parseInt(temp[0]);
            prefixSum[0] = nums[0];
            for (int i = 1; i < n; i++) {
                nums[i] = Integer.parseInt(temp[i]);
                prefixSum[i] = prefixSum[i - 1] + nums[i];
            }
            long res = prefixSum[k];
            for (int i = 0; i < k; i++) {
                for (int j = 1; j <= z; j++) {
                    int x = k - j * 2;
                    if (x < i)
                        continue;
                    res = Math.max(res, prefixSum[x] + j * (nums[i] + nums[i + 1]));
                }
            }
            System.out.println(res);
        }
    }
}
