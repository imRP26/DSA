/*
 * https://leetcode.com/problems/palindrome-partitioning/
 */



/*
 * Referenced from -> 
 * https://www.youtube.com/watch?v=WBgsABoClE0
 * https://leetcode.com/problems/palindrome-partitioning/solutions/857510/palindrome-partitioning/?orderBy=most_votes
 */
class Solution {

    int len;
    List<List<String> > result = new ArrayList<>();

    private boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--))
                return false;
        }
        return true;
    }

    private void recursion(String s, int index, List<String> list) {
        if (index == len) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < len; i++) {
            if (!isPalindrome(s, index, i))
                continue;
            list.add(s.substring(index, i + 1));
            recursion(s, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    public List<List<String>> partition(String s) {
        len = s.length();
        recursion(s, 0, new ArrayList<>());
        return result;
    }
}



/*
 * Referenced from -> 
 * https://leetcode.com/problems/palindrome-partitioning/solutions/857510/palindrome-partitioning/?orderBy=most_votes
 */

 