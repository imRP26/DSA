import java.util.*;

/*
 * https://leetcode.com/problems/time-needed-to-inform-all-employees/
 */



// Classic BFS
class Solution1 {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if (n == 1)
            return informTime[headID];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            if (i == headID)
                continue;
            graph.get(manager[i]).add(i);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {headID, informTime[headID]});
        int result = 0;
        while (!queue.isEmpty()) {
            int[] emp = queue.poll();
            int empID = emp[0], infTime = emp[1];
            result = Math.max(result, infTime);
            for (int i = 0; i < graph.get(empID).size(); i++) {
                int j = graph.get(empID).get(i);
                queue.offer(new int[] {j, infTime + informTime[j]});
            }
        }
        return result;
    }
}



// Same approach but by using a different representation
class Solution2 {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int[] time = informTime.clone();
        List<Integer>[] subordinates = new List[n];
        for (int i = 0; i < n; i++)
            subordinates[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == headID)
                continue;
            subordinates[manager[i]].add(i);
        }
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(headID));
        int result = 0;
        while (!queue.isEmpty()) {
            int emp = queue.poll();
            if (manager[emp] >= 0)
                time[emp] += time[manager[emp]];
            result = Math.max(result, time[emp]);
            queue.addAll(subordinates[emp]);
        }
        return result;
    }
}



// DFS
class Solution3 {

    int dfs(int i, int[] manager, int[] informTime) {
        if (manager[i] != -1) {
            informTime[i] += dfs(manager[i], manager, informTime);
            manager[i] = -1;
        }
        return informTime[i];
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int result = 0;
        for (int i = 0; i < n; i++)
            result = Math.max(result, dfs(i, manager, informTime));
        return result;
    }
}
