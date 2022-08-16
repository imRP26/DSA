import java.util.*;

/*
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */



// Hopelessly brute-force wala solution
class Solution1 {
    public int kthSmallest(int[][] matrix, int k) {
        List<Integer> list = new ArrayList<>();
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                list.add(matrix[i][j]);
        }
        Collections.sort(list);
        return list.get((k - 1));
    }
}



/*
 * Concept of Min-Heap :-
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/
 * TC = O(K * log(K)), SC = O(K)
 */ 
class minHeapNode {
    int row, column, value;
    
    public minHeapNode(int val, int r, int c) {
        this.value = val;
        this.row = r;
        this.column = c;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getColumn() {
        return this.column;
    }
}


class heapComparator implements Comparator<minHeapNode> {
    public int compare(minHeapNode x, minHeapNode y) {
        return x.value - y.value;
    }
}


class Solution2 {
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        PriorityQueue<minHeapNode> minHeap = new PriorityQueue<minHeapNode>(Math.min(rows, k), new heapComparator());
        for (int i = 0; i < Math.min(rows, k); i++)
            minHeap.offer(new minHeapNode(matrix[i][0], i, 0));
        minHeapNode element = minHeap.peek();
        while (k-- > 0) {
            element = minHeap.poll();
            int row = element.getRow(), column = element.getColumn();
            if (column < rows - 1)
                minHeap.offer(new minHeapNode(matrix[row][column + 1], row, column + 1));
        }
        return element.getValue();
    }
}



// Same approach as above, but by using a cleaner representation
class Solution3 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, result = -1;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < Math.min(n, k); i++)
            minHeap.offer(new int[] {matrix[i][0], i, 0});
        for (int i = 1; i <= k; i++) {
            int[] top = minHeap.poll();
            int row = top[1], column = top[2];
            result = top[0];
            if (column + 1 < n)
                minHeap.offer(new int[] {matrix[row][column + 1], row, column});
        }
        return result;
    }
}



/*
 * Solution of Max Heap as explained in here :- 
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/1322101/C%2B%2BJavaPython-MaxHeap-MinHeap-Binary-Search-Picture-Explain-Clean-and-Concise 
 * TC = O(N * N * log(K)), SC = O(K)
 */
class Solution4 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Integer>maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                maxHeap.offer(matrix[row][column]);
                if (maxHeap.size() > k)
                    maxHeap.poll();
            }
        }
        return maxHeap.poll();
    }
}



/*
 * Approach of Binary Search -> 
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/1322101/C%2B%2BJavaPython-MaxHeap-MinHeap-Binary-Search-Picture-Explain-Clean-and-Concise
 */
class Solution5 {
    public int kthSmallest(int[][] matrix) {

    }
}
