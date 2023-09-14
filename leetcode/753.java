/*
 * https://leetcode.com/problems/cracking-the-safe/
 */



/*
 * Approach of DFS from -> 
 * https://leetcode.com/problems/cracking-the-safe/solutions/110264/easy-dfs/
 */
class Solution {

    private boolean dfs(StringBuilder sb, int goal, Set<String> vis, int n, int k) {
        if (vis.size() == goal)
            return true;
        String prev = sb.substring(sb.length() - n + 1);
        for (int i = 0; i < k; i++) {
            String next = prev + i;
            if (!vis.contains(next)) {
                vis.add(next);
                sb.append(i);
                if (dfs(sb, goal, vis, n, k))
                    return true;
                vis.remove(next);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return false;
    }

    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int totalNumPasswords = (int)Math.pow(k, n);
        Random rand = new Random();
        for (int i = 0; i < n; i++)
            sb.append(rand(k));
        Set<String> vis = new HashSet<>();
        vis.add(sb.toString());
        dfs(sb, totalNumPasswords, vis, n, k);
        return sb.toString();
    }
}



/*
 * Approach and Deduction of Eulerian Circuit from -> 
 * https://leetcode.com/problems/cracking-the-safe/solutions/110264/easy-dfs/comments/215515
 */
class Solution {

    private void createPasswords(int n, int k, StringBuilder sb, List<String> list) {
        if (n == 0) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < k; i++) {
            sb.append(i);
            createPasswords(n - 1, k, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private void findEulerianPath(String node, List<String> list, Map<String, List<String> > graph) {
        while (graph.get(node).size() > 0) {
            String neighbor = graph.get(node).remove(0);
            findEulerianPath(neighbor, list, graph);
        }
        list.add(0, node);
    }

    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        if (n == 1) {
            for (int i = 0; i < k; i++)
                sb.append(i);
            return sb.toString();
        }
        List<String> passwords = new ArrayList<>();
        createPasswords(n - 1, k, new StringBuilder(), passwords);
        Map<String, List<String> > graph = new HashMap<>();
        for (String s : passwords) {
            graph.put(s, new LinkedList<>());
            String prev = s.substring(1);
            for (int i = 0; i < k; i++)
                graph.get(s).add(prev + i);
        }
        List<String> eulerianPath = new LinkedList<>();
        String start = passwords.get(0);
        findEulerianPath(start, eulerianPath, graph);
        sb.append(eulerianPath.get(0));
        for (int i = 1; i < eulerianPath.size(); i++)
            sb.append(eulerianPath.get(i).charAt(n - 2));
        return sb.toString();
    }
}
