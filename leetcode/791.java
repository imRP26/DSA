/*
 * https://leetcode.com/problems/custom-sort-string/
 */



/*
 * My Naive Solution!
 */
class Solution {
    public String customSortString(String order, String s) {
        int orderLen = order.length(), sLen = s.length();
        StringBuilder sb = new StringBuilder();
        Set<Integer> indicesTaken = new HashSet<>();
        Set<Character> orderChars = new HashSet<>();
        for (char c : order.toCharArray())
            orderChars.add(c);
        for (int i = 0; i < orderLen; i++) {
            char orderChar = order.charAt(i);
            for (int j = 0; j < sLen; j++) {
                if (indicesTaken.contains(j))
                    continue;
                char sChar = s.charAt(j);
                if (sChar == orderChar || !orderChars.contains(sChar)) {
                    sb.append(sChar);
                    indicesTaken.add(j);
                }
            }
        }
        return sb.toString();
    }
}



/*
 * Okay now I feel really, really bad for myself! Self-loathing, self-pitying....
 * Solution from -> 
 * https://leetcode.com/problems/custom-sort-string/solutions/116615/java-python-3-one-java-two-python-solutions-w-comment/
 */
class Solution {
    public String customSortString(String order, String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray())
            charCount[c - 'a']++;
        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            while (charCount[c - 'a']-- > 0)
                sb.append(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            while (charCount[c - 'a']-- > 0)
                sb.append(c);
        }
        return sb.toString();
    }
}



/*
 * Approach of Custom Comparator from LC Official Editorial!
 */
class Solution {
    public String customSortString(String order, String s) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++)
            list.add(s.charAt(i));
        Collections.sort(list, (c1, c2) -> {
            return order.indexOf(c1) - order.indexOf(c2);
        });
        StringBuilder sb = new StringBuilder();
        for (char c : list)
            sb.append(c);
        return sb.toString();
    }
}
