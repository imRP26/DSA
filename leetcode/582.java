/*
 * HashMap + DFS, HashMap + BFS
 */
class Solution {

    private void dfs(Map<Integer, List<Integer> > map, List<Integer> killedProcesses, int kill) {
        killedProcesses.add(kill);
        if (!map.containsKey(kill))
            return;
        List<Integer> list = map.get(kill);
        for (int childProcess : list)
            dfs(map, killedProcesses, childProcess);
    }

    private List<Integer> bfs(Map<Integer, List<Integer> > map, int kill) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> killedProcesses = new ArrayList<>();
        queue.offer(kill);
        while (!queue.isEmpty()) {
            kill = queue.poll();
            killedProcesses.add(kill);
            if (!map.containsKey(kill))
                continue;
            List<Integer> list = map.get(kill);
            for (int cp : list)
                queue.offer(cp);
        }
        return killedProcesses;
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer> > map = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            int parentProcess = ppid.get(i);
            if (parentProcess == 0)
                continue;
            if (!map.containsKey(parentProcess))
                map.put(parentProcess, new ArrayList<>());
            map.get(parentProcess).add(pid.get(i));
        }
        List<Integer> killedProcesses = new ArrayList<>();
        //dfs(map, killedProcesses, kill);
        //return killedProcesses;
        return bfs(map, kill);
    }
}
