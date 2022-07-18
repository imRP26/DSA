import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/lexicographical-numbers/
 */



// My Naive Approach - TC = O(n log n), SC = O(n)
class Solution1 {
    public List<Integer> lexicalOrder(int n) {
        String[] temp = new String[n];
        for (int i = 0; i < n; i++)
            temp[i] = String.valueOf(i + 1);
        Arrays.sort(temp);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++)
            result.add(Integer.parseInt(temp[i]));
        return result;
    }
}



// Recursive Approach using DFS - TC = O(n), SC = O(log(10)n)
class Solution2 {

    public void dfs(int currNum, int n, List<Integer> result) {
        if (currNum > n) // overflow check
            return;
        result.add(currNum);
        for (int i = 0; i < 10; i++) {
            if (10 * currNum + i > n) // overflow check
                return;
            // preorder traversal of the resultant tree
            dfs(10 * currNum + i, n, result);
        }
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < 10; i++)
            dfs(i, n, result);
        return result;
    }
}



/*
 * Iterative Approach - TC = O(n * log(10)n), SC = O(1)
 * Viewing this is a preorder traversal, each tree node may have atmost 10 
 * children (last digit is from 0 - 9), the difference being that there's no 
 * need to go from the left child to the right child through the parent, since 
 * its now known how to go from 1 child to another child (which is addition of 1). 
 * So its almost like nodes at the same level get linked before the preorder traversal.
 */
class Solution3 {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>(n);
        int currNum = 1;
        for (int i = 1; i <= n; i++) {
            result.add(currNum);
            if (currNum * 10 <= n)
                currNum *= 10; // going to the leftmost lead node
            else if (currNum % 10 != 9 && currNum + 1 <= n)
                currNum += 1; // level order traversal - all children from left - right
            else {
                // when the rightmost node on this level has been hit
                while ((currNum / 10) % 10 == 9)
                    currNum /= 10; // going back to the upper level
                // preparing to go for the next (sibling wala) level order traversal
                currNum = currNum / 10 + 1; 
            }
        }
        return result;
    }
}
