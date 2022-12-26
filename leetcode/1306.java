/*
 * https://leetcode.com/problems/jump-game-iii/
 */



/*
 * Simple BFS, no graph construction required
 */
class Solution1 {
    public boolean canReach(int[] arr, int start) {
        Map<Integer, List<Integer> > graph = new HashMap<>();
        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            start = queue.poll();
            if (arr[start] == 0)
                return true;
            if (visited.contains(start))
                continue;
            visited.add(start);
            int node1 = start - arr[start], node2 = start + arr[start];
            if (node1 >= 0 && !visited.contains(node1))
                queue.offer(node1);
            if (node2 < n && !visited.contains(node2))
                queue.offer(node2);
        }
        return false;
    }
}



/*
 * Simple DFS, no graph construction required
 */
class Solution2 {
    public boolean canReach(int[] arr, int start) {
        if (start >= 0 && start < arr.length && arr[start] >= 0) {
            if (arr[start] == 0)
                return true;
            arr[start] = -arr[start];
            return canReach(arr, start - arr[start]) || canReach(arr, start + arr[start]);
        }
        return false;
    }
}
