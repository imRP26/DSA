/*
 * https://codeforces.com/contest/208/problem/E
 */



// Approach referred from -> https://www.youtube.com/watch?v=UeTzHb0RBYs
import java.io.*;
import java.util.*;


public class Main {

    private static int numIter, timer = 0;
    private static int[] nodeLevel, inTime, outTime;
    private static Map<Integer, List<Integer> > graph = new HashMap<>(), heightMap = new HashMap<>();
    private static int[][] kthParent;
    private static Set<Integer> vis = new HashSet<>();

    private static void dfs(int node, int parent, int level) {
        if (!vis.add(node))
            return;
        nodeLevel[node] = level;
        inTime[node] = timer++;
        heightMap.computeIfAbsent(level, k -> new ArrayList<>()).add(inTime[node]);
        kthParent[0][node] = parent;
        for (int child : graph.getOrDefault(node, Collections.emptyList())) {
            if (!vis.contains(child))
                dfs(child, node, level + 1);
        }
        outTime[node] = timer++;
        heightMap.computeIfAbsent(level, k -> new ArrayList<>()).add(outTime[node]);
    }

    private static int query(int node, int k) {
        for (int i = 0; i <= numIter; i++) {
            if ((k & (1 << i)) != 0) {
                node = kthParent[i][node];
                if (node == 0)
                    break;
            }
        }
        return node;
    }

    private static int lowerBound(int x, List<Integer> list) {
        int low = 0, high = list.size() - 1, ans = high + 1;
        while (low <= high) {
            int mid = low + (high - low) / 2, val = list.get(mid);
            if (val < x)
                low = mid + 1;
            else {
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(br.readLine().trim());
        String[] temp = br.readLine().trim().split(" ");
        for (int i = 1; i <= nodes; i++) {
            int parent = Integer.parseInt(temp[i - 1]);
            graph.computeIfAbsent(parent, k -> new ArrayList<>()).add(i);
        }
        int q = Integer.parseInt(br.readLine().trim());
        numIter = 1 + (int)(Math.log(nodes) / Math.log(2));
        kthParent = new int[numIter + 1][1 + nodes];
        nodeLevel = new int[nodes + 1];
        inTime = new int[nodes + 1];
        outTime = new int[nodes + 1];
        for (int[] row : kthParent)
            Arrays.fill(row, 0);
        for (int i = 1; i <= nodes; i++) {
            if (!vis.contains(i))
                dfs(i, 0, 0);
        }
        for (int k = 1; k <= numIter; k++) {
            for (int node = 1; node <= nodes; node++) {
                int intermediate = kthParent[k - 1][node];
                if (intermediate != 0)
                    kthParent[k][node] = kthParent[k - 1][intermediate];
            }
        }
        while (q-- > 0) {
            temp = br.readLine().trim().split(" ");
            int node = Integer.parseInt(temp[0]), k = Integer.parseInt(temp[1]), res = 0, h = nodeLevel[node];
            if (h >= k) {
                int kPar = query(node, k);
                List<Integer> list = heightMap.get(h);
                res = (lowerBound(outTime[kPar], list) - lowerBound(inTime[kPar], list)) / 2 - 1;
            }
            System.out.print(res + " ");
        }
    }
}
