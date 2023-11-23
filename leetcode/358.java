/*
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/
 */



/*
 * My Approach after I initially got the hint that I've got to insert chars and their corresponding 
 * counts dynamically, after reading 1/2way through LC's official editorial!
 * But was unable to grasp the actual reasoning for insertion of chars into the maxPQ dynamically!
 * But lemme take an attempt here.
 * So after the insertion of a char, now I have to insert the new config of chars into the string 
 * of length (len - 1), while simulataneously taking care of the conditions!!
 * Also the lastPos map is essential for storing chars in indices while taking into due consideration 
 * their previously inserted positions!
 */
class Solution {
    public String rearrangeString(String s, int k) {
        int len = s.length();
        char[] res = new char[len];
        Map<Character, Integer> counts = new HashMap<>(), lastPos = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
            lastPos.put(c, -1);
        }
        PriorityQueue<int[]> maxPQ = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (char c : counts.keySet())
            maxPQ.offer(new int[] {c - 'a', counts.get(c)});
        Set<Integer> indices = new HashSet<>();
        while (!maxPQ.isEmpty()) {
            int[] arr = maxPQ.poll();
            char c = (char)(arr[0] + 'a');
            int idx = lastPos.get(c), cnt = arr[1];
            if (idx == -1) {
                while (idx == -1 || indices.contains(idx))
                    idx++;
            }
            else {
                idx += k;
                while (indices.contains(idx))
                    idx++;
            }
            if (idx >= len)
                return "";
            res[idx] = c;
            indices.add(idx);
            lastPos.put(c, idx); // dynamic insertion of char and charCounts
            if (--cnt > 0)
                maxPQ.offer(new int[] {c - 'a', cnt});
        }   
        return String.valueOf(res);
    }
}



/*
 * Approach of PriorityQueue from LC Official Editorial!
 */

