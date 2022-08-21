import java.util.*;
<<<<<<< HEAD
import javafx.util.Pair;
=======
>>>>>>> 780bea96af145cea4ceff0ddaf2d8278a3b4a171

/*
 * https://leetcode.com/problems/word-ladder/
 */



<<<<<<< HEAD
// My Naive Solution - BFS
class Solution1 {
=======
// My Naive BFS
class Solution {
>>>>>>> 780bea96af145cea4ceff0ddaf2d8278a3b4a171
    
    boolean computeDiff(String str1, String str2) {
        int numDiff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i))
                numDiff++;
        }
        return numDiff == 1;
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        Set<String> isVisited = new HashSet<>();
        Pair<String, Integer> p = new Pair(beginWord, 1);
        queue.offer(p);
        int result = 0;
        while (!queue.isEmpty()) {
            p = queue.poll();
            beginWord = p.getKey();
            if (isVisited.contains(beginWord))
                continue;
            isVisited.add(beginWord);
            int level = p.getValue();
            if (beginWord.equals(endWord)) {
                result = level;
                break;
            }
            for (String word : wordList) {
                if (!isVisited.contains(word) && computeDiff(word, beginWord))
                    queue.offer(new Pair(word, level + 1));
            }
        }
        return result;
    }
}



<<<<<<< HEAD
// Same concept, but a different approach
class Solution2 {
=======
// Another approach of the same concept as above
class Solution {
>>>>>>> 780bea96af145cea4ceff0ddaf2d8278a3b4a171
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int changes = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord))
                    return changes;
                for (int j = 0; j < word.length(); j++) {
                    for (int k = 'a'; k <= 'z'; k++) {
                        char[] charArr = word.toCharArray();
                        charArr[j] = (char) k;
                        String str = new String(charArr);
                        if (wordSet.contains(str) && !visited.contains(str)) {
                            queue.offer(str);
                            visited.add(str);
                        }
                    }
                }
            }
            changes++;
        }
        return 0;
    }
}



<<<<<<< HEAD
/*
 * 2-end BFS Approach :-
 * The idea behind bidirectional search is to run 2 simultaneous searches - 1 
 * forward from the initial state and the other backward from the goal, hoping 
 * that the 2 searchees meet in the middle.
 * This approach is yet to be understood - seems really un-intuitive!
 */
class Solution3 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;
        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        wordList.remove(beginWord);
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        wordList.remove(endWord);
        int length = 1;
        while (!beginSet.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] wordArray = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    char oldChar = wordArray[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        wordArray[i] = ch;
                        String str = String.valueOf(wordArray);
                        if (endSet.contains(str))
                            return length + 1;
                        if (wordSet.contains(str)) {
                            temp.add(str);
                            wordSet.remove(str);
                        }
                    }
                    wordArray[i] = oldChar;
                }
            }
            beginSet = temp.size() < endSet.size() ? temp : endSet;
            endSet = beginSet.size() < endSet.size() ? endSet : temp;
            length++;
        }
        return 0;
    }
}
=======
// Need to understand the 2-end BFS approach - seems tough as of now!!
>>>>>>> 780bea96af145cea4ceff0ddaf2d8278a3b4a171
