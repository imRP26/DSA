/*
 * https://codeforces.com/problemset/problem/1593/E
 */
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String blank = br.readLine();
            String[] temp = br.readLine().trim().split(" ");
            int n = Integer.parseInt(temp[0]), k = Integer.parseInt(temp[1]), res = 0, numOp = 1;
            int[] degree = new int[n + 1], opNum = new int[n + 1];
            Map<Integer, List<Integer> > graph = new HashMap<>();
            for (int i = 1; i < n; i++) {
                temp = br.readLine().trim().split(" ");
                int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
                graph.computeIfAbsent(u, k1 -> new ArrayList<>()).add(v);
                graph.computeIfAbsent(v, k1 -> new ArrayList<>()).add(u);
                degree[u]++;
                degree[v]++;
            }
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (degree[i] <= 1) {
                    q.offer(i);
                    degree[i] -= 1;
                }
            }
            Set<Integer> vis = new HashSet<>();
            q.offer(-1); 
            while (!q.isEmpty()) {
                int u = q.poll();
                if (u == -1) {
                    if (!q.isEmpty())
                        q.offer(-1);
                    numOp++;
                    continue;
                }
                if (vis.contains(u))
                    continue;
                vis.add(u);
                opNum[u] = numOp;
                for (int v : graph.getOrDefault(u, Collections.emptyList())) {
                    if (!vis.contains(v) && --degree[v] <= 1)
                        q.offer(v);
                }
            }
            for (int i = 1; i <= n; i++)
                res += opNum[i] > k ? 1 : 0;
            System.out.println(res);
        }
    }
}
