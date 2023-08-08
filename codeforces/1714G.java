/*
 * https://codeforces.com/problemset/problem/1714/G
 */
import java.io.*;
import java.util.*;


public class Main {

    private static int[] res;
    private static Map<Integer, List<int[]> > graph = new HashMap<>();

    private static int lowerBound(long x, List<Long> list) {
        int low = 0, high = list.size() - 1, res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) == x)
                return mid + 1;
            if (list.get(mid) > x) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res == -1 ? list.size() : res;
    }

    private static void dfs(int node, long ai, List<Long> list) {
        for (int[] temp : graph.getOrDefault(node, Collections.emptyList())) {
            int neighbor = temp[0], a = temp[1], b = temp[2];
            ai += a;
            if (list.size() == 0)
                list.add((long)b);
            else 
                list.add(list.get(list.size() - 1) + (long)b);
            res[neighbor] = lowerBound(ai, list);
            dfs(neighbor, ai, list);
            list.remove(list.size() - 1);
            ai -= a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int nodes = Integer.parseInt(br.readLine().trim());
            graph.clear();
            res = new int[nodes + 1];
            for (int i = 2; i <= nodes; i++) {
                String[] temp = br.readLine().trim().split(" ");
                int par = Integer.parseInt(temp[0]), ai = Integer.parseInt(temp[1]), bi = Integer.parseInt(temp[2]);
                graph.computeIfAbsent(par, k -> new ArrayList<>()).add(new int[] {i, ai, bi});
            }
            dfs(1, 0, new ArrayList<>());
            for (int i = 2; i <= nodes; i++)
                System.out.print(res[i] + " ");
            System.out.println();
        }
    }  
}
