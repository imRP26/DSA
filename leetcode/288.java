/*
 * https://leetcode.com/problems/unique-word-abbreviation/
 */



/*
 * Simple Application of HashMap!
 */
class ValidWordAbbr {

    Map<String, Set<String> > map;

    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String d : dictionary) {
            int n = d.length();
            String abbr = d;
            if (n > 2)
                abbr = d.charAt(0) + String.valueOf(n - 2) + d.charAt(n - 1);
            map.computeIfAbsent(abbr, k -> new HashSet<>()).add(d);
        }
    }
    
    public boolean isUnique(String word) {
        String abbr = word;
        int n = word.length();
        if (n > 2)
            abbr = word.charAt(0) + String.valueOf(n - 2) + word.charAt(n - 1);
        if (!map.containsKey(abbr))
            return true;
        Set<String> set = map.get(abbr);
        boolean flag = true;
        for (String s : set) {
            if (!s.equals(word)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */



/*
 * Smarter approach of the same concept from -> 
 * https://leetcode.com/problems/unique-word-abbreviation/solutions/73143/java-solution-with-one-hashmap-string-string-beats-90-of-submissions/
 */
class ValidWordAbbr {

	private Map<String, String> map;

	public ValidWordAbbr(String[] dictionary) {
		map = new HashMap<>();
		for (String d : dictionary) {
			String abbr = d;
			int n = abbr.length();
			if (n > 2)
				abbr = d.charAt(0) + String.valueOf(n - 2) + d.charAt(n - 1);
			if (map.containsKey(abbr)) {
				if (!map.get(abbr).equals(d))
					map.put(abbr, "");
			}
			else
				map.put(abbr, d);
		}
	}

	public boolean isUnique(String word) {
		String abbr = word;
		int n = word.length();
		if (n > 2)
			abbr = word.charAt(0) + String.valueOf(n - 2) + word.charAt(n - 1);
		if (!map.containsKey(abbr) || (map.containsKey(abbr) && map.get(abbr).equals(word)))
			return true;
		return false;
	}
}
