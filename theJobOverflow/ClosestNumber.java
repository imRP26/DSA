/*
 * https://thejoboverflow.com/problem/391/
 */



/*
 * Simple Approach of Lower Bound (Binary Search)!
 */
import java.io.*;
import java.util.*;


public class Main {

    private static int lowerBound(int[] nums, int x) {
        int n = nums.length, low = 0, high = n - 1, res = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == x)
                return mid;
            if (nums[mid] < x)
                low = mid + 1;
            else {
                res = mid;
                high = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int n = Integer.parseInt(temp[0]), x = Integer.parseInt(temp[1]);
        temp = br.readLine().trim().split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(temp[i]);
        int lb = lowerBound(nums, x), res = Integer.MAX_VALUE;
        if (lb == n)
            res = Math.min(res, x - nums[n - 1]);
        if (lb < n)
            res = Math.min(res, nums[lb] - x);
        if (lb >= 1)
            res = Math.min(res, Math.abs(x - nums[lb - 1]));
        System.out.println(res);
    }
}
