/*
 * https://leetcode.com/problems/reorganize-string/
 */



/*
 * Approach of PriorityQueue (Greedy) from LC Official Editorial!
 * If the 1st char popped off from the maxPQ was same as the last appended char, 
 * then try popping off another char from the maxPQ, append this 2nd char to the 
 * StringBuilder() and re-insert the 1st and 2nd chars into the maxPQ, condition 
 * being that their individual counts are > 0.
 */
class Solution {
    public String reorganizeString(String s) {
        PriorityQueue<int[]> maxPQ = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        int[] charCounts = new int[26];
        for (char c : s.toCharArray())
            charCounts[c - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (charCounts[i] > 0)
                maxPQ.offer(new int[] {i, charCounts[i]});
        }
        StringBuilder sb = new StringBuilder();
        while (!maxPQ.isEmpty()) {
            int[] temp1 = maxPQ.poll();
            int pos1 = temp1[0], count1 = temp1[1];
            char c1 = (char)(pos1 + 'a');
            if (sb.length() == 0 || c1 != sb.charAt(sb.length() - 1)) {
                sb.append(c1);
                if (--count1 > 0)
                    maxPQ.offer(new int[] {pos1, count1});
            }
            else {
                if (maxPQ.isEmpty())
                    return "";
                int[] temp2 = maxPQ.poll();
                int pos2 = temp2[0], count2 = temp2[1];
                char c2 = (char)('a' + pos2);
                sb.append(c2);
                if (--count2 > 0)
                    maxPQ.offer(new int[] {pos2, count2});
                maxPQ.offer(new int[] {pos1, count1});
            }
        }
        return sb.toString();
    }
}



/*
 * Simple Ad-Hoc Approach from LC Official Editorial!
 */
class Solution {
    public String reorganizeString(String s) {
        int[] charCounts = new int[26];
        for (char c : s.toCharArray())
            charCounts[c - 'a']++;
        int maxFreq = 0, len = s.length(), maxFreqChar = 0;
        for (int i = 0; i < 26; i++) {
            if (charCounts[i] > maxFreq) {
                maxFreq = charCounts[i];
                maxFreqChar = i;
            }
        }
        if (maxFreq > len / 2)
            return "";
        char[] res = new char[len];
        int idx = 0;
        while (charCounts[maxFreqChar] != 0) {
            res[idx] = (char)(maxFreqChar + 'a');
            idx += 2;
            charCounts[maxFreqChar]--;
        }
        for (int i = 0; i < 26; i++) {
            while (charCounts[i] > 0) {
                if (idx >= len)
                    idx = 1;
                res[idx] = (char)(i + 'a');
                idx += 2;
                charCounts[i]--;
            }
        }
        return String.valueOf(res);
    }
}
