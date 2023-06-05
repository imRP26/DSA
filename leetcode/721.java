/*
 * https://leetcode.com/problems/accounts-merge/
 */



/*
 * Checkout Striver's video for the explanation - but code is mine!
 * Approach of DSU!! - Proper CP Question...
 */
class Solution {

    private int[] parent, rank;

    private int findParent(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void unionOperation(int u, int v) {
        int uPar = findParent(u), vPar = findParent(v);
        if (uPar == vPar)
            return;
        if (rank[uPar] < rank[vPar]) {
            rank[vPar] += rank[uPar];
            parent[uPar] = parent[vPar];
        }
        else {
            rank[uPar] += rank[vPar];
            parent[vPar] = parent[uPar];
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> map = new HashMap<>();
        int n = accounts.size();
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        rank = new int[n];
        Arrays.fill(rank, 1);
        for (int i = 0; i < n; i++) {
            List<String> list = accounts.get(i);
            for (int j = 1; j < list.size(); j++) {
                String email = list.get(j);
                if (map.containsKey(email)) {
                    int k = map.get(email);
                    unionOperation(k, i);
                }
                else
                    map.put(email, i);
            }
        }
        Set<Integer> parents = new HashSet<>();
        for (int i = 0; i < n; i++)
            parent[i] = findParent(i);
        List<List<String> > temp = new ArrayList<>(), res = new ArrayList<>();
        for (int i = 0; i < n; i++)
            temp.add(new ArrayList<>());
        Set<String> seen = new HashSet<>();
        for (List<String> list : accounts) {
            String name = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String email = list.get(i);
                if (seen.contains(email))
                    continue;
                int j = parent[map.get(email)];
                if (temp.get(j).isEmpty())
                    temp.get(j).add(name);
                temp.get(j).add(email);
                seen.add(email);
            }
        }
        for (int i = 0; i < n; i++) {
            List<String> list1 = temp.get(i), list2 = new ArrayList<>();
            if (list1.isEmpty())
                continue;
            for (int j = 1; j < list1.size(); j++)
                list2.add(list1.get(j));
            Collections.sort(list2);
            list2.add(0, list1.get(0));
            res.add(new ArrayList<>(list2));
        }
        return res;
    }
}
