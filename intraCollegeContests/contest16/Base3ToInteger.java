/*
 * Question Link -> https://binarysearch.com/problems/Base-3-to-Integer
 */



// Simple Simulation
import java.util.*;

class Solution {
    public int solve(String s) {
        int pow3 = 1, result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            result += pow3 * (s.charAt(i) - '0');
            pow3 *= 3;
        }
        return result;
    }
}
