/*
 * https://leetcode.com/problems/keys-and-rooms/
 */



// Simple BFS 
class Solution1 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int key = queue.poll();
            if (visited.contains(key))
                continue;
            visited.add(key);
            List<Integer> keysToRooms = rooms.get(key);
            for (int room : keysToRooms) {
                if (!visited.contains(room))
                    queue.offer(room);
            }
        }
        return visited.size() == n;
    }
}



// Simple DFS
class Solution2 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int key = stack.pop();
            if (visited.contains(key))
                continue;
            visited.add(key);
            List<Integer> keysToRooms = rooms.get(key);
            for (int room : keysToRooms) {
                if (!visited.contains(room))
                    stack.push(room);
            }
        }
        return visited.size() == n;
    }
}
