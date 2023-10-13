/*
 * https://codeforces.com/problemset/problem/1540/A
 */
// Approach from -> https://www.youtube.com/watch?v=4D65uGUMtPk
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] temp = br.readLine().trim().split(" ");
            long[] dist = new long[n];
            for (int i = 0; i < n; i++)
                dist[i] = Long.parseLong(temp[i]);
            Arrays.sort(dist);
            long res = dist[n - 1]; // sum of all +ve roads
            long[] reversedDist = new long[n]; // sum of all -ve roads emanating out of node i
            for (int i = 1; i < n; i++) {
                // take a pen and paper and fucking get to how the below formula is derived!
                reversedDist[i] = reversedDist[i - 1] + i * (dist[i] - dist[i - 1]);
                res -= reversedDist[i];
            }
            System.out.println(res);
        }
    }
}
