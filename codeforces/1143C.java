/*
 * https://codeforces.com/problemset/problem/1143/C
 */
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(br.readLine().trim()), root = 0;
        int[] parent = new int[nodes + 1], respect = new int[nodes + 1];
        List<Integer> res = new ArrayList<>();
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int i = 1; i <= nodes; i++) {
            String[] temp = br.readLine().trim().split(" ");
            parent[i] = Integer.parseInt(temp[0]);
            respect[i] = 1 - Integer.parseInt(temp[1]);
            if (parent[i] != -1)
                graph.computeIfAbsent(parent[i], k -> new ArrayList<>()).add(i);
            else 
                root = i;
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int node = q.poll(), sum = respect[node];
            for (int child : graph.getOrDefault(node, Collections.emptyList())) {
                sum += respect[child];
                q.offer(child);
            }
            if (sum == 0)
                res.add(node);
        }
        if (res.size() > 0) {
            Collections.sort(res);
            for (int x : res)
                System.out.print(x + " ");
        }
        else 
            System.out.print(-1);
    }
}
