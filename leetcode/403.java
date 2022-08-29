import java.util.*;

/*
 * https://leetcode.com/problems/frog-jump/
 */



// My own solution, took about 1 hour, but really, really proud nevertheless!
class Solution1 {
    public boolean canCross(int[] stones) {
        int numStones = stones.length, lastStonePos = stones[numStones - 1];
        if (numStones == 1) // trivial case, the frog needs no jump
            return true;
        // since the frog is initially at position 0 and assumes that the 1st jump is of length 1
        if (stones[1] != 1)
            return false;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int stone : stones) // O(N)
            map.put(stone, new HashSet<>());
        Set<Integer> temp = map.get(1);
        // By default, the frog jumps to stones[1] from stones[0] with a jump length of 1
        temp.add(1);
        map.put(1, temp);
        for (int i = 1; i < numStones; i++) { // O(N)
            // this set contains lengths of jumps that were taken to arrive at this stones[i] position
            temp = map.get(stones[i]);
            if (temp.isEmpty())
                continue;
            for (int k : temp) {
                int pos1 = stones[i] + k - 1, pos2 = stones[i] + k, pos3 = stones[i] + k + 1;
                if (pos1 > stones[i] && pos1 <= lastStonePos && map.containsKey(pos1)) {
                    Set<Integer> set = map.get(pos1);
                    set.add(k - 1);
                    map.put(pos1, set);
                }
                if (pos2 > stones[i] && pos2 <= lastStonePos && map.containsKey(pos2)) {
                    Set<Integer> set = map.get(pos2);
                    set.add(k);
                    map.put(pos2, set);
                }
                if (pos3 > stones[i] && pos3 <= lastStonePos && map.containsKey(pos3)) {
                    Set<Integer> set = map.get(pos3);
                    set.add(k + 1);
                    map.put(pos3, set);
                }
            }
        }
        temp = map.get(lastStonePos);
        return !temp.isEmpty();
    }
}
