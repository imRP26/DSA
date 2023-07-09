/*
 * https://leetcode.com/problems/maximize-the-confusion-of-an-exam/
 */



/*
 * Simple Approach of 2 Pointers - take 2 cases each of flipping 'F' or 'T'
 */
class Solution {

    private int maxLength(String key, int k, char c) {
        int res = 0, low = 0, high = 0, unequal = 0;
        while (high < key.length()) {
            if (key.charAt(high) != c) {
                unequal++;
                while (low < high && unequal > k) {
                    if (key.charAt(low) != c) {
                        unequal--;
                        low++;
                        break;
                    }
                    low++;
                }
            }
            res = Math.max(res, high - low + 1);
            high++;   
        }
        return res;
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxLength(answerKey, k, 'T'), maxLength(answerKey, k, 'F'));
    }
}



/*
 * Approach of Binary Search + Sliding Window from LC Official Editorial
 */
class Solution {

    private boolean isValid(String ans, int k, int len) {
        int ct = 0, cf = 0;
        for (int i = 0; i < len; i++) {
            if (ans.charAt(i) == 'T')
                ct++;
            else
                cf++;
        }
        if (Math.min(ct, cf) <= k)
            return true;
        for (int i = len; i < ans.length(); i++) {
            if (ans.charAt(i - len) == 'T')
                ct--;
            else
                cf--;
            if (ans.charAt(i) == 'T')
                ct++;
            else
                cf++;
            if (Math.min(ct, cf) <= k)
                return true;
        }
        return false;
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {
        int res = 0, low = 1, high = answerKey.length();
        while (low <= high) {
            int mid = low +(high - low) / 2;
            if (isValid(answerKey, k, mid)) {
                res = mid;
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return res;
    }
}



/*
 * Approach of Sliding Window + Hashing from LC Official Editorial
 */
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int res = k, low = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++)
            map.put(answerKey.charAt(i), map.getOrDefault(answerKey.charAt(i), 0) + 1);
        for (int high = k; high < answerKey.length(); high++) {
            map.put(answerKey.charAt(high), map.getOrDefault(answerKey.charAt(high), 0) + 1);
            while (Math.min(map.getOrDefault('T', 0), map.getOrDefault('F', 0)) > k) {
                map.put(answerKey.charAt(low), map.get(answerKey.charAt(low)) - 1);
                low++;
            }
            res = Math.max(res, high - low + 1);
        }
        return res;
    }
}



/*
 * An improvement upon the previous approach from LC Official Editorial
 */
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int res = 0;
        Map<Character, Integer> counts = new HashMap<>();
        for (int high = 0; high < answerKey.length(); high++) {
            char c = answerKey.charAt(high);
            counts.put(c, counts.getOrDefault(c, 0) + 1);
            int minorCount = Math.min(counts.getOrDefault('T', 0), counts.getOrDefault('F', 0));
            if (minorCount <= k)
                res++;
            else {
                char ch = answerKey.charAt(high - res);
                counts.put(ch, counts.get(ch) - 1);
            }
    
        }
        return res;
    }
}
