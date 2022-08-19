import java.util.*;

/*
 * https://leetcode.com/problems/word-ladder/
 */



// My Naive BFS
class Solution {
    
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



// Another approach of the same concept as above
class Solution {
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



// Need to understand the 2-end BFS approach - seems tough as of now!!
