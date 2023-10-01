/*
 * https://leetcode.com/problems/encrypt-and-decrypt-strings/
 */



/*
 * Approach from @lee215's (GAWD-LEVEL) solution -> 
 * https://leetcode.com/problems/encrypt-and-decrypt-strings/solutions/1909025/java-c-python-two-hashmaps-with-explanation/
 */
class Encrypter {

    private Map<Character, String> keyToValMap = new HashMap<>();
    private Map<String, Integer> dictStrCounts = new HashMap<>();

    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        for (int i = 0; i < keys.length; i++)
            keyToValMap.put(keys[i], values[i]);
        for (String d : dictionary) {
            String encryptedStr = encrypt(d);
            dictStrCounts.put(encryptedStr, dictStrCounts.getOrDefault(encryptedStr, 0) + 1);
        }
    }
    
    public String encrypt(String word1) { // word1 is to be encrypted
        StringBuilder sb = new StringBuilder();
        for (char c : word1.toCharArray()) {
            if (!keyToValMap.containsKey(c))
                return "";
            sb.append(keyToValMap.get(c));
        }
        return sb.toString();
    }
    
    public int decrypt(String word2) { // word2 is an encrypted string
        return dictStrCounts.getOrDefault(word2, 0);
    }
}

/**
 * Your Encrypter object will be instantiated and called as such:
 * Encrypter obj = new Encrypter(keys, values, dictionary);
 * String param_1 = obj.encrypt(word1);
 * int param_2 = obj.decrypt(word2);
 */
 