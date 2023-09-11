/*
 * https://thejoboverflow.com/problem/155/
 */



/*
 * Simple AdHoc based solution!
 */
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim()), res = 0;
            int[] arr = new int[n + 2];
            String[] temp = br.readLine().trim().split(" ");
            for (int i = 1; i <= n; i++)
                arr[i] = Integer.parseInt(temp[i - 1]);
            for (int i = 1; i <= n; i++)
                res += (2 * arr[i] == arr[i - 1] + arr[i + 1]) ? 1 : 0;
            System.out.println(res);
        }
    }
}
