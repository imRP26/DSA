/*
 * https://cses.fi/problemset/task/1137/
 */
import java.io.*;
import java.util.*;


public class SubtreeQueries {

    private static int timer = 1;
    private static int[] nodeVal, inTime, outTime;
    private static long[] segtree;
    private static Map<Integer, List<Integer> > graph = new HashMap<>();
    private static List<Integer> eulerTour = new ArrayList<>();

    private static void dfs(int node, int parent) {
        inTime[node] = timer++;
        eulerTour.add(nodeVal[node]);
        for (int child : graph.getOrDefault(node, Collections.emptyList())) {
            if (child != parent)
                dfs(child, node);
        }
        outTime[node] = timer++;
        eulerTour.add(nodeVal[node]);
    }

    private static void buildSegmentTree(int nodeIdx, int low, int high) {
        if (low == high)
            segtree[nodeIdx] = eulerTour.get(low);
        else {
            int mid = low + (high - low) / 2, leftChildIdx = 2 * nodeIdx, rightChildIdx = 2 * nodeIdx + 1;
            buildSegmentTree(leftChildIdx, low, mid);
            buildSegmentTree(rightChildIdx, mid + 1, high);
            segtree[nodeIdx] = segtree[leftChildIdx] + segtree[rightChildIdx];
        }
    }

    private static void updateSegmentTree(int nodeIdx, int low, int high, int pos, long newVal) {
        if (low == high)
            segtree[nodeIdx] = newVal;
        else {
            int mid = low + (high - low) / 2, leftChildIdx = 2 * nodeIdx, rightChildIdx = 2 * nodeIdx + 1;
            if (pos <= mid)
                updateSegmentTree(leftChildIdx, low, mid, pos, newVal);
            else 
                updateSegmentTree(rightChildIdx, mid + 1, high, pos, newVal);
            segtree[nodeIdx] = segtree[leftChildIdx] + segtree[rightChildIdx];            
        }
    }

    private static long sumQuery(int nodeIdx, int low, int high, int left, int right) {
        if (left > right)
            return 0;
        if (left == low && right == high)
            return segtree[nodeIdx];    
        int mid = low + (high - low) / 2;
        long leftNodeSum = sumQuery(2 * nodeIdx, low, mid, left, Math.min(right, mid));
        long rightNodeSum = sumQuery(2 * nodeIdx + 1, mid + 1, high, Math.max(left, mid + 1), right);
        return leftNodeSum + rightNodeSum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int nodes = Integer.parseInt(temp[0]), queries = Integer.parseInt(temp[1]);
        nodeVal = new int[nodes + 1];
        inTime = new int[nodes + 1];
        outTime = new int[nodes + 1];
        segtree = new long[8 * nodes + 2];
        temp = br.readLine().trim().split(" ");
        for (int i = 1; i <= nodes; i++)
            nodeVal[i] = Integer.parseInt(temp[i - 1]);
        for (int i = 1; i < nodes; i++) {
            temp = br.readLine().trim().split(" ");
            int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        eulerTour.add(0);
        dfs(1, 0);
        buildSegmentTree(1, 1, 2 * nodes);
        while (queries-- > 0) {
            temp = br.readLine().trim().split(" ");
            if (temp.length == 3) {
                int node = Integer.parseInt(temp[1]), newVal = Long.parseLong(temp[2]);
                updateSegmentTree(1, 1, 2 * nodes, inTime[node], newVal);
                updateSegmentTree(1, 1, 2 * nodes, outTime[node], newVal);
            }
            else {
                int node = Integer.parseInt(temp[1]);
                long res = sumQuery(1, 1, 2 * nodes, inTime[node], outTime[node]) / 2;
                System.out.println(res);
            }
        }
    }
}
