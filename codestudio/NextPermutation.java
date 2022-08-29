import java.util.*;

/*
 * https://www.codingninjas.com/codestudio/problem-details/next-permutaion_893046
 */



/*
 * https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
 */
class Solution {
	public static ArrayList<Integer> nextPermutation(ArrayList<Integer> permutation) {
		int n = permutation.size(), i = n - 2, j = n - 1;
        if (n == 1)
            return permutation;
        while (i >= 0 && permutation.get(i) >= permutation.get((i + 1)))
            i--;
        if (i == -1) {
            i = 0;
            j = n - 1;
            while (i < j) {
                int temp = permutation.get(i);
                permutation.set(i, permutation.get(j));
                permutation.set(j, temp);
                i++;
                j--;
            }
            return permutation;
        }
        while (j > i && permutation.get(j) < permutation.get(i))
            j--;
        int temp = permutation.get(i);
        permutation.set(i, permutation.get(j));
        permutation.set(j, temp);
        j = i + 1;
        i = n - 1;
        while (j < i) {
            temp = permutation.get(i);
            permutation.set(i, permutation.get(j));
            permutation.set(j, temp);
            j++;
            i--;
        }
        return permutation;
	}
}
