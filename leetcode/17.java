/*
 * Question Link :-
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
*/



// Backtracking (key-pad as an array) using String
class Solution1 {

    String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", 
                     "wxyz"};
    
    public void combination(String prefix, String digits, int index, 
                            List<String> result) {
        if (index >= digits.length()) { // base condition
            result.add(prefix);
            return;
        }
        String letters = keys[digits.charAt(index) - '0'];
        for (char ch : letters.toCharArray())
            combination(prefix + ch, digits, index + 1, result);
    }

    public List<String> letterCombinations(String digits) {
        /*
         * Here, we can easily see that Backtracking is a given, similar to
         * the DFS problem.
         * For storing the final result, we use a LinkedList instead of an 
         * ArrayList, since insertion performance is better in case of the 
         * former.
         * For more info, refer to -> 
         * https://stackoverflow.com/questions/10656471/performance-differences-between-arraylist-and-linkedlist#:~:text=Why%20ArrayList%20is%20faster%3F&text=LinkedList%20is%20faster%20than%20ArrayList%20for%20insertion.,should%20be%20slower%20than%20LinkedList.
        */
        List<String> result = new LinkedList<>();
        // we need to return empty array in case digits is null, so a check
        if (digits == null || digits.length() == 0)
            return result;
        combination("", digits, 0, result);
        return result;
    }
}



// Backtracking (key-pad using map) using StringBuilder
class Solution2 {
    /*
     * For a variable declared as "final", it can be assigned different values 
     * at runtime when initialized.
     * For static final, all instances share the same value, and can't be 
     * altered after being first initialized.
     * A static variable stays in the memory for the entire lifetime of the 
     * application, and is initialised during class loading. 
     * A non-static variable is being initialised each time a new object is 
     * constructed.
     * Using "static" reduces the memory footprint per instance. It possibly is 
     * also favourable for cache hits. 
     * And it just makes sense: "static" should be used for things that are shared 
     * across all instances (a.k.a. objects) of a certain type (a.k.a. class).
    */
    private static final Map<Character, String> KEYS = new HashMap<>();
    static {
        //KEYS.put('0', "");
        //KEYS.put('1', "");
        KEYS.put('2', "abc");
        KEYS.put('3', "def");
        KEYS.put('4', "ghi");
        KEYS.put('5', "jkl");
        KEYS.put('6', "mno");
        KEYS.put('7', "pqrs");
        KEYS.put('8', "tuv");
        KEYS.put('9', "wxyz");
    }
    
    public void dfs(StringBuilder sb, int index, String digits, 
                    List<String> result) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }
        char digit = digits.charAt(index);
        //String options = KEYS.getOrDefault(digit, digit + "");
        String options = KEYS.get(digit);
        for (char option : options.toCharArray()) {
            sb.append(option);
            dfs(sb, index + 1, digits, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<>();
        if (digits.length() == 0)
            return result;
        StringBuilder sb = new StringBuilder();
        dfs(sb, 0, digits, result);
        return result;
    }
}



// BFS Solution 1
class Solution3 {

    public List<String> letterCombinations(String digits) {
        LinkedList<String> result = new LinkedList<String>();
        if (digits.isEmpty())
            return result;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", 
                                         "mno", "pqrs", "tuv", "wxyz"};
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (result.peek().length() == i) {
                String t = result.remove();
                for (char s : mapping[x].toCharArray())
                    result.add(t + s);
            }
        }
        return result;
    }
}



// BFS - Solution 2
class Solution4 {
    
    public List<String> letterCombinations(String digits) {
        LinkedList<String> result = new LinkedList<>();
        if (digits.isEmpty())
            return result;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", 
                                         "mno", "pqrs", "tuv", "wxyz"};
        result.add("");
        while (result.peek().length() != digits.length()) {
            String removed = result.remove();
            String map = mapping[digits.charAt(removed.length()) - '0'];
            for (char c : map.toCharArray())
                result.addLast(removed + c);
        }
        return result;
    }
}