/*
 * Generic graph construction and then BFS
 */
class Solution1 {

    private int bfs(int src, int nodes, Map<Integer, List<Integer> > map) {
        boolean[] visited = new boolean[nodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        int num = 0;
        while (!queue.isEmpty()) {
            src = queue.poll();
            if (visited[src])
                continue;
            visited[src] = true;
            num++;
            if (!map.containsKey(src))
                continue;
            List<Integer> neighbors = map.get(src);
            for (int neighbor : neighbors) {
                if (!visited[neighbor])
                    queue.offer(neighbor);
            }
        }
        return num;
    }

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length, result = 1;
        Map<Integer, List<Integer> > map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x1 = bombs[i][0], y1 = bombs[i][1], r1 = bombs[i][2];
            for (int j = i + 1; j < n; j++) {
                int x2 = bombs[j][0], y2 = bombs[j][1], r2 = bombs[j][2];
                float distance = (float)Math.sqrt(1.0 * (x1 - x2) * (x1 - x2) + 1.0 * (y1 - y2) * (y1 - y2));
                if (r1 * 1.0 >= distance) {
                    if (!map.containsKey(i))
                        map.put(i, new ArrayList<>());
                    map.get(i).add(j);
                }
                if (r2 * 1.0 >= distance) {
                    if (!map.containsKey(j))
                        map.put(j, new ArrayList<>());
                    map.get(j).add(i);
                }
            }
        }
        for (int i = 0; i < n; i++)
            result = Math.max(result, bfs(i, n, map));
        return result;
    }
}


/*
 * Same bloody way of doing the above thing
 */
class Solution2 {

    private int bfs(int src, int nodes, Map<Integer, List<Integer> > map) {
        boolean[] visited = new boolean[nodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        int num = 0;
        while (!queue.isEmpty()) {
            src = queue.poll();
            if (visited[src])
                continue;
            visited[src] = true;
            num++;
            if (!map.containsKey(src))
                continue;
            List<Integer> neighbors = map.get(src);
            for (int neighbor : neighbors) {
                if (!visited[neighbor])
                    queue.offer(neighbor);
            }
        }
        return num;
    }

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length, result = 1;
        Map<Integer, List<Integer> > map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long x1 = (long)bombs[i][0], y1 = (long)bombs[i][1], r1 = (long)bombs[i][2];
            for (int j = i + 1; j < n; j++) {
                long x2 = (long)bombs[j][0], y2 = (long)bombs[j][1], r2 = (long)bombs[j][2];
                long sqDist = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                if (r1 * r1 >= sqDist) {
                    if (!map.containsKey(i))
                        map.put(i, new ArrayList<>());
                    map.get(i).add(j);
                }
                if (r2 * r2 >= sqDist) {
                    if (!map.containsKey(j))
                        map.put(j, new ArrayList<>());
                    map.get(j).add(i);
                }
            }
        }
        for (int i = 0; i < n; i++)
            result = Math.max(result, bfs(i, n, map));
        return result;
    }
}
