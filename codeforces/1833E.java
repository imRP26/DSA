/*
 * https://codeforces.com/problemset/problem/1833/E
 */
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int nodes = Integer.parseInt(br.readLine().trim()), bamboos = 0, cycles = 0;
            String[] temp = br.readLine().trim().split(" ");
            Map<Integer, Set<Integer> > graph = new HashMap<>();
            for (int node = 1; node <= nodes; node++) {
                int neighbor = Integer.parseInt(temp[node - 1]);
                graph.computeIfAbsent(node, k -> new HashSet<>()).add(neighbor);
                graph.computeIfAbsent(neighbor, k -> new HashSet<>()).add(node);
            }
            int[] degree = new int[nodes + 1];
            for (int i = 1; i <= nodes; i++)
                degree[i] = graph.get(i).size();
            Set<Integer> vis = new HashSet<>();
            for (int i = 1; i <= nodes; i++) {
                if (!vis.contains(i)) {
                    Queue<Integer> q = new LinkedList<>();
                    q.offer(i);
                    vis.add(i);
                    List<Integer> component = new ArrayList<>();
                    component.add(i);
                    while (!q.isEmpty()) {
                        int node = q.poll();
                        for (int neighbor : graph.getOrDefault(node, Collections.emptySet())) {
                            if (!vis.contains(neighbor)) {
                                vis.add(neighbor);
                                q.offer(neighbor);
                                component.add(neighbor);
                            }
                        }
                    }
                    boolean isBamboo = false;
                    for (int v : component) {
                        if (degree[v] == 1) {
                            isBamboo = true;
                            break;
                        }
                    }
                    if (isBamboo)
                        bamboos++;
                    else 
                        cycles++;
                }
            }
            System.out.println((cycles + Math.min(1, bamboos)) + " " + (bamboos + cycles));
        }
    }
}
