/*
 * 
 */



/*
 * Unidirectional BFS Approach referenced from 
 * https://leetcode.com/problems/jump-game-iv/solutions/796466/jump-game-iv/
 */
class Solution1 {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer> > map = new HashMap<>();
        int n = arr.length, numSteps = 0;
        for (int i = 0; i < n; i++)
            map.computeIfAbsent(arr[i], val -> new ArrayList<>()).add(i);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        queue.offer(-1);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == n - 1)
                break;
            if (node == -1) {
                if (!queue.isEmpty())
                    queue.offer(-1);
                numSteps++;
                continue;
            }
            if (node >= 1 && !visited.contains(node - 1)) {
                queue.offer(node - 1);
                visited.add(node - 1);
            }
            if (node + 1 < n && !visited.contains(node + 1)) {
                queue.offer(node + 1);
                visited.add(node + 1);
            }
            for (int neighbor : map.get(arr[node])) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
            map.get(arr[node]).clear();
        }
        return numSteps;
    }
} 



/*
 * Bidirectional BFS Approach from 
 * https://leetcode.com/problems/jump-game-iv/solutions/796466/jump-game-iv/
 */
class Solution2 {
    public int minJumps(int[] arr) {
        int n = arr.length, minSteps = 0;
        if (n <= 1)
            return minSteps;
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.computeIfAbsent(arr[i], val -> new ArrayList<>()).add(i);
        Set<Integer> curs = new HashSet<>(); // layers from the start
        curs.add(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        visited.add(n - 1);
        Set<Integer> other = new HashSet<>(); // layers from the end
        other.add(n - 1);
        while (!curs.isEmpty()) {
            if (curs.size() > other.size()) {
                Set<Integer> temp = curs;
                curs = other;
                other = temp;
            }
            Set<Integer> next = new HashSet<>();
            for (int node : curs) {
                for (int child : graph.get(arr[node])) {
                    if (other.contains(child))
                        return 1 + minSteps;
                    if (!visited.contains(child)) {
                        visited.add(child);
                        next.add(child);
                    }
                }
                graph.get(arr[node]).clear();
                if (other.contains(node + 1) || other.contains(node - 1))
                    return 1 + minSteps;
                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    next.add(node + 1);
                }
                if (node >= 1 && !visited.contains(node - 1)) {
                    visited.add(node - 1);
                    next.add(node - 1);
                }
            }
            curs = next;
            minSteps++;
        }
        return -1;
    }
}
