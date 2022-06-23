import java.util.*;

/*
 * Question Link -> https://binarysearch.com/problems/Detect-Voter-Fraud
 */



// Simple solution using HashSet
class Solution {
    public boolean solve(int[][] votes) {
        Set<Integer> set = new HashSet<>();
        for (int[] v : votes) {
            if (!set.add(v[1]))
                return true;
        }
        return false;
    }
}
