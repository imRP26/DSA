/*
 * https://leetcode.com/problems/count-k-subsequences-of-a-string-with-maximum-beauty/
 */



/*
 * Approach from -> 
 * https://leetcode.com/problems/count-k-subsequences-of-a-string-with-maximum-beauty/solutions/3993005/c-with-explanation-simple-mathematics/
 */
class Solution {

    private long mod = (long)1e9 + 7;

    private long fastPower(long base, long expo) {
        long ans = 1;
        while (expo > 0) {
            if (expo % 2 == 1)
                ans = (ans * base) % mod;
            base = (base * base) % mod;
            expo /= 2;
        }
        return ans;
    }

    private long factorial(long n) {
        long ans = 1;
        for (int i = 1; i <= n; i++)
            ans = (ans * i) % mod;
        return ans;
    }

    private long nCr(int n, int r) {
        long ans = factorial(n), deno = (factorial(r) * factorial(n - r)) % mod;
        ans = (ans * fastPower(deno, mod - 2)) % mod;
        return ans;
    }

    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        for (char c : map.keySet())
            maxPQ.offer(map.get(c));
        if (maxPQ.size() < k)
            return 0;
        long res = 1;
        while (k > 0) {
            int x = maxPQ.peek(), countEqual = 0;
            while (maxPQ.size() > 0 && maxPQ.peek() == x) {
                countEqual += 1;
                maxPQ.poll();
            }
            if (countEqual <= k) {
                k -= countEqual;
                res = (res * fastPower(x, countEqual)) % mod;
            }
            else {
                res = (res * fastPower(x, k)) % mod;
                res = (res * nCr(countEqual, k)) % mod;
                break;
            }
        }
        return (int)res;
    }
}



/*
 * Approach of Knapsack-based DP from -> 
 * https://leetcode.com/problems/count-k-subsequences-of-a-string-with-maximum-beauty/solutions/3993063/knapsack-take-notake/
 */
class Solution {

    private Map<Character, Integer> map = new HashMap<>();
    private Map<String, Long> dp = new HashMap<>();
    private List<Integer> list = new ArrayList<>();
    private long mod = (long)1e9 + 7;

    private long memoization(int i, int k, long val) {
        if (i == list.size())
            return (k == 0 && val == 0) ? 1 : 0;
        if (k < 0 || val < 0)
            return 0;
        String x = String.valueOf(i) + "#" + String.valueOf(k) + "#" + String.valueOf(val);
        if (dp.containsKey(x))
            return dp.get(x);
        long take = list.get(i) * memoization(i + 1, k - 1, val - list.get(i));
        long notTake = memoization(i + 1, k, val);
        dp.put(x, (take + notTake) % mod);
        return dp.get(x);
    }

    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        if (k > map.size())
            return 0;
        for (char x : map.keySet())
            list.add(map.get(x));
        long val = 0;
        Collections.sort(list, Collections.reverseOrder());
        for (int i = 0; i < k; i++)
            val += list.get(i);
        return (int)memoization(0, k, val);
    }
}
