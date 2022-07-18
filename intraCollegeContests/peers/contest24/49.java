import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/group-anagrams/
 */



// Simulation
class Solution {
    public List<List<String> > groupAnagrams(String[] strs) {
        if (strs.length == 0 || strs == null)
            return new ArrayList<>();
        Map<String, List<String> > map = new HashMap<>();
        for (String s : strs) {
            char[] chArr = s.toCharArray();
            Arrays.sort(chArr);
            String keyStr = String.valueOf(chArr);
            //String keyStr = new String(chArr);
            if (!map.containsKey(keyStr))
                map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());        
    }
}
