/*
 * https://cses.fi/problemset/task/1138/
 */
import java.io.*;
import java.util.*;


public class PathQueries {

    private static int timer = 1;
    private static int[] nodeVal, inTime, outTime;
    private static long[] segtree;
    private static List<Integer> eulerTour = new ArrayList<>();
    private static Map<Integer, List<Integer> > graph = new HashMap<>();

    private static void dfs(int node, int parent) {
        inTime[node] = timer++;
        eulerTour.add(nodeVal[node]);
        for (int child : graph.getOrDefault(node, Collections.emptyList())) {
            if (child != parent)
                dfs(child, node);
        }
        outTime[node] = timer++;
        eulerTour.add(-nodeVal[node]);
    }

    private static void buildSegmentTree(int nodeIdx, int low, int high) {
        if (low == high)
            segtree[nodeIdx] = eulerTour.get(low);
        else {
            int mid = low + (high - low) / 2, leftChild = 2 * nodeIdx, rightChild = 2 * nodeIdx + 1;
            buildSegmentTree(leftChild, low, mid);
            buildSegmentTree(rightChild, mid + 1, high);
            segtree[nodeIdx] = segtree[leftChild] + segtree[rightChild];
        }
    }

    private static void updateSegmentTree(int nodeIdx, int low, int high, int pos, int newVal) {
        if (low == high)
            segtree[nodeIdx] = newVal;
        else {
            int mid = low + (high - low) / 2, leftChild = 2 * nodeIdx, rightChild = 2 * nodeIdx + 1;
            if (pos <= mid)
                updateSegmentTree(leftChild, low, mid, pos, newVal);
            else 
                updateSegmentTree(rightChild, mid + 1, high, pos, newVal);
            segtree[nodeIdx] = segtree[leftChild] + segtree[rightChild];
        }
    }

    private static long sumQuery(int nodeIdx, int low, int high, int left, int right) {
        if (left > right)
            return 0;
        if (left == low && right == high)
            return segtree[nodeIdx];
        int mid = low + (high - low) / 2;
        long leftSum = sumQuery(2 * nodeIdx, low, mid, left, Math.min(right, mid));
        long rightSum = sumQuery(2 * nodeIdx + 1, mid + 1, high, Math.max(left, mid + 1), right);
        return leftSum + rightSum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().trim().split(" ");
        int nodes = Integer.parseInt(temp[0]), queries = Integer.parseInt(temp[1]);
        nodeVal = new int[nodes + 1];
        inTime = new int[nodes + 1];
        outTime = new int[nodes + 1];
        segtree = new long[8 * nodes + 2];
        eulerTour.add(0);
        temp = br.readLine().trim().split(" ");
        for (int i = 1; i <= nodes; i++)
        	nodeVal[i] = Integer.parseInt(temp[i - 1]);
        for (int i = 1; i < nodes; i++) {
            temp = br.readLine().trim().split(" ");
            int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        dfs(1, 0);
        buildSegmentTree(1, 1, 2 * nodes);
        while (queries-- > 0) {
            temp = br.readLine().trim().split(" ");
            if (temp.length == 3) {
                int node = Integer.parseInt(temp[1]), newVal = Integer.parseInt(temp[2]);
                updateSegmentTree(1, 1, 2 * nodes, inTime[node], newVal);
                updateSegmentTree(1, 1, 2 * nodes, outTime[node], -newVal);
            }
            else {
                int node = Integer.parseInt(temp[1]);
                long res = sumQuery(1, 1, 2 * nodes, 1, inTime[node]);
                System.out.println(res);
            }
        }
    }
}
