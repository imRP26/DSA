/*
 * https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
 */



/*
 * Simple Ad-Hoc Solution Approach!
 */
class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer> > map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++)
            map.computeIfAbsent(groupSizes[i], k -> new ArrayList<>()).add(i);
        List<List<Integer> > res = new ArrayList<>();
        for (int x : map.keySet()) {
            List<Integer> list = map.get(x), temp = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                temp.add(list.get(i));
                if (temp.size() == x) {
                    res.add(new ArrayList<>(temp));
                    temp.clear();
                }
            }
        }
        return res;
    }
}



/*
 * Approach from LC Official Editorial - A better 1 pass solution!
 */
class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer> > res = new ArrayList<>();
        Map<Integer, List<Integer> > map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            if (!map.containsKey(groupSizes[i]))
                map.put(groupSizes[i], new ArrayList<>());
            List<Integer> list = map.get(groupSizes[i]);
            list.add(i);
            if (list.size() == groupSizes[i]) {
                res.add(new ArrayList<>(list));
                map.remove(groupSizes[i]);
            }
        }
        return res;
    }
}
