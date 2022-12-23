import java.io.*;
import java.lang.*;
import java.util.*;


/*
 * DSU - 1600
 */
public class Codeforces {

    public static PrintWriter out; //PrintWriter for faster output

    public static class FastScanner { //MyScanner class for faster input
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        public FastScanner(String s) {
            try {
                br = new BufferedReader(new FileReader(s));    
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static boolean bfs(int src, Map<Integer, List<Integer> > graph, int[] nodeColor) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> colors = new HashSet<>();
        for (int x : graph.get(src)) {
            /*
             * Here, we need to ensure that for a particular vertex 'src', we check 
             * each of its individual subtrees, as to whether they contain nodes 
             * having 2 different colors or not.
             */
            Queue<Integer> queue = new LinkedList<>();
            visited.clear();
            colors.clear();
            queue.offer(x);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                if (visited.contains(node) || node == src)
                    continue;
                visited.add(node);
                colors.add(nodeColor[node]);
                if (colors.size() > 1)
                    return false;
                for (int neighbor : graph.get(node)) {
                    if (!visited.contains(neighbor))
                        queue.offer(neighbor);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws java.lang.Exception {
        FastScanner sc = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        
        //starting the solution below...
        int numNodes = sc.nextInt();
        Map<Integer, List<Integer> > graph = new HashMap<>();
        List<int[]> edges = new ArrayList<>();
        for (int i = 1; i < numNodes; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            graph.computeIfAbsent(u, val -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, val -> new ArrayList<>()).add(u);
            edges.add(new int[] {u, v});
        }
        int[] nodeColor = new int[numNodes + 1];
        for (int i = 1; i <= numNodes; i++)
            nodeColor[i] = sc.nextInt();
        boolean flag = false;
        int nodeResult = 1, sameColorEdges = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (nodeColor[u] == nodeColor[v]) {
                sameColorEdges++;
                continue;
            }
            boolean bfsU = bfs(u, graph, nodeColor), bfsV = bfs(v, graph, nodeColor);
            if (!bfsU && !bfsV)
                break;
            if (bfsU) {
                flag = true;
                nodeResult = u;
                break;
            }
            else if (bfsV) {
                flag = true;
                nodeResult = v;
                break;
            }
        }
        if (sameColorEdges == numNodes - 1)
            flag = true;
        if (!flag)
            out.println("NO");
        else {
            out.println("YES");
            out.println(nodeResult);
        }
        //finishing the solution code here...
        
        out.close(); 
    }
}
