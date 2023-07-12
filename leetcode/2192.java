/*
 * https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/
 */



/*
 * Simple BFS Approach that did manage to get AC!! :D
 */
class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer> > res = new ArrayList<>();
        Map<Integer, Set<Integer> > graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            res.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, k -> new HashSet<>()).add(v);
        }
        Set<Integer> vis = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Queue<Integer> q = new LinkedList<>();
            vis.clear();
            q.offer(i);
            while (!q.isEmpty()) {
                int node = q.poll();
                if (vis.contains(node))
                    continue;
                vis.add(node);
                if (node != i)
                    res.get(node).add(i);
                for (int j : graph.getOrDefault(node, Collections.emptySet())) {
                    if (!vis.contains(j))
                        q.offer(j);
                }
            }
        }
        for (int i = 0; i < n; i++)
            Collections.sort(res.get(i));
        return res;
    }
}



/*
 * Same concept as above, but just reversed the edges of the original graph!
 */
class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer> > res = new ArrayList<>();
        Map<Integer, Set<Integer> > graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            res.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(v, k -> new HashSet<>()).add(u);
        }
        Set<Integer> vis = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Queue<Integer> q = new LinkedList<>();
            vis.clear();
            q.offer(i);
            while (!q.isEmpty()) {
                int node = q.poll();
                if (vis.contains(node))
                    continue;
                vis.add(node);
                if (node != i)
                    res.get(i).add(node);
                for (int j : graph.getOrDefault(node, Collections.emptySet())) {
                    if (!vis.contains(j))
                        q.offer(j);
                }
            }
        }
        for (int i = 0; i < n; i++)
            Collections.sort(res.get(i));
        return res;
    }
}



/*
 * Approach of Topological Sorting from -> 
 * https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/solutions/1821991/java-python-3-2-codes-topological-sort-dfs-w-brief-explanation-and-comments/
 */
class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        int[] indegree = new int[n];
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            indegree[v]++;
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }
        List<Set<Integer> > sets = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            sets.add(new HashSet<>());
            if (indegree[i] == 0)
                q.offer(i);
        }
        while (!q.isEmpty()) {
            int parent = q.poll();
            for (int child : graph.getOrDefault(parent, Arrays.asList())) {
                sets.get(child).add(parent);
                sets.get(child).addAll(sets.get(parent));
                if (--indegree[child] == 0)
                    q.offer(child);
            }
        }
        List<List<Integer> > res = new ArrayList<>();
        for (Set<Integer> set : sets)
            res.add(new ArrayList<>(new TreeSet<>(set)));
        return res;
    }
}



/*
 * Will try out the DFS method later on, at this point, it just seems a tad needless!
 */
