/*
 * https://leetcode.com/problems/concatenated-words/
 */



/*
 * Approach of DFS + Caching from 
 * https://leetcode.com/problems/concatenated-words/solutions/541520/java-dfs-memoization-clean-code/
 */ 
class Solution1 {

    Map<String, Boolean> cache = new HashMap<>();
    Set<String> wordSet;

    private boolean dfs(String word) {
        if (cache.containsKey(word))
            return cache.get(word);
        for (int i = 1; i < word.length(); i++) {
            if (!wordSet.contains(word.substring(0, i)))
                continue;
            String suffix = word.substring(i);
            if (wordSet.contains(suffix) || dfs(suffix)) {
                cache.put(word, true);
                return true;
            }
        }
        cache.put(word, false);
        return false;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        wordSet = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (dfs(word))
                result.add(word);
        }
        return result;
    }
}



/*
 * Without any caching, just DFS + a small trick!
 * https://leetcode.com/problems/concatenated-words/solutions/541520/java-dfs-memoization-clean-code/
 */
class Solution2 {

    Set<String> wordSet;

    private boolean dfs(String word) {
        for (int i = 1; i < word.length(); i++) {
            if (wordSet.contains(word.substring(0, i))) {
                String suffix = word.substring(i);
                if (wordSet.contains(suffix) || dfs(suffix)) {
                    wordSet.add(word);
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        wordSet = new HashSet<>(Arrays.asList(words));
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (dfs(word))
                result.add(word);
        }
        return result;
    }
}



/*
 * Need to refer to more solutions from here when time permits, also need to 
 * understand about the time complexity involved!!
 * https://leetcode.com/problems/concatenated-words/solutions/541520/java-dfs-memoization-clean-code/
 */
