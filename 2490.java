import java.util.*;


// Easy, Simulation
class Solution {
    public boolean isCircularSentence(String sentence) {
        sentence += ' ';
        List<String> wordList = new ArrayList<>();
        String word = "";
        for (char c : sentence.toCharArray()) {
            if (c == ' ') {
                wordList.add(word);
                word = "";
            }
            else
                word += c;
        }
        int n = wordList.size();
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            String word1 = wordList.get(i), word2 = wordList.get(j);
            if (word1.charAt(word1.length() - 1) != word2.charAt(0))
                return false;    
        }
        return true;
    }
}
