import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/largest-number/
 */



/*
 * Implementing a custom comparator function...
 * Analyzing the Time Complexity...
 * Let average length of a string = O(k) and size of I/P array = O(n).
 * In case of merge sort, we'll need O(log n) times of operations, but the 
 * comparison at the leaf node takes O(k), instead of O(1).
 * Thus, total TC = O(n * k * log n)
 */
class Solution1 {
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int n : nums)
            list.add(String.valueOf(n));
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1);
            }
        };
        Collections.sort(list, comp);
        // An extreme edge case, when we have only a bunch of 0's in the I/P array
        if (list.get(0).charAt(0) == '0')
            return "0";
        StringBuilder sb = new StringBuilder();
        for (String s : list)
            sb.append(s);
        return sb.toString();
    }
}



// Another way of doing the same thing as above
class Solution2 {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++)
            strs[i] = nums[i] + "";
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s1.compareTo(s2);
            }
        });
        if (strs[n - 1].charAt(0) == '0')
            return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.insert(0, strs[i]);
        return sb.toString();
    }
}
