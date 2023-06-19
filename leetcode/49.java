/*
 * https://leetcode.com/problems/group-anagrams/
 */



/*
 * Making a sorted String as the key of the HashMap
 */
class Solution {
    public List<List<String> > groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
			return new ArrayList<>();
		Map<String, List<String> > map = new HashMap<>();
		for (String s : strs) {
			char ca[] = s.toCharArray();
			Arrays.sort(ca);
			String keyStr = String.valueOf(ca);
			if (! map.containsKey(keyStr))
				map.put(keyStr, new ArrayList<>());
			map.get(keyStr).add(s);
		}
		return new ArrayList<>(map.values());
    }
}



/*
 * Making a String as the key of the hashmap
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0)
			return new ArrayList<>();
        Map<String, List<String> > map = new HashMap<>();
		for (String s : strs) {
			char chArray[] = new char[26];
			for (char c : s.toCharArray())
				chArray[c - 'a'] ++;
			String keyStr = String.valueOf(chArray);
			if (! map.containsKey(keyStr))
				map.put(keyStr, new ArrayList<>());
			map.get(keyStr).add(s);
		}
		return new ArrayList<>(map.values());
    }
}



/*
 * Approach of String Hashing
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        int mod = 1_000_000_007;
        int[] powers = new int[100];
        powers[0] = 1;
        for (int i = 1; i < 100; i++) {
            powers[i] = powers[i - 1] * 26;
            powers[i] %= mod;
        }
        Map<Integer, List<String> > map = new HashMap<>();
        for (String str : strs) {
            int keyVal = 0;
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            for (int i = 0; i < str.length(); i++) {
                keyVal += (chars[i] - 'a' + 1) * powers[i];
                keyVal %= mod;
            }
            System.out.println(keyVal);
            map.computeIfAbsent(keyVal, k -> new ArrayList<>()).add(str);
        }
        List<List<String> > res = new ArrayList<>();
        for (int keyVal : map.keySet())
            res.add(new ArrayList<>(map.get(keyVal)));
        return res;
    }
}
