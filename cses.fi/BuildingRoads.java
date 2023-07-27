/*
 * https://cses.fi/problemset/task/1666/
 */
import java.io.*;
import java.util.*;


public class BuildingRoads {

    private static Map<Integer, List<Integer> > graph = new HashMap<>();
    private static Set<Integer> vis = new HashSet<>();

    private static void dfs(int node) {
        if (!vis.add(node))
            return;
        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!vis.contains(neighbor))
                dfs(neighbor);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int n = Integer.parseInt(temp[0]), m = Integer.parseInt(temp[1]);
        while (m-- > 0) {
            temp = br.readLine().trim().split(" ");
            int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (vis.contains(i))
                continue;
            list.add(i);
            dfs(i);
        }
        n = list.size();
        System.out.println(n - 1);
        for (int i = 1; i < n; i++)
            System.out.println(list.get(i - 1) + " " + list.get(i));
    }
}
