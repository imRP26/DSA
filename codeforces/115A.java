import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
    
    private static int helper(int x, int[] parent) {
        int ans = 1;
        while (x != parent[x]) {
            x = parent[x];
            ans++;
        }
        return ans;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(br.readLine()), res = Integer.MIN_VALUE;
        int[] parent = new int[nodes + 1];
        for (int i = 1; i <= nodes; i++) {
            int par = Integer.parseInt(br.readLine());
            parent[i] = (par == -1) ? i : par;
        }
        for (int i = 1; i <= nodes; i++)
            res = Math.max(res, helper(i, parent));
        System.out.println(res); 
    }
}
