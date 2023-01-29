/*
 * https://leetcode.com/problems/game-of-nim/
 */



/*
 * Simple solution, as learnt from B.Tech. days!!
 * More information from Approach 2 of 
 * https://leetcode.com/problems/game-of-nim/solutions/2886321/game-of-nim/?orderBy=most_votes
 */ 
class Solution1 {
    public boolean nimGame(int[] piles) {
        int xorval = 0;
        for (int pile : piles)
            xorval ^= pile;
        return xorval != 0;
    }
}



/*
 * Approach 1 (DP) from 
 * https://leetcode.com/problems/game-of-nim/solutions/2886321/game-of-nim/?orderBy=most_votes
 */
class Solution2 {

	Map<String, Boolean> memo = new HashMap<>();

	private String getKey(int[] piles) {
		StringBuilder key = new StringBuilder();
		for (int x : piles)
			key.append(x).append("-");
		return key.toString();
	}

	private boolean isNextPersonWinner(int[] piles, int remainingSum) {
		String key = getKey(piles);
		if (memo.containsKey(key))
			return memo.get(key);
		if (remainingSum == 0)
			return false;
		for (int i = 0; i < piles.length; i++) { // O(piles.length)
			for (int j = 1; j <= piles[i]; j++) { // O(max{piles[i]})
				piles[i] -= j;
				int[] nextState = piles.clone();
				Arrays.sort(nextState); // O(piles.length * log(piles.length))
				if (!isNextPersonWinner(nextState, remainingSum - j)) {
					memo.put(key, true);
					return true;
				}
				piles[i] += j;
			}
		}
		memo.put(key, false);
		return false;
	}

	public boolean nimGame(int[] piles) {
		int remainingSum = Arrays.stream(piles).sum(); // NEW SYNTAX
		Arrays.sort(piles);
		return isNextPersonWinner(piles, remainingSum);
	}
}
