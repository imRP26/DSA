/*
 * https://codeforces.com/contest/1151/problem/B
 */
// Solution link -> https://www.youtube.com/watch?v=9TDSIM-KmRY
import java.io.*;
import java.util.*;


public class Main {
    
    /*
     * Idea of DP? :-
     * Since all the values of the matrix are < 2^10, so this gives us the idea of 
     * using hashing or rather DP (seeing the dimensions of the matrix).
     * Also XOR-ing of the values of the matrix will always be < 2^10.
     * 
     * DP State :-
     * dp(i, x) = 1, if we're able to obtain a xor-value of 'x' when we've processed 'i' rows.
     *          = 0, otherwise.
     * 
     * DP Transition :-
     * dp(i, x) -> dp(i + 1, x XOR a[i + 1][j]), where 0 <= j < number of columns
     * 
     * Final Answer :-
     * YES, if any 1 of dp(n, x) != 0, where x > 0, otherwise NO.
     * We can keep a secondary array to keep note of column numbers from previous rows.
     */
    
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
        int rows = Integer.parseInt(temp[0]), cols = Integer.parseInt(temp[1]), val = -1;
        int[][] matrix = new int[rows + 1][cols + 1], parent = new int[rows + 1][1024];
        for (int[] row : parent)
            Arrays.fill(row, -1);
        for (int i = 1; i <= rows; i++) {
            temp = br.readLine().trim().split(" ");
            for (int j = 1; j <= cols; j++)
                matrix[i][j] = Integer.parseInt(temp[j - 1]);
        }
        boolean[][] dp = new boolean[rows + 1][1024];
        dp[0][0] = true;
        for (int i = 0; i < rows; i++) {
            for (int xorVal = 0; xorVal < 1024; xorVal++) {
                if (!dp[i][xorVal])
                    continue;
                for (int j = 1; j <= cols; j++) {
                    int newXorVal = xorVal ^ matrix[i + 1][j];
                    dp[i + 1][newXorVal] = true;
                    parent[i + 1][newXorVal] = j;
                }
            }
        }
        boolean flag = false;
        for (int xorVal = 1; xorVal < 1024; xorVal++) {
            if (dp[rows][xorVal]) {
                flag = true;
                val = xorVal;
                break;
            }
        }
        if (!flag)
            System.out.println("NIE");
        else {
            System.out.println("TAK");
            List<Integer> list = new ArrayList<>();
            for (int i = rows; i >= 1; i--) {
                list.add(parent[i][val]);
                val ^= matrix[i][parent[i][val]];
            }
            for (int i = rows - 1; i >= 0; i--)
                System.out.print(list.get(i) + " ");
        }
	}
}
