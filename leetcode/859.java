/*
 * 
 */



/*
 * Good Solution -> 
 * https://leetcode.com/problems/buddy-strings/solutions/141780/easy-understood/
 */
class Solution {
    public boolean buddyStrings(String s, String goal) {
        int slen = s.length(), glen = goal.length();
        if (slen != glen)
            return false;
        if (s.equals(goal)) {
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) {
                if (set.contains(c))
                    return true;
                set.add(c);
            }
            return false;
        }
        List<Integer> diffs = new ArrayList<>();
        for (int i = 0; i < slen; i++) {
            if (s.charAt(i) != goal.charAt(i))
                diffs.add(i);
        }
        return diffs.size() == 2 && s.charAt(diffs.get(0)) == goal.charAt(diffs.get(1)) && s.charAt(diffs.get(1)) == goal.charAt(diffs.get(0));
    }
}
